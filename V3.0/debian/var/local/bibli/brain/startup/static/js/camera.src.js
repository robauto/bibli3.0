/*
Robato Camera module
by James Gardner for Robauto
started 2016-11-12

This module governs the camera and video playback

*/

(function(){
"use strict";

RB.camera = {
	socket: null,
	streaming: false,
	bg_src: null,
	init: function () {
		var t = RB.camera;
		t.bg_src = $("#vdostream").attr("src");
		$("#vdoswitch").on("change", function () {
			t.streaming = $(this).prop("checked");
			if (t.streaming) {
				t.socket = new ReconnectingWebSocket("ws://" + window.location.hostname + (location.port ? ':'+location.port: '') + "/video");
				t.socket.onmessage = t.message;
			} else {
				if (t.socket) {
					t.socket.close();
					t.socket = null;
					$("#vdostream").attr("src", t.bg_src);
				}
			}
		});
	},
	message: function (event) {
		$("#vdostream").attr("src", "data:image/jpeg;base64, " + event.data);
	},
	showDesktop: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({show: "desktop"}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/screen'
		});
	}
};

$(document).ready(RB.camera.init);
}());
/*
Robato Bluetooth module
by James Gardner for Robauto
started 2016-12-06

This module governs the bluetooth scanning and connection
for BT audio devices

*/

(function(){
"use strict";

RB.bt = {
	init: function () {
		var t = RB.bt;
		// Wire up the buttons
		$("button#bt-scan").on("click", t.scan).removeAttr("disabled");
		// $("button#bt-reconnect").on("click", t.reconnect).removeAttr("disabled");
		$("button#bt-remove").on("click", t.remove).removeAttr("disabled");
		// $("button#bt-disconnect").on("click", t.disconnect).removeAttr("disabled");
	},
	scan: function () {
		$(this).html("Scanning...").attr("disabled", "disabled");
		$("#bt-loading").addClass("is-active");
		$("#found-devices").hide();
		$.ajax({
			dataType: 'json',
			type: 'GET',
			url: '/api/bluetooth',
			success: RB.bt.parse_devices
		});
	},
	parse_devices: function (data) {
		var i, html, device, $html,
			$tb = $("#found-devices tbody");
		$tb.html("");
		for (i = 0; i < data.devices.length; i += 1) {
			device = data.devices[i];
			html = "<tr><td class='mdl-data-table__cell--non-numeric name'>" + device.name + "</td>" +
			"<td class='mdl-data-table__cell--non-numeric addr'>" + device.mac_address + "</td>" + 
			"<td class='mdl-data-table__cell--non-numeric'>" + 
			"<button type='button' class='mdl-button mdl-js-button mdl-button--accent'>Connect</button></td></tr>";
			$html = $(html);
			$html.find("button").on("click", RB.bt.connect);
			$tb.append($html);
		}
		if (data.devices.length < 1) {
			$tb.html("<tr><td colspan='3' class='mdl-data-table__cell--non-numeric'>No devices found. Please ensure your device is in pairing mode and try again.</td></tr>");
		}
		$("#bt-scan").removeAttr("disabled").html("Scan");
		$("#bt-loading").removeClass("is-active");
		$("#found-devices").show();
	},
	connect: function () {
		var name = $(this).parents("tr").find("td.name").html(),
			addr = $(this).parents("tr").find("td.addr").html();
		$(this).attr("disabled", "disabled").html("Connecting...");
		$("#bt-loading").addClass("is-active");
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({name: name, mac_address: addr}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/bluetooth',
			success: function () {
				RB.bt.connect_success(name);
				$("#bt-loading").removeClass("is-active");
				$(this).removeAttr("disabled", "disabled").html("Connect");
			}
		});
	},
	connect_success: function (name) {
		RB.toast("Connected to " + name);
		$("#bt-paired-name").html(name);
		$("#bt-pair").hide();
		$("#bt-paired").show();
	},
	remove: function () {
		$.ajax({
			dataType: 'json',
			type: 'DELETE',
			url: '/api/bluetooth',
			success: function () {
				RB.toast("Bluetooth disconnected");
				$("#bt-paired").hide();
				$("#bt-pair").show();
			}
		});
	},
	/*
	reconnect: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({connect: true}),
			dataType: 'json',
			processData: false,
			type: 'PATCH',
			url: '/api/bluetooth',
			success: function () {
				RB.toast("Reconnected to: " + $("#bt-paired-name").html());
			}
		});
	},
	disconnect: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({connect: false}),
			dataType: 'json',
			processData: false,
			type: 'PATCH',
			url: '/api/bluetooth',
			success: function () {
				RB.toast("Disconnected from: " + $("#bt-paired-name").html());
			}
		});
	} */
};

$(document).ready(RB.bt.init);
}());
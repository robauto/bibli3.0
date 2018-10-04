/*
Robauto Media module
by James Gardner for Robauto
started 2016-11-11

This module governs the media related functions of Bibli:
* Text to speech in English and Spanish

*/

(function(){
"use strict";

RB.media = {
	init: function () {
		var t = RB.media;
		// event handlers for text to speech
		$("#speakform-en").on("submit", function (e) {
			e.preventDefault();
			t.speak($(this).find("textarea").val(), "en");
		});
		$("#speakform-es").on("submit", function (e) {
			e.preventDefault();
			t.speak($(this).find("textarea").val(), "es");
		});
		// volume slider
		$("#volume").on("change input", t.volumeChange);
		// add audio form
		$("#addmusic").on("submit", t.addMusic);
		// query the server for the media
		$.ajax({
			type: 'GET',
			url: '/api/media',
			dataType: 'json',
			success: function (data) {
				t.populateMusic(data["music"]);
				t.populateStories(data["story"]);
				t.populateVideos(data["video"]); 
				componentHandler.upgradeDom();
			}
		});
	},
	toast: function (msg) {
		var notification = document.querySelector('.mdl-js-snackbar');
		notification.MaterialSnackbar.showSnackbar({
			message: msg
		});
	},
	// nextVolumeSend: null,
	volumeChange: function (e) {
		var val = parseInt($(this).val()),
			now = new Date(),
			t = RB.media;
		// update the volume label
		if (val > 0) {
			$("#volume-label").html(val + "%");
		} else {
			$("#volume-label").html("off");
		}
		// only send the volume to the server every 300 milliseconds
		// if (t.nextVolumeSend && t.nextVolumeSend > now && e.type != "change") {
		// actually, for now, only send it on releasing the slider
		if (e.type != "change") {
			return;
		}
		// t.nextVolumeSend = new Date(now.getTime() + 500);
		$.ajax({
			type: 'PATCH',
			url: '/api/audio',
			dataType: 'json',
			data: JSON.stringify({
				volume: val / 100.0
			}),
			processData: false,
			contentType: 'application/json'
		});
	},
	/*
	populateMusic: function (songs) {
		var i, song, html,
			$ul = $("#music-cell ul");
		if (songs.length > 0) {
			$ul.html("");
		}
		for (i = 0; i < songs.length; i += 1	) {
			song = songs[i];
			html = '<li class="mdl-list__item mdl-list__item--three-line">' +
			'<span class="mdl-list__item-primary-content">' + 
			'<i class="material-icons mdl-list__item-avatar">play_arrow</i>' + 
			'<span>' + song.title + '</span>' + 
			'<span class="mdl-list__item-text-body">' +
			'By: ' + song.artist +
			'<br />Duration: ' + song.duration +
			'</span></span></li>';
			$ul.append(html);
		}
	}, */
	populateMusic: function (songs) {
		var i, song, html,
			$tb = $("#music-cell tbody");
		if (songs.length > 0) {
			$tb.html("");
		}
		for (i = 0; i < songs.length; i += 1	) {
			RB.media.appendSong(songs[i], $tb);
		}
		// $tb.find("button.play").on("click", RB.media.musicClick);
	},
	appendSong: function (song, $tb) {
		var $tr = $("<tr class='media' data-file='" + song.file + "' data-title='" + song.title + "'>" + 
		" <td class='mdl-data-table__cell--non-numeric'><button " + 
		"class='play mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored mdl-js-ripple-effect'>" +
		"<i class='material-icons'>play_arrow</i></button></td>" +
		"<td class='mdl-data-table__cell--non-numeric'>" + song.title + "</td>" + 
		"<td class='mdl-data-table__cell--non-numeric'>" + song.artist + "</td>" + 
		"<td class='mdl-data-table__cell--non-numeric'>" + song.duration + "</td>" + 
		"</tr>");
		$tr.find("button.play").on("click", RB.media.musicClick);
		$tb.append($tr);
	},
	musicClick: function (e) {
		var t = RB.media,
			$tr = $(this).parents("tr"),
			file = $tr.attr("data-file"),
			title = $tr.attr("data-title");
		// is this a play or stop click
		if ($tr.hasClass("playing")) {
			t.musicStop();
			t.mediaStop();
			return;
		}
		t.mediaStop();
		t.mediaPlay($tr);
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({
				file: file,
				type: "music"
			}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/audio',
			success: function () {
				t.toast('Playing "' + title + '"');
			}
		});
	},
	musicStop: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({file: ""}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/audio',
			success: function () {
				RB.media.toast('Stopping music');
			}
		});
	},
	addMusic: function (e) {
		e.preventDefault();
		var files = $('#addmusic input[type=file]')[0].files;
		function upload(file) {
			var reader = new FileReader();
			reader.addEventListener("load", function () {
				var d = {
					name: file.name,
					mime: file.type,
					type: "music",
					b64: this.result.substr(this.result.indexOf(",") + 1)
				}
				$.ajax({
					contentType: 'application/json',
					data: JSON.stringify(d),
					dataType: 'json',
					processData: false,
					type: 'POST',
					url: '/api/media',
					success: function (data) {
						RB.media.toast(file.name + ' uploaded.');
						// look to see if this song is already in the list
						if ($("#music-cell tr[data-file='" + data.music.file + "']").length < 1) {
							RB.media.appendSong(data.music, $("#music-cell tbody"));
						}
					}
				});
			});
			reader.readAsDataURL(file);
			// console.log(file);
		}
		if (files) {
			[].forEach.call(files, upload);
		}
	},
	storyClick: function (e) {
		var t = RB.media,
			$tr = $(this).parents("tr"),
			file = $tr.attr("data-file"),
			title = $tr.attr("data-title");
		// is this a play or stop click
		if ($tr.hasClass("playing")) {
			t.storyStop();
			t.mediaStop();
			return;
		}
		t.mediaStop();
		t.mediaPlay($tr);
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({
				file: file,
				type: "story"
			}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/audio',
			success: function () {
				t.toast('Playing "' + title + '"');
			}
		});
	},
	storyStop: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({file: ""}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/audio',
			success: function () {
				RB.media.toast('Stopping story');
			}
		});
	},
	videoClick: function () {
		var t = RB.media,
			$tr = $(this).parents("tr"),
			file = $tr.attr("data-file"),
			title = $tr.attr("data-title");
		// is this a play or stop click
		if ($tr.hasClass("playing")) {
			t.videoStop();
			t.mediaStop();
			return;
		}
		t.mediaStop();
		t.mediaPlay($tr);
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({
				file: file,
				type: "video"
			}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/video',
			success: function () {
				t.toast('Playing "' + title + '"');
			}
		});
	},
	videoStop: function () {
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({file: ""}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/video',
			success: function () {
				RB.media.toast('Stopping video');
			}
		});
	},
	mediaPlay: function ($tr) {
		$tr.addClass("playing");
		$tr.find("button .material-icons").html("stop");
	},
	mediaStop: function () {
		$("tr.media.playing").each(function () {
			$(this).removeClass("playing");
			$(this).find("button.play .material-icons").html("play_arrow");
		});
	},
	populateStories: function (stories) {
		var i, story, html,
			$tb = $("#story-cell tbody");
		if (stories.length > 0) {
			$tb.html("");
		}
		for (i = 0; i < stories.length; i += 1	) {
			story = stories[i];
			html = "<tr class='media' data-file='" + story.file + "' data-title='" + story.title + "'>" +
			"<td class='mdl-data-table__cell--non-numeric'>" +
			"<button class='play mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored mdl-js-ripple-effect'>" +
			"<i class='material-icons'>play_arrow</i></button></td>" +
			"<td class='mdl-data-table__cell--non-numeric'>" + story.title + "</td>" + 
			"<td class='mdl-data-table__cell--non-numeric'>" + story.author + "</td>" + 
			"<td class='mdl-data-table__cell--non-numeric'>" + story.duration + "</td>" + 
			"</tr>";
			$tb.append(html);
		}
		$tb.find("button.play").on("click", RB.media.storyClick);
	},
	populateVideos: function (videos) {
		var i, video, html,
			$tb = $("#video-cell tbody");
		if (videos.length > 0) {
			$tb.html("");
		}
		for (i = 0; i < videos.length; i += 1	) {
			video = videos[i];
			html = "<tr class='media' data-file='" + video.file + "' data-title='" + video.title + "'>" +
			"<td class='mdl-data-table__cell--non-numeric'>" +
			"<button class='play mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored mdl-js-ripple-effect'>" +
			"<i class='material-icons'>play_arrow</i></button></td>" +
			"<td class='mdl-data-table__cell--non-numeric'>" + video.title + "</td>" + 
			"<td class='mdl-data-table__cell--non-numeric'>" + video.duration + "</td>" + 
			"</tr>";
			$tb.append(html);
		}
		$tb.find("button.play").on("click", RB.media.videoClick);
	},
	speak: function (text, language) {
		// default language
		if (!language) {
			language = "en";
		}
		// speak API call
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({msg: text, lang: language}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/speak',
			success: function () {}
		});
	}
};

$(document).ready(RB.media.init);
}());
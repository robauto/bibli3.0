/*
Base BiBli module
by James Gardner for Robauto
started 2016-11-29

This module provides the base functionality for the BiBli web UI.
*/

var RB;

(function(){
"use strict";

RB = {
	init: function() {
		// navigation link clicks
		$(".mdl-navigation__link").on("click", RB.navLink);
		// settings page
		// name change keyup, enable or disable the submit button
		$("#name-change input[type='text']").on("keyup", function () {
			var $b = $("#name-change button[type='submit']");
			if ($(this).val() === $(this).attr("data-orig")) {
				$b.attr("disabled", "disabled");
			} else {
				$b.removeAttr("disabled");
			}
		});
		// settings page forms
		$("#name-change").on("submit", RB.nameChange);
		$("#portrait-change").on("submit", RB.portraitChange);
	},
	navLink: function (e) {
		e.preventDefault();
		var page = $(this).attr("href").substr(1);
		$(".mdl-layout__obfuscator.is-visible").trigger("click");
		$(".mdl-layout__tab-panel.is-active").removeClass("is-active");
		$("#" + page).addClass("is-active");
		$("#activepage").html(page);
	},
	nameChange: function (e) {
		e.preventDefault();
		var name = $("#bibliname").val();
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({
				name: name
			}),
			dataType: 'json',
			processData: false,
			type: 'PATCH',
			url: '/api/settings',
			success: function () {
				alert("Name changed. Please reboot your BiBli for the change to fully take effect.");
			}
		});
	},
	portraitChange: function (e) {
		e.preventDefault();
		var file = $('#portrait-change input[type=file]')[0].files[0],
			reader = new FileReader();
		reader.addEventListener("load", function () {
			var d = {
				portrait: {
					name: file.name,
					mime: file.type,
					b64: this.result.substr(this.result.indexOf(",") + 1)
				}
			}
			$.ajax({
				contentType: 'application/json',
				data: JSON.stringify(d),
				dataType: 'json',
				processData: false,
				type: 'PATCH',
				url: '/api/settings',
				success: function (data) {
					$("#portrait-change img").attr("src", "/portrait#" + new Date().getTime());
				}
			});
		});
		reader.readAsDataURL(file);	
	},
	// Shows a toast, the notification that pops up on the bottom of the screen
	toast: function (msg) {
		var notification = document.querySelector('.mdl-js-snackbar');
		notification.MaterialSnackbar.showSnackbar({
			message: msg
		});
	}
}

$(document).ready(RB.init);
}());
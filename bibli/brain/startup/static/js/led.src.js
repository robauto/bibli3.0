/*
Robato LED module
by James Gardner for Robauto
started 2016-11-14

This module governs the LED controls

*/

// Global RB object, protected from redeclaration by other potential modules
if (typeof RB === "undefined") {
	var RB = {};
}

(function(){
"use strict";

RB.led = {
	// one color for each rainbow entry and black
	colors: ["ff0000", "ff8800", "ffff00", "00ff00", "0000ff", "ff00ff", "ffffff", "000000"],
	init: function () {
		RB.led.populatePalette();
	},
	populatePalette: function () {
		var i, color,
			html = "",
			t = RB.led,
			$p = $("#color-palette");
		for (i = 0; i < t.colors.length; i += 1) {
			color = t.colors[i];
			html += "<button class='swatch mdl-shadow--3dp' data-val='" + color + "' style='background: #" + color + "'></button>"
		}
		$p.html(html);
		$p.find(".swatch").on("click", function () {
			t.swatchClick($(this));
		});
	},
	swatchClick: function ($s) {
		$("#color-palette .swatch.selected").removeClass("selected");
		$s.addClass("selected");
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({color: $s.attr("data-val")}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/led'
		});
	}
};

$(document).ready(RB.led.init);
}());
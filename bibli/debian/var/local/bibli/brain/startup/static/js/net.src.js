/*
Robato Network module
by James Gardner for Robauto
started 2016-11-14

This module governs the mesh network to find other BibLis

*/

(function(){
"use strict";

RB.net = {
	init: function () {
		var t = RB.net;
		// populate the known biblis
		$.ajax({
			contentType: 'application/json',
			dataType: 'json',
			type: 'GET',
			url: '/api/bibli',
			success: t.processBiblis
		});
	},
	processBiblis: function (data) {
		var i, bibli, fqdn, link,
			html = ["", "", ""];
		for (i = 0; i < data["biblis"].length; i += 1) {
			bibli = data["biblis"][i];
			if (bibli["self"]) {
				continue;
			}
			fqdn = bibli["fqdn"];
			if (!bibli["fqdn"] || bibli["fqdn"].indexOf(".") < 0 || bibli["fqdn"].toLowerCase() === bibli["name"].toLowerCase()) {
				fqdn = bibli["ip"];
			}
			link = fqdn.indexOf(".robot") > -1 ? fqdn : bibli["ip"];
			html[i%3] += "<div class='mdl-card mdl-shadow--2dp'>" + 
			"<div class='mdl-card__title mdl-card--expand' style=\"background-image: url('http://" + bibli["ip"] + "/portrait')\">" +
			"<h2 class='mdl-card__title-text'>" + bibli["name"] + "</h2></div>" + 
			"<div class='mdl-card__supporting-text'>" + fqdn + "</div>" +
			"<div class='mdl-card__actions mdl-card--border'>" +
			"<a class='mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect' href='http://" + link + "'>Control</a>" +
			"</div></div>";
		}
		for (i = 0; i < 3; i += 1) {
			$("#biblis" + i).html(html[i]);
		}
	}
};

$(document).ready(RB.net.init);
}());
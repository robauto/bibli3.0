/*
Robauto Demo module
by James Gardner for Robauto
started 2016-12-08

This module handles listing, executing and adding new demos

*/

(function(){
"use strict";

RB.demo = {
	init: function () {
		var t = RB.demo;
		// get the list of existing demos
		$.ajax({
			type: 'GET',
			url: '/api/demo',
			dataType: 'json',
			success: function (data) {
				t.populate(data["demos"]); 
				componentHandler.upgradeDom();
			}
		});
		$("#adddemo").on("submit", t.addDemo);
	},
	populate: function (demos) {
		var i, $ul = $("#demos");
		for (i = 0; i < demos.length; i += 1) {
			RB.demo.appendDemo(demos[i], $ul);
		}
	},
	appendDemo: function (demo, $ul) {
		var $li = $("<li class='mdl-list__item mdl-list__item--three-line' data-file='" + demo.file + "'>" +
			"<span class='mdl-list__item-primary-content'>" +
			"<i class='material-icons mdl-list__item-avatar'>code</i>" +
			"<span class='demoname'>" + demo.name + "</span>" +
			"<span class='mdl-list__item-text-body'>" + demo.description + "</span>" +
			"</span><span class='mdl-list__item-secondary-content'>" +
			"<a class='mdl-list__item-secondary-action' href='#''><i class='material-icons'>play_arrow</i></a>" +
			"</span></li>");
		$li.find(".mdl-list__item-secondary-action").on("click", function (e) {
			e.preventDefault();
			var file = $(this).parents("li").attr("data-file"),
				name = $(this).parents("li").find(".demoname").html();
			RB.demo.run(file, name);
		});
		$ul.append($li);
	},
	run: function (filename, name) {
		$.ajax({
			dataType: 'json',
			type: 'GET',
			url:'/api/demo/' + filename,
			success: function () {
				RB.toast("Running " + name);
			}
		});
	},
	addDemo: function (e) {
		e.preventDefault();
		var files = $('#adddemo input[type=file]')[0].files;
		function upload(file) {
			var reader = new FileReader();
			reader.addEventListener("load", function () {
				var d = {
					file: file.name,
					mime: file.type,
					b64: this.result.substr(this.result.indexOf(",") + 1)
				}
				$.ajax({
					contentType: 'application/json',
					data: JSON.stringify(d),
					dataType: 'json',
					processData: false,
					type: 'POST',
					url: '/api/demo',
					success: function (data) {
						if (data["demo"]) {
							RB.toast(file.name + ' uploaded.');
							RB.demo.appendDemo(data["demo"], $("#demos"));
						} else {
							RB.toast('Invalid file.');
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
};

$(document).ready(RB.demo.init);
}());
/*
Robato Joypad module
by James Gardner for Robauto
started 2016-11-10

This module governs the behavior of the joypad, that allows controling a Bibli by:
* keyboard (WASD or arrow keys)
* mouse or touch by moving cursor/finger over the joypad
* gamepad
  * only works on Firefox and Chrome
  * uses a hack for a Chrome bug (Chrome never fires a connected event for an already attached gamepad)
It translates the input into X,Y coordinates between -1.0 and 1.0.
Those coordiantes are sent at up to 4FPS to the server/Bibli.
*/

// Global RB object, protected from redeclaration by other potential modules
if (typeof RB === "undefined") {
	var RB = {};
}

(function(){
"use strict";

RB.joy = {
	// 1/2 the size of the usable joypad area in SVG
	size: 140,
	// padding around joypad area in SVG to show cursor
	padding: 40,
	// whether the mouse/touchpad is pressed, or a touch is happening
	mouseDown: false,
	// current coordianges of the cursor
	coords: [0,0],
	// prevents spamming the server with 0,0
	sentStopLastFrame: true,
	// current pressure from any pressed keys
	keyForce: [0,0],
	// object containing all currently connected gamepads
	gamepads: {},
	// number of connected gamepads
	gpcount: 0,
	// stores the interval reference for a running gamepad poll, this is a Chrome hack
	gppoll: null,
	// map the four xbox face buttons to a color
	gpColorMap: {
		11: "00ff00",	// Green A button
		12: "ff0000",	// Reb B button
		13: "0000ff",	// Blue X button
		14: "ffff00"	// Yellow Y button
	},
	socket: null,
	init: function () {
		var i, gamepads,
			t = RB.joy;
		// determine pixel to center of joypad
		t.center = t.size + t.padding;
		// Mouse and touch events
		$("#joypad").on("mousedown", function (e) {
			e.preventDefault();
			e.stopPropagation();
			var t = RB.joy;
			t.mouseDown = true;
			window.requestAnimationFrame(t.animFrame);
		});
		$("#joypad").on("touchstart", function (e) {
			e.preventDefault();
			e.stopPropagation();
			var t = RB.joy;
			t.mouseDown = true;
			$("#joypad #pointer").attr("r", 40);
			window.requestAnimationFrame(t.animFrame);
		});
		$("#joypad").on("mouseup touchend", function (e) {
			var t = RB.joy;
			e.preventDefault();
			e.stopPropagation();
			t.mouseDown = false;
			t.centerSnap();
		});
		$("#joypad").on("mousemove", RB.joy.mouseMove);
		$("#joypad").on("touchmove", RB.joy.touchMove);
		// Keyboard events
		$(window).on("keydown", RB.joy.keyDown);
		$(window).on("keyup", RB.joy.keyUp);
		// Gamepad support
		$(window).on("gamepadconnected", function (e) {
			// the event includes a gamepad object
			var gamepad = e.gamepad,
				t = RB.joy;
			// register the gamepad for reference later
			t.gamepads[gamepad.index] = gamepad;
			// and increase the count of connected gamepads
			t.gpcount += 1;
			// start running the animation frames
			// since we'll need to poll the gamepad data every frame
			window.requestAnimationFrame(t.animFrame);
			console.log("Connected:")
			console.log(gamepad);
			// stop the polling for new gamepads, if it's running
			if (t.gppoll) {
				clearInterval(t.gppoll);
			}
		});
		$(window).on("gamepaddisconnected", function (e) {
			var gamepad = e.gamepad;
			console.log("Disconnected:")
			console.log(gamepad);
			if (t.gamepads[gamepad.index]) {
				console.log("Deleting");
				delete RB.joy.gamepads[gamepad.index];
				t.gpcount -= 1;
				if (t.gpcount < 1) {
					// reset the polling for a gamepad
					t.gppoll = setInterval(t.pollGamepads, 1000);
				}
			}
		});
		// Gamepad polling to handle bug where Chrome never fires connected event for an already connected controller
		if (navigator.getGamepads) {
			t.gppoll = setInterval(t.pollGamepads, 1000);
		}
		// Network frame registration, 4 times per second
		setInterval(t.networkFrame, 250);
		// Autopilt switch
		$("#autopilotswitch").on("change", t.apSwitch);
		// Websocket client
		t.socket = new ReconnectingWebSocket("ws://" + window.location.hostname + (location.port ? ':'+location.port: '') + "/move");
	},
	apSwitch: function () {
		var ap = $(this).prop("checked");
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({autopilot: ap}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/move'
		});
	},
	// Manual scan for connected gamepads
	pollGamepads: function() {
		var i, gp,
			t = RB.joy,
			gamepads = navigator.getGamepads();
		for (i = 0; i < gamepads.length; i += 1) {
			gp = gamepads[i];
			if (gp && !t.gamepads[gp.index]) {
				// stop the polling for new gamepads
				if (t.gppoll) {
					console.log("Cleaing gamepad poll")
					clearInterval(t.gppoll);
				}
				t.gamepads[gp.index] = gp;
				t.gpcount += 1;
				window.requestAnimationFrame(t.animFrame);
				console.log("Gamepad connected at index " + gp.index + ": " + gp.id + ". It has " + gp.buttons.length + " buttons and " + gp.axes.length + " axes.");
			}
		}
	},
	// animation frame function
	// runs once per rendered frame while a gamepad, mouse or touch is active
	animFrame: function () {
		var i, k, gamepad, x, y,
			t = RB.joy,
			// re-query gamepads for new information, the only way to ge tupdated information in Chrome
			// this may become unecessary in the future if Chrome stops sucking at gamepad support
			new_gamepads = navigator.getGamepads ? navigator.getGamepads(): [];
		for (k in t.gamepads) {
			gamepad = new_gamepads[t.gamepads[k].index];
			break;
		}
		// only use gamepad axes data if we're not actuvely using the mouse or keyboard
		if (gamepad && !t.mouseDown && t.keyForce[0] == 0 && t.keyForce[1] == 0) {
			// deadzone support
			// the resting position of the axes on the gamepd is not necessarily exactly 0,0
			x = gamepad.axes[0];
			y = -gamepad.axes[1];
			// tweak 0.15 here to increase or descrease the deadzone
			// some cheap gamepads may need a higher number
			if (Math.abs(x) + Math.abs(y) < 0.15) {
				x = 0;
				y = 0;
			}
			t.coords = [x, y];
			for (i = 11; i < 15; i += 1) {
				if (gamepad.buttons[i].pressed) {
					$("#color-palette .swatch[data-val='" + t.gpColorMap[i] + "']").trigger("click");
				}
			}
		}
		// After possibly updating to most recent gamepad axes coordinates, render the cursor
		t.renderFromCoords();
		// only run the next animation frame if there are is a gamepad connected
		// or the mouse/touch event is still active
		if (t.mouseDown || t.gpcount > 0) {
			window.requestAnimationFrame(t.animFrame);
		}
	},
	// the network frame runs 4 times per second
	// it communicates the current coordinates to the server
	networkFrame: function () {
		var t = RB.joy,
			stopped = (t.coords[0] == 0 && t.coords[1] == 0);
		// don't spam stop commands
		if (stopped && t.sentStopLastFrame) {
			return;
		}
		// send coords to server
		t.socket.send(JSON.stringify({x: t.coords[0], y:t.coords[1]}));
		/*
		$.ajax({
			contentType: 'application/json',
			data: JSON.stringify({x: t.coords[0], y:t.coords[1]}),
			dataType: 'json',
			processData: false,
			type: 'POST',
			url: '/api/move'
		});*/
		t.sentStopLastFrame = stopped;
	},
	// Handle key presses
	// support WASD or arrows, why there are two key maps per direction
	keyDown: function (e) {
		var t = RB.joy;
		// prevent key down triggers if an input or textarea is the active element
		if (["textarea", "input"].indexOf(document.activeElement.tagName.toLowerCase()) > -1) {
			return;
		}
		switch (e.keyCode) {
			case 38:
			case 87:
				t.keyForce[1] = 1;
				break;
			case 40:
			case 83:
				t.keyForce[1] = -1;
				break;
			case 39:
			case 68:
				t.keyForce[0] = 1;
				break;
			case 37:
			case 65:
				t.keyForce[0] = -1;
				break;
		}
		t.coords = [t.keyForce[0], t.keyForce[1]];
		// force rendering as we are likely not running the animation frames
		t.renderFromCoords();
	},
	keyUp: function (e) {
		var t = RB.joy;
		switch (e.keyCode) {
			case 38:
			case 87:
				t.keyForce[1] = 0;
				break;
			case 40:
			case 83:
				t.keyForce[1] = 0;
				break;
			case 39:
			case 68:
				t.keyForce[0] = 0;
				break;
			case 37:
			case 65:
				t.keyForce[0] = 0;
				break;
		}
		t.coords = [t.keyForce[0], t.keyForce[1]];
		t.renderFromCoords();
	},
	renderFromCoords: function () {
		var x, y,
			t = RB.joy;
		$("svg #pointer").attr("cx", t.coords[0] * t.size + t.center).attr("cy", t.coords[1] * -t.size + t.center);
		// $("#coords").html(t.coords[0].toFixed(2) + " , " + t.coords[1].toFixed(2));

	},
	centerSnap: function () {
		var t = RB.joy;
		$("#joypad #pointer").attr("cx", t.center).attr("cy", t.center);
		// $("#coords").html(0 + " , " + 0);
		t.coords = [0,0];
	},
	mouseMove: function (e) {
		e.preventDefault();
		e.stopPropagation();
		if (!RB.joy.mouseDown) { return; }
		var os = $(this).offset(),
			x = e.clientX - os.left,
			y = e.clientY - os.top;
		RB.joy.moveBySVGCoords(x, y);
	},
	touchMove: function (e) {
		e.preventDefault();
		e.stopPropagation();
		var touch = e.touches[0],
			os = $(this).offset(),
			x = touch.clientX - os.left,
			y = touch.clientY - os.top;
		RB.joy.moveBySVGCoords(x, y);
	},
	moveBySVGCoords: function (x, y) {
		var t = RB.joy,
			max = t.size * 2 + t.padding;
		if (x > max) {
			x = max;
		} else if (x < t.padding) {
			x = t.padding;
		}
		if (y > max) {
			y = max;
		} else if (y < t.padding) {
			y = t.padding;
		}
		x = (x - t.center) / t.size;
		y = (y - t.center) / -t.size;
		t.coords = [x,y];
	}
}

$(document).ready(RB.joy.init);
}());
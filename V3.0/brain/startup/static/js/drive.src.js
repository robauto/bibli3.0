// Global RB object, protected from redeclaration by other potential modules
if (typeof RB === "undefined") {
  var RB = {};
}

(function() {
  "use strict";

  RB.drive = {
    // whether the mouse/touchpad is pressed, or a touch is happening
    mouseDown: false,
    //wether keyDown
    iskeydown: false,
    // current coordianges of the cursor
    coords: [0, 0],
    //keyForce: [0, 0],
    keyForce: 0,

    init: function() {
      var t = RB.drive;
      // Keyboard events
      $(window).on("keydown", RB.drive.keyDown);
      $(window).on("keyup", RB.drive.keyUp);
      $('.drive_btns').on("mousedown", RB.drive.mousedown);
      $('.drive_btns').on("mouseup", RB.drive.mouseup);
    },
    mousedown: function(e) {
      var t = RB.drive;
      //e.preventDefault();
      switch (this.id) {
        case "drive_up":
          t.keyForce = 1;
          break;
        case "drive_down":
          t.keyForce = 2;
          break;
        case "drive_right":
          t.keyForce = 3;
          break;
        case "drive_left":
          t.keyForce = 4;
          break;
        default:
          break;
      }
      //console.log(t.keyForce);
      t.coords[0] = t.keyForce;
      if (!t.iskeydown) {
        $.ajax({
          contentType: 'application/json',
          data: JSON.stringify({
              x: t.coords[0]
            }
            //x: t.coords[0],
            //y: t.coords[1]
          ),
          dataType: 'json',
          processData: false,
          type: 'POST',
          url: '/api/move'
        });
      }
      t.iskeydown = true;
    },
    mouseup: function(e) {
      var t = RB.drive;
      //e.preventDefault();
      switch (this.id) {
        case "drive_up":
          t.keyForce = 0;
          break;
        case "drive_down":
          t.keyForce = 0;
          break;
        case "drive_right":
          t.keyForce = 0;
          break;
        case "drive_left":
          t.keyForce = 0;
          break;
        default:
          break;
      }
      t.coords[0] = t.keyForce;
      $.ajax({
        contentType: 'application/json',
        data: JSON.stringify({
            x: t.coords[0]
          }
          //x: t.coords[0],
          //y: t.coords[1]
        ),
        dataType: 'json',
        processData: false,
        type: 'POST',
        url: '/api/move'
      });
      t.iskeydown = false;
    },
    keyDown: function(e) {
      var t = RB.drive;
      // prevent key down triggers if an input or textarea is the active element
      if (["textarea", "input"].indexOf(document.activeElement.tagName.toLowerCase()) > -1) {
        return;
      }
      switch (e.keyCode) {
        case 38:
        case 87:
          t.keyForce = 1;
          break;
        case 40:
        case 83:
          t.keyForce = 2;
          break;
        case 39:
        case 68:
          t.keyForce = 3;
          break;
        case 37:
        case 65:
          t.keyForce = 4;
          break;
      }
      t.coords[0] = t.keyForce;
      if (!t.iskeydown) {
        $.ajax({
          contentType: 'application/json',
          data: JSON.stringify({
              x: t.coords[0]
            }
            //x: t.coords[0],
            //y: t.coords[1]
          ),
          dataType: 'json',
          processData: false,
          type: 'POST',
          url: '/api/move'
        });
      }
      t.iskeydown = true;
      // force rendering as we are likely not running the animation frames
      //t.renderFromCoords();
      //t.networkFrame();
    },
    keyUp: function(e) {
      var t = RB.drive;
      switch (e.keyCode) {
        case 38:
        case 87:
          t.keyForce = 0;
          break;
        case 40:
        case 83:
          t.keyForce = 0;
          break;
        case 39:
        case 68:
          t.keyForce = 0;
          break;
        case 37:
        case 65:
          t.keyForce = 0;
          break;
      }
      t.coords[0] = t.keyForce;
      $.ajax({
        contentType: 'application/json',
        data: JSON.stringify({
            x: t.coords[0]
          }
          //x: t.coords[0],
          //y: t.coords[1]
        ),
        dataType: 'json',
        processData: false,
        type: 'POST',
        url: '/api/move'
      });
      t.iskeydown = false;
    }
  }
  $(document).ready(RB.drive.init);
}());
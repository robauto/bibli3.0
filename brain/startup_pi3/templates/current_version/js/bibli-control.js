/* Bibli control panel, by Wei, 2/17/2016  */

//

function Button_onclick(direction) {
    $.ajax({
        url: '/' + direction
    });
}

function Button_movie(movie) {
    $.ajax({
        url: '/movie/' + movie
    });
}

function Button_music(music) {
    $.ajax({
        url: '/music/' + music
    });
}

/*
function vdo_stm(vds) {
    var vdsm = document.getElementById("vdostream");
    if (vds == 1) {
        vdsm.src = "{{ url_for('video_feed') }}";
    } else if (vds == 0) {
        vdsm.src = "{{ url_for('static', filename='img/biblicam.jpg') }}";
        //for local test
        //vdsm.src = "img/biblicam.jpg";
    }
}

function Button_desktop(dstp) {
    $.ajax({
        url: '/desktop/' + dstp
    });
}
*/

function keydrive(){
var keyevent = [99, 99];
$("#keyctrl").keydown(function(event) {

    keyevent[0] = keyevent[1];
    keyevent[1] = event.which;
    //console.log(keyevent);
    if (keyevent[0] != keyevent[1]) {
        //console.log('different');
        if (event.which == 87) {
            Button_onclick('f')
        } else if (event.which == 65) {
            Button_onclick('l')
        } else if (event.which == 68) {
            Button_onclick('r')
        } else if (event.which == 83) {
            Button_onclick('b')
        } else if (event.which == 69) {
            Button_onclick('s')
        } else if (event.which == 86) {
            Button_onclick('v')
        }
    }
});
$("#keyctrl").keyup(function(event) {
    keyevent = [99, 99];
    if (event.which == 87) {
        Button_onclick('s')
    } else if (event.which == 65) {
        Button_onclick('s')
    } else if (event.which == 68) {
        Button_onclick('s')
    } else if (event.which == 83) {
        Button_onclick('s')
    } else if (event.which == 69) {
        Button_onclick('s')
    } else if (event.which == 86) {
        Button_onclick('v')
    }

});
}


function Button_saysth() {
    var msg = document.getElementById("Biblisay").value;
    $.ajax({
        url: '/saysth/' + msg
    });
}


function checkVtxt() {
    var checkText = $("#video_sl").find("option:selected").text();
    console.log(checkText);
    $.ajax({
        url: '/videos/' + checkText
    });
}

function checkMtxt() {
    var checkText = $("#sound_sl").find("option:selected").text();
    console.log(checkText);
    $.ajax({
        url: '/sounds/' + checkText
    });
}

function checkStxt() {
    var checkText = $("#story_sl").find("option:selected").text();
    console.log(checkText);
    $.ajax({
        url: '/stories/' + checkText
    });
}

function Button_video(vdoctl) {
    $.ajax({
        url: '/videoctrl/' + vdoctl
    });
}

function Button_sound(sndctl) {
    $.ajax({
        url: '/soundctrl/' + sndctl
    });
}

function Button_story(styctl) {
    $.ajax({
        url: '/storytrl/' + styctl
    });
}

function Button_jasper(jsp) {
    $.ajax({
        url: '/jasper/' + jsp
    });
}

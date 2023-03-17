let newMsgBtnStatus = false;
let msgRoom = false;

// 메시지 검색창 끄기
function msgSearchBoxClose() {
    $('.msg-user-search-box').remove();
    $('.msg-user-search-box-close').remove();
    var msgBox = $('.msg-content-box');
    msgBox.css("display", "block");
    newMsgBtnStatus = false;
}

function testaaa() {

    $.ajax({
        url: "/ajaxLoadChatRoom",
        data: {
            "userNo": $("#userNo").val()
        },
        type:"post",
        dataType: "json",
        success: function (data) {
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {

                }
            }
        }
    });
}

$(".que").click(function () {
    $(this).next(".anw").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".anw").siblings(".anw").slideUp(300); // 1개씩 펼치기
});
//  포인트 잔량 정규 표현식
var pointVal = document.querySelector(".point-val").value;
document.querySelector(".point-val-re").innerHTML = pointVal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");

// 포인트 충전 내역 정규표현식
var pointVal2 = document.querySelectorAll(".pointRecord-val");
for (var a = 0; a < pointVal2.length; a++) {
    document.querySelectorAll(".pointRecord-val-re")[a].innerHTML = pointVal2[a].value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + " Mango";
}

function modal(id) {
    var zIndex = 9999;
    var modal = document.getElementById(id);

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('#modal_close_btn').addEventListener('click', function () {
        bg.remove();
        modal.style.display = 'none';
    });
    // modal.querySelector('#modal_close_btn-2').addEventListener('click', function () {
    //     bg.remove();
    //     modal.style.display = 'none';
    // });

    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,

        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)'
    });
}

// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function (styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};

document.getElementById('popup_open_btn').addEventListener('click', function () {
    // 모달창 띄우기
    modal('my_modal');
    $('#point-add-input').val('');
});
document.getElementById('popup_open_btn-2').addEventListener('click', function () {
    // 모달창 띄우기
    modal('my_modal-2');
    $('#point-refund-input').val('');

});

function pointAddFunc() {
    let pointData = {
        "userNo": parseInt($('#userNo').val()),
        "pointVal": parseInt($('#point-add-input').val()),
    };
    if (pointData.pointVal < 0) {
        pointData = null;
    }
    $.ajax(
        {
            type: "POST",
            url: "/ajaxAddPoint",
            data: pointData,
            success: function (data) {
                $('.point-val-re').text(data.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
            },
            error: function () {
                alert("올바른 값을 입력해주세요.");
            }
        }
    )
    $('#modal_close_btn').trigger("click");
}

function pointRefundFunc() {
    let pointData = {
        "userNo": parseInt($('#userNo').val()),
        "pointVal": parseInt($('#point-refund-input').val()),
        "pointCurrentVal": parseInt($('#point-val-current').val())
    };
    if (pointData.pointVal > pointData.pointCurrentVal) {
        pointData = null;
    }
    $.ajax(
        {
            type: "POST",
            url: "/ajaxRefundPoint",
            data: pointData,
            success: function (data) {
                $('.point-val-re').text(data.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
            },
            error: function (request, status, error) {
                if (request.status === 400) {
                    alert("올바른 값을 입력해주세요.");
                } else if (request.status === 500) {
                    alert("현재 Mango 보다 작은 값을 입력해주세요.");
                }

            }
        }
    )
    $('.add-btn')[3].click();

}

const msgBtn = $('#msg-btn');
const msgBox = $('#msg-box');
const msgObj = {"userNo": parseInt($('#userNo').val())}

function msgToggle() {
    msgBox.toggle('active');
}

window.onload = function () {
    $.ajax(
        {
            type: "POST",
            url: "/ajaxMessage",
            data: msgObj,
            success: function (data) {
                let msgList = JSON.parse(data);
                if (msgList.length > 0) {
                    for (var i = 0; i < msgList.length; i++) {
                        $('#msg-ul').append("<button type='button' style='border:none; background-color: transparent' onclick='msg_user(" + i + ")'><li>" + msgList[i].userName + "</li> <input type='hidden' value='" + msgList[i].sendUserNo + "' id='send_user_" + i + "'></button><br>");
                    }
                } else {
                    // $('#msg-ul').append("<li>메시지가 없습니다.</li>");
                }
                console.log([0].msgContent);

            },
            error: function (request, status, error) {
                alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
            }
        }
    )
}

$('.new-msg-user-list').click(function () {
    if (newMsgBtnStatus === true) {
        return;
    }
    $('.msg-user-list-box').prepend("<button class='msg-user-search-box-close' onclick='msgSearchBoxClose()' value='<'></button>")
    var msgBox = $('.msg-content-box');
    msgBox.css("display", "none");
    msgBox.after('<div class="msg-user-search-box"><input type="text" class="msg-user-search-bar"  placeholder="검색 할 아이디를 입력해주세요..." onkeyup="msgUserSearchFunc()"></div>');
    var msgSearchBox = $('.msg-user-search-box');
    msgSearchBox.append("<ul class='msg-search-box-ul'></ul>");
    newMsgBtnStatus = true;
});

function msgUserSearchFunc() {
    var word = $('.msg-user-search-bar').val();
    $.ajax({
        url: "/ajaxMsgUserSearch",
        type: "POST",
        data: {
            "userId": word
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var str = '';
            if (data != null) {
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str += "<li onclick='chatStart(" + data[i].userNo + ")' class='msg-user-list-result-" + data[i].userNo + "'> " + data[i].userName + "<br><span class='msg-user-list-result-span'>@" + data[i].userId + "</span></li>";
                    }
                    $('.msg-search-box-ul').html(str);
                }
            } else {
                $('.msg-search-box-ul').html(str);
            }
        },
        error: function (request, status, error) {
            console.log("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    });
}

function chatStart(userNo) {
    msgSearchBoxClose();
    if (msgRoom === true) {
        return;
    }
    $('.msg-user-list-result-' + userNo).css('background-color', '#F2F3F5');
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/ajaxMsgUserAdd",
        data: {
            "userNo": userNo
        },
        success: function (data) {
            console.log(data);
            $('.msg-user-list-ul').append("<li onclick='chatStart(" + data[0].userNo + ")' class='msg-user-list-result-" + data[0].userNo + "'> " + data[0].userName + "<br><span class='msg-user-list-result-span'>@" + data[0].userId + "</span><input type='hidden' value='" + data[0].userNo + "' id='receiveUser'> </li>");

        }
    })
    newMsgBtnStatus = false;
}


// function msg_user(i) {
//     const test = {
//         "sendUserNo" : $('#send_user_1').val(),
//         "receiveUserNo": $('#userNo').val()
//     };
//     $.ajax(
//         {
//             type: "POST",
//             url: "/ajaxMsgUser",
//             data: test,
//             success : function (data) {
//                 console.log(JSON.parse(data));
//             },
//             error: function (request, status, error) {
//                 alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
//             }
//         }
//     )
// }

function msg_btn() {
    var msgData = {
        "msgContent": $('.msg-input').val(),
        "sendUser": $('#userNo').val(),
        "receiveUser": $('#receiveUser').val()
    }
    $.ajax({
        url: "/ajaxMsgSend",
        type: "post",
        data: msgData,
        datatype: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (request, status, error) {
            alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    })
}


// 음악 정보 api 로 가져오기

test = $('#likeTag').val();
var request = new XMLHttpRequest();
request.open('GET', 'https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=45ac2dc0a0b1bad3966314cc210f8c49&artist=cher&track=believe&format=json');
request.send();
request.onload = function () {
    var songInfo = JSON.parse(request.response).track;
    document.querySelector("#songName").innerHTML = songInfo.name;
    document.querySelector("#songArtist").innerHTML = songInfo.artist.name;
}
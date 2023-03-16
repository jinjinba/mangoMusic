$(".que").click(function() {
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
        "userNo": parseInt($('.userNo').val()),
        "pointVal": parseInt($('#point-add-input').val()),
    };
    if(pointData.pointVal < 0){
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
            error : function(){
                alert("올바른 값을 입력해주세요.");
            }
        }
    )
    $('#modal_close_btn').trigger("click");
}

function pointRefundFunc() {
    let pointData = {
        "userNo": parseInt($('.userNo').val()),
        "pointVal": parseInt($('#point-refund-input').val()),
        "pointCurrentVal" : parseInt($('#point-val-current').val())
    };
    if(pointData.pointVal > pointData.pointCurrentVal){
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
            error: function(request, status, error){
                if(request.status === 400){
                    alert("올바른 값을 입력해주세요.");
                }else if(request.status === 500){
                    alert("현재 Mango 보다 작은 값을 입력해주세요.");
                }

            }
        }
    )
    $('.add-btn')[3].click();

}
const msgBtn = $('#msg-btn');
const msgBox = $('#msg-box');
const msgObj = { "userNo": parseInt($('.userNo').val()) }
function msgToggle(){
   msgBox.toggle('active');
}
window.onload = function(){
    $.ajax(
        {
            type:"POST",
            url: "/ajaxMessage",
            data: msgObj,
            success:function(data){
                let msgList = JSON.parse(data);
                if(msgList.length > 0){
                    for(var i = 0; i< msgList.length; i++){
                        $('#msg-ul').append("<button type='button' style='border:none; background-color: transparent' onclick='msg_user"+i+"()'><li>" + msgList[i].userName + "</li> <input type='hidden' value='"+msgList[i].userNoSend+"'></button>");
                    }
                }else {
                    $('#msg-ul').append("<li>메시지가 없습니다.</li>");
                }
                console.log([0].msgContent);

            },
            error : function(request, status, error ){
                alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
            }
        }
    )
}

function msg_user0(){
    alert("");
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
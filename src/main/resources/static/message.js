const msgBox = $('#msg-box');

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

$(document).ready(function () {
    loadingChatRoom();
});

function loadingChatRoom() {
    $.ajax({
        url: "/ajaxLoadChatRoom",
        data: {
            "userNo": $("#userNo").val()
        },
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var str = "";
                    str += "<li onclick='selectChatRoom(" + data[i].msgRoom + ")' class='msg-user-list-li' id='msg-user-list-li-"+data[i].msgRoom+"'>" + data[i].userName + "<p>@"+data[i].userId+"</p><input type='hidden' value='"+data[i].msgRoom+"' id='msg-user-list-id-"+data[i].msgRoom+"'></li>";
                    $('.msg-user-list-ul').append(str);
                    $('.msg-input-box').remove();
                    var str2 = "";
                    str2 += "<div class='msg-input-box' style='display: inline-block; flex-direction: row;float:left;'>";
                    str2 +=     "<div style=' height: 100%; width: 495px; background-color: #F2F3F5;  float:left;'>";
                    str2 +=    "</div>";
                    str2 +="</div>";

                    $('.msg-area').append(str2);
                }
            }
        }
    });
}

var msgToggleStatus = false;
function msgToggle() {
    var msgToggle = $('.msg-toggle-btn');
    var modifyToggle = $('.modify-btn');
    if(!msgToggleStatus){
        msgToggle.css("top","230px")
        // msgToggle.css("right","200px")
        //     modifyToggle.css("position","fixed");
        modifyToggle.css("left","700px");
        msgToggleStatus = true;
    }else {
        msgToggle.css("top","0px");
        modifyToggle.css("left","0px");
        msgToggleStatus = false;
    }
    msgBox.toggle(0,'show');
}



// // 채팅 시작
// function chatStart(otherUserNo) {
//     if(userNo == $('#msg-user-list-id-'+otherUserNo).val()){
//         $('.msg-user-list-li').removeAttr('style');
//         $('#msg-user-list-li-'+otherUserNo).css("background-color","#F2F3F5");
//         msgSearchBoxClose();
//         addMsgInputBox(otherUserNo);
//         selectChatRoom(otherUserNo);
//         $.ajax({
//            type:"post",
//            dataType:"json",
//            url:"/ajaxSelectChatRoom",
//            success:function(data){
//                var str = "";
//                if (data.length > 0) {
//                    str += "<li onclick='selectChatRoom(R."+otherUserNo+")' class='msg-user-list-li' id='msg-user-list-li-"+otherUserNo+"'>"+data[0].userName + "<p>@"+data[0].userId+"</p></li>";
//                    str+="<input type='hidden' value='"+data[0].userNo+"' id='otherUserNo"+data[0].userNo+"'>";
//                    $('.msg-user-list-ul').prepend(str);
//                    $('#msg-user-list-li-'+userNo).css("background-color","#F2F3F5");
//                }
//            }
//         });
//     }else {
//         $('.msg-user-list-li').removeAttr('style');
//         msgSearchBoxClose();
//         addMsgInputBox(otherUserNo);
//         $('#receiveUserNo').remove();
//         $('.receive-msg-area-container').remove();
//         $('.send-msg-area-container').remove();
//         $('.msg-user-list-result-' + otherUserNo).css('background-color', '#F2F3F5');
//         $.ajax({
//             type: "post",
//             dataType: "json",
//             url: "/ajaxMsgUserAdd",
//             data: {
//                 "userNo": otherUserNo
//             },
//             success: function (data) {
//                 var str = "";
//                 if (data.length > 0) {
//                     str += "<li onclick='selectChatRoom("+userNo+")' class='msg-user-list-li' id='msg-user-list-li-"+userNo+"'>"+data[0].userName + "<p>@"+data[0].userId+"</p></li>";
//                     str+="<input type='hidden' value='"+data[0].userNo+"' id='receiveUserNo"+data[0].userNo+"'>";
//                     $('.msg-user-list-ul').prepend(str);
//                     $('#msg-user-list-li-'+userNo).css("background-color","#F2F3F5");
//                 }
//             }
//         });
//         newMsgBtnStatus = false;
//     }
// }
//
//
// let newMsgBtnStatus = false;
// let selectMsgRoom = false;

//
// // 메시지 입력 버튼
// function msgBtn() {
//     var msgData = {
//         "msgContent": $('.msg-input').val(),
//         "sendUser": $('#userNo').val(),
//         "receiveUser": $('#receiveUserNo').val()
//     }
//     $.ajax({
//         url: "/ajaxMsgSend",
//         type: "post",
//         data: msgData,
//         datatype: "json",
//         success: function (data) {
//             $('.msg-input').val("");
//             $('.msg-content-box').scrollTop($('.msg-content-box')[0].scrollHeight);
//         },
//         error: function (request, status, error) {
//             alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
//         }
//     });
// }
//
//
// // 메시지 검색창 끄기
// function msgSearchBoxClose() {
//     $('.msg-user-search-box').remove();
//     $('.msg-user-search-box-close').remove();
//     var msgBox = $('.msg-content-box');
//     msgBox.css("display", "block");
//     newMsgBtnStatus = false;
// }
//
//

// function addMsgInputBox(i){
//     $('.msg-input-box').remove();
//     var str2 = "";
//     str2 += "<div class='msg-input-box' style='display: inline-block; flex-direction: row;float:left;'>";
//     str2 +=     "<div style='display:flex; height: 100%; width: 495px; background-color: #F2F3F5;  float:left;'>";
//     str2 +=     "<div>";
//     str2 +=        "<input type='text' class='msg-input' onKeyPress='javascript:if(event.keyCode==13) {msgBtn()}'>";
//     str2 +=            "<button type='button' class='msg-input-btn' onClick='msgBtn()'></button>";
//     str2 +=     "</div>"
//     str2 +=     "<div><button class='msg-room-close' onclick='chatRemove("+i+")'>나가기</button></div>";
//     str2 +=    "</div>";
//     str2 +="</div>";
//     $('.msg-area').append(str2);
// }
// var timer;
// var selectChatRoomStatus = false;
//
// function selectChatRoom(i) {
//     if(selectChatRoomStatus){
//         clearInterval(timer);
//     }
//     $('.msg-user-list-li').removeAttr('style');
//     $('#msg-user-list-li-'+i).css("background-color","#F2F3F5");
//     timer = setInterval(function () {
//     $.ajax({
//         url: "/ajaxSelectChatRoom",
//         type: "post",
//         dataType: "json",
//         data: {
//             "userNo": i,
//             "userNo2" : $('#userNo').val()
//         },
//         async:false,
//         success: function (data) {
//             console.log(data);
//             $('#receiveUserNo').remove();
//
//             $('.receive-msg-area-container').remove();
//             $('.send-msg-area-container').remove();
//             var str = "";
//             for (var a = 0; a < data.length; a++) {
//                 if (data[a].sendUserNo === i) {
//                     str += "<div class='receive-msg-area-container'>";
//                     str += "<div class='receive-msg-area'>";
//                     str += "<ul>";
//                     str += "<li>" + data[a].msgContent + "</li>";
//                     str += "<span class='receive-msg-p'>"+moment(data[a].msgDate).format("MM-DD HH:mm")+"</span><br>";
//                     str += "</ul>";
//                     str += "</div>";
//                     str += "</div>";
//                 } else {
//                     str += "<div class='send-msg-area-container'>";
//                     str += "<div class='send-msg-area'>";
//                     str += "<ul>";
//                     str += "<span class='receive-msg-p'>"+moment(data[a].msgDate).format("MM-DD HH:mm")+"</span><li>" + data[a].msgContent + "</li><br>";
//                     str += "</ul>";
//                     str += "</div>";
//                     str += "</div>";
//                 }
//             }
//             str+="<input type='hidden' value='"+i+"' id='receiveUserNo'>";
//             let msgBox = $('.msg-box');
//             msgBox.append(str);
//             selectChatRoomStatus = true;
//         },
//         error: function (request, status, error) {
//             console.log("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
//         }
//     })
//     }, 1000);
//     addMsgInputBox(i);
//     $('.msg-content-box').scrollTop($('.msg-content-box')[0].scrollHeight);
//
// }
//
// function chatRemove(i){
//     if(confirm("방에서 나가시겠습니까?")){
//         $.ajax({
//             url : "/ajaxChatRemove",
//             type : "post",
//             data : {
//                 "roomNo" : i
//             },
//             success : function(data){
//                 $('.msg-user-list-li-'+i).remove();
//                 $('.msg-box').remove();
//             },
//             error(data){
//                 alert("삭제실패");
//             }
//         });
//     }
// }
//

//
// function msgUserSearchFunc() {
//     var word = $('.msg-user-search-bar').val();
//     $.ajax({
//         url: "/ajaxMsgUserSearch",
//         type: "POST",
//         data: {
//             "userId": word
//         },
//         dataType: 'json',
//         success: function (data) {
//             console.log(data);
//             var str = '';
//             if (data != null) {
//                 if (data.length > 0) {
//                     for (var i = 0; i < data.length; i++) {
//                         str += "<li onclick='chatStart(" + data[i].userNo + ")' class='msg-user-list-result-" + data[i].userNo + "'> " + data[i].userName + "<br><span class='msg-user-list-result-span'>@" + data[i].userId + "</span></li>";
//                     }
//                     $('.msg-search-box-ul').html(str);
//                 }
//             } else {
//                 $('.msg-search-box-ul').html(str);
//             }
//         },
//         error: function (request, status, error) {
//             console.log("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
//         }
//     });
// }

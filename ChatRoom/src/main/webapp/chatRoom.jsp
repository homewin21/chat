<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<script src="static/js/jquery-3.3.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="static/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/css/room.css" />
<title>简单聊天室</title>
<script type="text/javascript">
window.onload=function(){
	// 创建WebSocket对象
	
	function $(id){
    return  document.getElementById(id);    
    }
	function formatDate(time){
	    var date = new Date(time);

	    var year = date.getFullYear(),
	        month = date.getMonth()+1,//月份是从0开始的
	        day = date.getDate(),
	        hour = date.getHours(),
	        min = date.getMinutes(),
	        sec = date.getSeconds();
	    var newTime = year + '-' +
	                (month < 10? '0' + month : month) + '-' +
	                (day < 10? '0' + day : day) + ' ' +
	                (hour < 10? '0' + hour : hour) + ':' +
	                (min < 10? '0' + min : min) + ':' +
	                (sec < 10? '0' + sec : sec);

	    return newTime;         
	}

	var webSocket = new WebSocket("ws://localhost:8080/MailTest/chat");
	var sendMsg = function() {
		var inputElement = document.getElementById('msg');

		if (inputElement.value == '')
			alert("输入内容不为空");
		else {
			// 发送消息
			webSocket.send("   " + inputElement.value);
			// 清空单行文本框
			inputElement.value = "";
		}
	}
	var send = function(event) {
		if (event.keyCode == 13) {
			sendMsg();
		}
	};
	webSocket.onopen = function() {
		var user_online=document.getElementById('user_online');
		webSocket.send("${name}");
		 user_online.innerHTML="";  
		document.getElementById('msg').onkeydown = send;
		document.getElementById('sendBn').onclick = sendMsg;
	};
	// 为onmessage事件绑定监听器，接收消息
	webSocket.onmessage = function(event) {

	  
		var show = document.getElementById('show');
		var user_online=document.getElementById('user_online');
	
		// 接收、并显示消息
		if (event.data.indexOf("djalsdalsi1213123jlasd") != -1) {
			 
			 var str =event.data.toString();
			 str = str.replace("djalsdalsi1213123jlasd", "");
			
			
			user_online.innerHTML+=str+ "<br/>"; 
		}else if (event.data.indexOf("wjdlasjdajsdaheflajdf")!=-1) {
			user_online.innerHTML="";
		}else{
			show.innerHTML += formatDate(new Date().getTime()) + "<br/>" + event.data + "<br/>"; 
		}
		
		
		//让聊天框滚动条始终显示新消息
		show.scrollTop = show.scrollHeight;
	}

	webSocket.onclose = function() {


		document.getElementById('msg').onkeydown = null;
		document.getElementById('sendBn').onclick = null;
		//console.log('WebSocket已经被关闭。');
	};

    
}
</script>

</head>
<body>
<div id="main">
            <div class="box">
                <!-- 聊天框 -->
                <div id="show">

                </div>
                <!-- 显示聊天室里在线用户 -->
                <div id="user">
                <div>
                    <span>【在线用户列表】</span>
                </div>
                <div id="user_online">

                </div>
                </div>
            </div>
        <div id="input">
            <input type="text"  size="40" id="msg" name="msg" placeholder="输入聊天内容..." > 
         <span>
            <button type="button"  id="sendBn"
                name="sendBn"><i class="iconfont">&#xe643;</i>发送</button>
        </span>
        </div>
        </div>


</body>
</html>

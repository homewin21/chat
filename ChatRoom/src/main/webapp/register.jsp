<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="static/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/css/register.css" />
<script type="text/javascript" src="static/js/jquery-3.3.1.js"></script>
<title>注册</title>
<script type="text/javascript">
var validate='<%=request.getAttribute("validate")%>' ;
var login='<%=request.getAttribute("login")%>' ;
if (validate=='no') {
	alert("请前往邮箱进行激活！");
}else if(login=='no') {
	alert('密码不正确！');
}

	$(function() {
		
		$("#username").blur(function() {
			var username = $("#username").val();
			var checkname1 = $(".checkname1");//错误
			var checkname2 = $(".checkname2");//正确
			var nameMsg=$(".nameMsg");
			if (username == '') {
				
				checkname2.html("");
				checkname1.html("&#xe61f;");
			    nameMsg.html("用户名不可以为空！");
			    $("#submit").attr('disabled',true);
			} else {
				$.ajax({
					type : "GET",
					url : "register?action=check&username=" + username,
					async : true,
					success : function(data) {
						
						if (data == '1') {
							nameMsg.html("该用户名已经被人使用！");
							checkname2.html("");
							checkname1.html("&#xe61f;");
							$("#submit").attr('disabled',true);
						} else {
							checkname1.html("");
							checkname2.html("&#xe600;");
							nameMsg.html("");
							$("#submit").attr('disabled',false);
						}
					}
				})
			}
		})
		$("#password").blur(function(){
			var password=$("#password");
			var checkname3 = $(".checkname3");//错误
            var checkname4 = $(".checkname4");//正确
            var pwdMsg=$(".pwdMsg");
            if (password.val()=='') {
                pwdMsg.html("密码不为空！");
            	checkname4.html("");
                checkname3.html("&#xe61f;");
                $("#submit").attr('disabled',true);
			}else {
				if (/^[0-9a-zA-Z_]{6,15}$/.test(password.val())) {
					checkname3.html("");
                    checkname4.html("&#xe600;");
                    pwdMsg.html("");
                    $("#submit").attr('disabled',false);
				}else {
					checkname4.html("");
	                checkname3.html("&#xe61f;");
	                pwdMsg.html("密码长度不正确！");
	                $("#submit").attr('disabled',true);
				}
			}
		})
		$("#email").blur(function() {
			var email=$("#email").val();
			var checkname5 = $(".checkname5");//错误
            var checkname6 = $(".checkname6");//正确
            var emailMsg=$(".emailMsg");
			var reg=/\w+[@]{1}\w+[.]\w+/;
			if (email=='') {
				checkname6.html("");
                checkname5.html("&#xe61f;");
                emailMsg.html("邮箱地址不可为空");
                $("#submit").attr('disabled',true);
			}else {
				if (reg.test(email)) {
					checkname5.html("");
                    checkname6.html("&#xe600;");
                    emailMsg.html("");
                    $("#submit").attr('disabled',false);
	            }else {
	            	checkname6.html("");
                    checkname5.html("&#xe61f;");
                    emailMsg.html("填写正确的邮箱地址！");
                    $("#submit").attr('disabled',true);
				}
			}
			
		})
		

	})
</script>
</head>

<body>

	<form method="POST"
		action="${pageContext.request.contextPath }/register">
		<div id="main">
			<div class="login">
				<div class="title">
					<span>用户注册</span>
				</div>
				<div class="message">
					<div class="username">
						<i class="iconfont">&#xe604;</i> <input type="text"
							name="username" id="username" />
							 <i class="iconfont checkname1"></i>
						<i class="iconfont checkname2"></i>
					</div>
					<div class="nameMsg">
                        
                    </div>
					<div class="password">
						<i class="iconfont">&#xe65e;</i> <input type="password"
							name="password" id="password" /> 
							 <i class="iconfont checkname3"></i>
                        <i class="iconfont checkname4"></i>
					</div>
					<div class="pwdMsg">
                        
                    </div>
					<div class="email">
						<i class="iconfont">&#xe501;</i> <input type="email" name="email"
							id="email" /> 
							 <i class="iconfont checkname5"></i>
                        <i class="iconfont checkname6"></i>
					</div>
					<div class="emailMsg">
                        
                        
                    </div>
					<div class="submit">
						<input type="submit" value="注   册"  id="submit"/>
						<div class="visitor"></div>

					</div>
				</div>

			</div>
		</div>
	</form>
</body>
</html>

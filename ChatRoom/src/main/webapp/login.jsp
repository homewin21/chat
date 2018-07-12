<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <link rel="stylesheet" href="static/iconfont/iconfont.css" />
        <link rel="stylesheet" type="text/css" href="static/css/login.css" />
        <script type="text/javascript" src="static/js/jquery-3.3.1.js"></script>
<title>用户登陆</title>
<script type="text/javascript">
       $(function () {
    	   var validate ="${validate}";
    	   var login ="${login}";
    	   /* alert(login);
    	   alert(validate); */
    	   if (validate=='no') {
			alert("请前往验证你的邮箱");
		}else if (login=='no') {
			alert('密码不正确！');
		}
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
            }else {
            	$.ajax({
                    type : "GET",
                    url : "login?action=check&username=" + username,
                    async : true,
                    success : function(data) {
                    	
                        if (data == "0") {
                        	
                            nameMsg.html("该用户名不存在！");
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
		$("#password").blur(function() {
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
            	checkname3.html("");
                checkname4.html("&#xe600;");
                pwdMsg.html("");
                $("#submit").attr('disabled',false);
			}
		})
	})

</script>
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath }/login">
 <div id="main">
            <div class="login">
                <div class="title">
                    <span>用户登录</span>
                </div>
                <div class="message">
                    <div class="username">
                        <i class="iconfont">&#xe604;</i>
                        <input type="text" name="username" id="username" />
                         <i class="iconfont checkname1"></i>
                        <i class="iconfont checkname2"></i>
                    </div>
                    <div class="nameMsg">
                        
                    </div>
                    <div class="password">
                        <i class="iconfont">&#xe65e;</i>
                        <input type="password" name="password" id="password" />
<i class="iconfont checkname3"></i>
                        <i class="iconfont checkname4"></i>
                    </div>
                    <div class="pwdMsg">
                        
                    </div>
                    <div class="submit">
                        <input type="submit" value="登   录" id="submit" />
                        <div class="visitor">
                            <i class="iconfont">&#xe602;</i>
                            <span><a href="${pageContext.request.contextPath }/login">游客登录</a></span>
                            
                        </div>
<span><a href="register.jsp">注册</a></span>
                    </div>
                </div>

            </div>
        </div>

</form>
</body>
</html>
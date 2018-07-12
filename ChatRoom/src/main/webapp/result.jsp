<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="static/iconfont/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/css/result.css" />
<script type="text/javascript" src="static/js/jquery-3.3.1.js"></script>
<title>结果页</title>
<script type="text/javascript">
    $(function() {
    	   $("#home").click(function() {
    		   location.href="login.jsp";
		})	
	})
</script>
</head>
<body>
	<div id="main">
            <div class="login">
                <div class="msg">
                    <span>注册成功！请前往所填邮箱激活账号后登录！</span>
                    
                    </div>
                       
                    <i class="iconfont" id="home">&#xe6dd;</i>
                
                
            </div>
        </div>
</body>
</html>
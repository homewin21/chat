<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<style type="text/css">
#user{
    font-size:12px;
}
</style>
<script type="text/javascript">
window.onload=function(){
var arr = new Array();
var user = document.getElementById('user');

<%
List<String> list = (List)session.getAttribute("list");
for(String str : list){
%>
arr.push("<%=str%>");
<%
}
%>



for (var i = 0; i < arr.length; i++) {
	user.innerHTML+=arr[i]+"<br>";
}

}
</script>
</head>
<body>

    <div id="user">
        用户列表
    </div>

</body>
</html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>医院收费管理界面</title>
<!--    <script src="/js/jquery.js"></script>-->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    .div-inline1{ width:1300px; height:150px;background-image:url("/img/e.jpg");display:block}
    /*.div-inline2{ width:1300px; height:50px;background-image:url("/img/e.jpg");display:block;position:absolute;top:100px;left:29px;}*/
    .div-inline3{ width:1000px; height:800px;background-image:url("/img/e.jpg");display:block;position:absolute;top:170px;left:29px;}
    .div-inline4{ width:185px; height:150px;background-image:url("/img/e.jpg");display:block;position:absolute;top:0px;left:1329px;}
    .div-inline5{ width:480px; height:800px;background-image:url("/img/e.jpg");display:block;position:absolute;top:170px;left:1035px;}
    .div-inline6{ width:460px; height:200px;background-image:url("/img/qq.png");background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;display:block;position:absolute;top:100px;left:10px;}
    .div-inline7{ width:900px; height:400px;background-image:url("/img/r.jpg");display:block;position:absolute;top:100px;left:50px;}
    .div-inline8{ width:200px; height:300px;background-image:url("/img/zz.png");background-size:contain;display:block;position:absolute;top:50px;left:50px;}
    .div-inline9{ width:500px; height:300px;background-image:url("/img/e.jpg");background-size:contain;display:block;position:absolute;top:50px;right:100px;}
    .div-inline{display:inline;}
    /*button{
        width: 50px;
        height: 50px;
        border: 1px solid blue;
        background-color: blue;
        color: red;
        border-radius: 5px;
        -webkit-box-shadow: 2px 2px 2px gray;
        -moz-box-shadow: 2px 2px 2px gray ;
        box-shadow: 2px 2px 2px gray ;
    }*/
    button:hover{
        background-color: green;
        cursor: pointer;
    }
    .box{
        position: fixed;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.2);
        display: none;
    }
    .box1{
        width: 500px;
        height: 500px;
        position: fixed;left: 50%; top: 20%;
        margin-left: -250px;
        border: 1px solid #000000;
    }
    #testContainer {text-align: center;}
</style>
<body>
<div class="div-inline1"style="color: black; margin-left: 80px;font-size:50px;";>欢迎来到医疗门诊多角色权限管理系统</div>
  <img src='<%=basePath%>static/imgs/huanying.png' style="height: 600px;width: 1000px">
</body>
</html>

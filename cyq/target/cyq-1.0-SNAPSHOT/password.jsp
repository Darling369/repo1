<%--
  Created by IntelliJ IDEA.
  User: Darling
  Date: 2021/9/18
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <style type="text/css">
        html {
            width: 100%;
            height: 100%;
            background: transparent;
        }
        body {
            background: rgba(0, 0, 0, 0.5);
            width: 100%;
            height: 100%;
        }
        .body-class{
            background: #FFFFFF;
        }
        .modal{
            width: 300px;
            margin: 0 auto;
            position: relative;
            top: 50%; /*偏移*/
            background: #ffffff;
            border-radius: 5px;
        }
        .confirm{
            height: 50px;
            border-top: 1px solid #eee;
            display: flex;
        }
        .confirm>span{
            flex: 1;
            height: 50px;
            line-height: 50px;
            font-size: 16px;
            text-align: center;
        }
        .confirm>span:nth-child(1){
            color: red;
        }
        .confirm>span:nth-child(2){
            border-left: 1px solid #eee;
        }
        .title{
            text-align: center;
            border-bottom: 1px solid #eee;
            margin-bottom: 10px;
        }
        .title>p{
            height: 40px;
            line-height: 40px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;

        }
        .important{
            color: red;
        }
        .close-but:hover{
            opacity: 0.5;
        }
        .content{
            max-height: 500px;
            overflow-y:auto;
        }
        .content>span{
            padding: 0 10px 10px 10px;
        }
        .hidden{
            display: none
        }
    </style>
</head>
<body id="body">
<div class="modal" id="modal">
    <div class="title">
        <p class="important">提示</p>
    </div>
    <div class="content" align="center" >
        <div  id="msg" >你的密码是：${requestScope.password}</div>
        <br>
    </div>
    <div class="confirm" onclick="fnClose()">
        <span class="close-but">确认返回</span>
    </div>
</div>
<script type="text/javascript">
    let modalHeight = document.getElementById('modal').offsetHeight;
    document.getElementById('modal').style.marginTop=-(modalHeight/2)+"px";

    function fnClose(){
        document.getElementById('modal').setAttribute("class","hidden");
        document.getElementById('body').setAttribute("class","body-class");
        window.location.href='/demo1/demo.html';
    };
</script>
</body>
</html>
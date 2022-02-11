<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新用户注册</title>
    <!--  <script type="text/javascript" >-->
    <!--  function use(){-->
    <!--    try{-->
    <!--      x=document.getElementById("a").value;-->
    <!--      if(x==""||x==null) throw "非空";-->
    <!--      if(isNaN(x)) throw "必须是数字";-->
    <!--      if(x<10||x>10) throw "数字太小";-->
    <!--    }catch(err){-->
    <!--      document.getElementById("demo").innerHTML="提示："+err;-->
    <!--    }-->
    <!--  }-->
    <!--</script>-->
    <style type="text/css">
        html {
            width: 100%;
            height: 100%;
            background: transparent;
        }
        body {
            background-image: url("1608477862033.jpg");
            background-size: 100%;
            width: 100%;
            height: 100%;
        }
        .body-class{
            background:rgba(220,216,255,0.29);
        }
        .modal{
            width: 300px;
            margin: 0 auto;
            position: relative;
            top: 50%; /*偏移*/
            background:white;
            border-radius: 5px;
        }
        .confirm{
            height: 50px;
            /*border-top: 1px solid #eee;*/
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
        input{
            color: #ff0500;
            font:15px Arial;
        }
        .btn{
            font-size: 20px;
            position: relative;
            top: 60px;
            background-color:transparent;  /* 背景色透明 */
            border:0px;       /* 边框取消 */
        }
        .btn:hover{
            color: red;
            opacity: 0.5;
            cursor: pointer;
        }
        /*.remember{*/
        /*    position: relative;*/
        /*    font-size: 12px;*/
        /*    top: -20px;*/
        /*    left: 110px;*/
        /*    color: black;*/
        a{text-decoration:none}
        .p{
            position: relative;
            top: -30px;
            left: 100px;
            width: 170px;
            height: 10px;
        }
    </style>
</head>
<body id="body">
<div class="modal" id="modal">
    <div class="title">
        <p class="important">提示</p>
    </div>
    <div class="fu" >
        <div align="center">新用户注册</div>
        <form action="/cyq/newuserServlet" method="post" align="center" >
            <div class="yhm">用户名：
                <input type="text" placeholder="用户名为十位纯数字" name="username"
                       required="required" id="a"
                       oninput="this.value=this.value.replace(/[^\d]/g,'')" maxlength="10">
                <!--             onkeyup="this.value=this.value.replace(/\D/g,'')">-->
                <br>
            </div>
            <div class="mima">密码：&nbsp&nbsp&nbsp
                <input type="password" placeholder="请输入密码" name="password"required="required" id="b" ><br>
            </div>
            <div class="nicheng">昵称：&nbsp&nbsp&nbsp
                <input type="name" placeholder="请输入昵称" name="name"required="required" id="c" ><br>
            </div>
            <div class="email">邮箱：&nbsp&nbsp&nbsp
                <input type="email" placeholder="请输入邮箱" name="email"required="required" id="d" ><br>
            </div>



            <input type="submit" value="注册" class="btn" name="btnSubmit" id="btnSubmit" style=" top: 40px;">
        </form>
        <div class="alert alert-warning alert-dismissible" role="alert" style="Aposition: relative; top: -46px;right: -95px" >
            <button type="button" class="close" data-dismiss="alert" style="display: none">
                <span>&times;</span></button>
            <div class="p">
                <strong style="color: red" >${login_msg}</strong>
            </div>
        </div>
        <div class="confirm"  >
        </div>
        <p id = "demo"></p>
    </div>
</div>
<script>
    function refreshCode(){
        var vcode =  document.getElementById(`vcode`);
        vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time="+ new Date().getTime();add.html
    }
</script>
<script type="text/javascript">
    let modalHeight = document.getElementById('modal').offsetHeight;
    document.getElementById('modal').style.marginTop=-(modalHeight/2)+"px";

    function fnClose(){
        document.getElementById('modal').setAttribute("class","hidden");
        document.getElementById('body').setAttribute("class","body-class");
        window.location.href='/demo1/demo.jsp'
        ;}
</script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Darling
  Date: 2021/11/10
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    /* 这个语句是用来拼装当前网页的相对路径的。 */
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//判断是否已经登陆
    Object userName=session.getAttribute("username");
    if(userName==null){
//	重定向
        response.sendRedirect("/cyq/demo.jsp");
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--	 <base> 标签为页面上的所有链接规定默认地址或默认目标。--%>
    <%--	  个人认为这行代码是为了隐藏路径，只显示大概的路径--%>
    <base href="<%=basePath%>">

    <title>聊天窗口</title>
    <%--	http-equiv顾名思义，相当于http的文件头作用，它可以向浏览器传回一些有用的信息，以帮助正确和精确地显示网页内容，--%>
    <%--	与之对应的属性值为content，content中的内容其实就是各个参数的变量值。--%>
    <%--	具体含义参考网址：https://www.cnblogs.com/yumo1627129/p/7198968.html--%>

    <%--	Pragma(cache模式)--%>
    <%--	说明：禁止浏览器从本地计算机的缓存中访问页面内容。--%>
    <%--	用法：<metahttp-equiv="Pragma"content="no-cache">--%>
    <%--	注意：这样设定，访问者将无法脱机浏览。--%>
    <meta http-equiv="pragma" content="no-cache">

    <%--	清除缓存（再访问这个网站要重新下载！）--%>

    <meta http-equiv="cache-control" content="no-cache">

    <%--	Expires(期限)--%>
    <%--	说明：可以用于设定网页的到期时间。一旦网页过期，必须到服务器上重新传输。--%>
    <%--	用法：<metahttp-equiv="expires"content="Fri,12Jan200118:18:18GMT">--%>
    <%--	注意：必须使用GMT的时间格式--%>

    <meta http-equiv="expires" content="0">

    <%--	关键字,给搜索引擎用的--%>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <%--	页面描述--%>

    <meta http-equiv="description" content="This is my page">

    <%--	导入jq包--%>
    <script src="js/jquery.js"></script>

    <%--	导入bootstradp框架--%>
    <script src="js/bootstrap.js"></script>

    <%--	链接一个外部样式表：--%>
    <link rel="stylesheet" type="text/css" href="css/bootstrapa.min.css">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style>
        .div123{
            position: relative;
            top: -9px;
        }
    </style>
</head>

<body>
<script>
    $(function(){
        //getTime();
        // 调用getChatMessage
        getChatMessage();
    });
    function getTime(){
        $.ajax({
            url:"/cyq/TimePolling",//地址
            dataType:"html",//传输类型
            type:"POST",//post类型
            data:{
                id:1
            },
            success:function(data){
                $("#time").html(data);
                getTime();
            }
        });
    }
    function getChatMessage(){
        $.ajax({
            url:"/cyq/charServlet",
            dataType:"html",
            type:"POST",
            data:{
                id:1
            },
            success:function(data){
                $("#chatHistory").append(data+"<br/>");
                getChatMessage();
            }
        });
    }
    //
    function sendMsg(){
        var msg=$("#chatInput").val();
        $.ajax({
            url:"/cyq/SendMsg",
            dataType:"html",
            type:"POST",
            data:{
                message:msg
            },
            success:function(data){
                $("#chatHistory").append(data);
            }
        });
        $("#chatInput").val("")
    }
    //ajax查询在线人数
    timename=setInterval(function(){
        let a=1;
        $.post("onloadServlet",{a:a},function (data){
            $("#userList").empty()
            var arr=new Array();
            arr=data.split(',');
            $("#a").html('在线人数：' +arr[0]);
            for (let i = 1; i < arr.length; i++) {
                let a = arr.length;
                var trs = "<li>"+arr[i]+"</li>";
                $("#userList").append(trs);
            }
            // $.each((arr), function (n,value) {
                // $.each((arr), function (n,value) {
                //     var trs = "<ul><li>"+value.name+"</li></ul>";
                //     $("#userList").append(trs);
                // $("#userList").html(arr[1])
                //     var trs = "<li>"+arr[n]+"</li>";
                //     console.log("n:"+n);
                // console.log("arr:"+arr[n]);
                // $("#userList").html(trs);
                //     $("#userList").append("<li>" + value.name+ "</li>");
                // });
            // });
        },"text");
    },3000);
</script>
<div class="page-header" style="margin-left:20px;">
</div>
<div class="container">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span10">
                <div style="height:600px">
                    <div class="well" style="height:300px;overflow-y:auto;"id="chatHistory"></div>
                    <div style="height:150px;overflow-y:auto;"id="chatBox">
                        <%--  							<textarea style="width:100%" id="chatInput" ></textarea>--%>
                        <input required="required"  placeholder="请输入"  id="chatInput" style="width: 100%">
                        <br/>
                        <button type="submit" class="btn" onclick="sendMsg()">
                            发送
                        </button>

<%--                            <FORM action="/cyq/onloadServlet" method="get">--%>
<%--                                <button type="submit" class="exit" onclick="exit()">退出聊天</button>--%>
<%--                            </FORM>--%>


                    </div>
                </div>
            </div>
            <div class="span2">
                <div style="background:#abcdef;height:400px;" class="div123">
                    <h5 id="a"></h5>
                    <ul id="userList">
<%--                        <c:forEach items="${sessionname}" var="u">--%>
<%--                        <li>${u}</li>--%>
<%--                        <br/>--%>
<%--                        </c:forEach>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //回车键发送消息
    $(document).bind("keydown", function (e) {
        // 兼容FF和IE和Opera
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            var elem = $(":focus");
            //排除remark编辑的 回车事件
            if (elem.attr("id") !="Remark") {
                //回车执行添加行
                $("#btn").click(sendMsg());
            }
        }
    });
<%--function exit(){--%>
<%--<%HttpSession session1=request.getSession();--%>
<%--    session1.setAttribute("session2",session1);--%>
<%--%>--%>
}
</script>
</body>
</html>

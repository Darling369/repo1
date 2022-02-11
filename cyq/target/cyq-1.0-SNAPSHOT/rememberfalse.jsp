<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" charset="UTF-8"></script>
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

    /*整体布局*/
    .modal{
      /*修改*/
      width: 800px;
      height: 500px;
      margin: 0 auto;
      position: relative;
      top: 40%; /*偏移*/
      background: #ffffff;
      border-radius: 5px;
    }

    /*横线*/
    .confirm{
      height: 50px;
      border-top: 3px solid skyblue;
      display: flex;
      /*修改*/
      position: relative;
      top: 290px;
    }
    .confirm>span{
      flex: 1;
      height: 50px;
      line-height: 50px;
      font-size: 16px;
      text-align: center;
      /*修改*/
      top: -5px;
      left: 390px;
      position: absolute;
      cursor: pointer;
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
      background-color: aquamarine;
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

    /*输入框*/
    .a{
      width: 794px;
      height: 100px;
      top: 290px;
      position: relative;
    }
    .btn{
      color: red;
      font-size: 20px;
      position: relative;
      top: -10px;
      background-color:transparent;  /* 背景色透明 */
      border:0px;       /* 边框取消 */
      left: 360px;
      cursor: pointer;
    }
    .tabletalk{
        overflow-x: auto;
        overflow-y: auto;
        height: 406px;
        width: 800px;
        top: 105px;
        position: fixed;
    }
    .table{
        table-layout: fixed;
        width: 500px;
        height:818px;
        top: -170px;
        position: relative;
     }
    tr{
        height: 40px;
    }
    td{
        width: 10px;
    }
  </style>
</head>
<body id="body">

<div class="modal" id="modal">
  <div class="title">
    <p class="important">聊天窗口</p>
  </div>
  <div class="content">
<%--      <strong><%="用户："+session.getAttribute("username")+"       说"%></strong>--%>
      <strong>${text}</strong>
  </div>
    <div class="tabletalk" id="divDetail">
        <table class="table">
            <c:forEach items="${requestScope.s}" var="user"  varStatus="s">
                <tr>
                    <td>用户&nbsp&nbsp&nbsp${requestScope.s.username}说：&nbsp&nbsp${requestScope.s.talk}</td>
                                   <td>${user.talk}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form action="talkServlet">
<%--获取用户的用户名--%>
    <input type="hidden" name="username" value="<%=session.getAttribute("username")%>" id="username"/>
        <input type="hidden" name="name" value="<%=session.getAttribute("name")%>" id="name"/>
        <input type="hidden" name="date"  id="date_info"/>
    <input type="hidden" name="id" value=null id="id"/>
    <script>
        $(document).ready(function () {
            var time = new Date();
            var ss = ("0" + time.getSeconds()).slice(-2);
            var min = ("0" + time.getMinutes()).slice(-2);
            var hours = ("0" + time.getHours()).slice(-2);
            var day = ("0" + time.getDate()).slice(-2);
            var month = ("0" + (time.getMonth() + 1)).slice(-2);
            var today = time.getFullYear() + "-" + (month) + "-" + (day)+ "-" + (hours)+ "-" + (min)+ "-" + (ss);
            $('#date_info').val(today);
        })
    </script>

    <input required="required" type="text" name="talk" placeholder="请输入" class="a" id="talk">
      <div class="confirm" id="about">
<!--        <span class="close-but" type="submit" >发送</span>-->
        <input type="submit" value="发送" class="btn" name="btnSubmit" id="btnSubmit" >
      </div>
        <script>
            // timename=setInterval(function(){


            //     var name = $("#name").val()
            //     var talk= $("#talk").val()
            //     $.post("ajaxServlet",{name:name,talk:talk},function (data){
            //
            //         $.each($.parseJSON(data), function (n, value) {
            //             // console.log(n + ' ' + value);
            //             var trs = "<tr><td>" +"用户"+ value.name +"说"+ "</td> <td>" + value.talk + "</td><td>"+ value.date + "</td></tr>";
            //             $(".table").append(trs);
            //             var div = document.getElementById('divDetail');
            //             div.scrollTop = div.scrollHeight;
            //         });
            //     },"text");


                // },3000);

            //查询数据库id最大的一条数据
            // timename=setInterval(function(){
            // var name = $("#name").val()
            // var talk= $("#talk").val()
            // $.post("ajaxnewServlet",{name:name,talk:talk},function (data){
            //
            //     $.each($.parseJSON(data), function (n, value) {
            //         // console.log(n + ' ' + value);
            //         var trs = "<tr><td>" +"用户"+ value.name +"说"+ "</td> <td>" + value.talk + "</td><td>"+ value.date + "</td></tr>";
            //         $(".table").append(trs);
            //         var div = document.getElementById('divDetail');
            //         div.scrollTop = div.scrollHeight;
            //     });
            // },"text");
            // },3000);
            function longPolling(){
                $.ajax({
                    async : true,//异步
                    url : 'ajaxnewServlet',
                    type : 'post',
                    dataType : 'json',
                   data:{},
                    timeout : 30000,//超时时间设定30秒
                    error : function(xhr, textStatus, thrownError) {
                        longPolling();//发生异常错误后再次发起请求
                    },
                    success : function(response) {
                        message = response.data.message;
                        if(message!="timeout"){
                                $.each($.parseJSON(data), function (n, value) {
                                    // console.log(n + ' ' + value);
                                    var trs = "<tr><td>" +"用户"+ value.name +"说"+ "</td> <td>" + value.talk + "</td><td>"+ value.date + "</td></tr>";
                                    $(".table").append(trs);
                                    var div = document.getElementById('divDetail');
                                    div.scrollTop = div.scrollHeight;
                                });
                        }
                        longPolling();
                    }
                });
            }
        </script>
    </form>
</div>
<script type="text/javascript">
  let modalHeight = document.getElementById('modal').offsetHeight;
  document.getElementById('modal').style.marginTop=-(modalHeight/2)+"px";

  function fnClose(){
    document.getElementById('modal').setAttribute("class","hidden");
    document.getElementById('body').setAttribute("class","body-class");
    window.location.href='/demo1/asd.jsp';
  }
</script>
<div class="center-in-center" ></div>
</body>
<script type="text/javascript" defer>
        var div = document.getElementById('divDetail');
      div.scrollTop = div.scrollHeight;
      // $('#divDetail').scrollTop($('30'));
     //alert(div.scrollTop);
   </script>
</html>
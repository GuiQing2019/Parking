<%@page import="java.util.*" errorPage="_Error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/Style/AddStyle.css"/>
</head>
<body onload="findSeat()">
<form id="addCardId" action="<%=path %>/card" method="get" class="Form">
    <input type="hidden" name="method" value="addCard">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>车位编号：</td>
            <td>
                <select  id="seatId" name="seat_id">
                    <option>请选择</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>用户名称：</td>
            <td><input type="text" name="user_name" required/></td>
        </tr>
        <tr>
            <td>用户性别：</td>
            <td>
                <select name="user_gender">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>家庭住址：</td>
            <td><input type="text" name="user_addr" required/></td>
        </tr>
        <tr>
            <td>车牌号码：</td>
            <td><input type="text" name="car_num" required/></td>
        </tr>
        </tbody>

        <tfoot>
        <tr>
            <td><input type="submit" value="确定" id="btnSure"/></td>
            <td><input type="reset" value="取消" id="btnCancel"/></td>
        </tr>
        </tfoot>
    </table>
</form>

<script>
    //获取车位编号
    var sNode=document.getElementById("seatId");

    $("#addCardId").ajaxForm({
       beforeSubmit:function () {

       },
        dataType:"json",
        success:function (data) {
            if (data.code ==400){
                alert(data.msg);
            } else {
                alert(data.msg)
            }
        }
    });

  function findSeat() {
     $.ajax({
         type:"get",
         url:"/card",
         data:{
             method:"findSeat"
         },
         async:false,
         dataType:"json",
         success: function (data) {
             for (var i = 0; i < data.length; i++) {
                 //创建option
                 var optNode=document.createElement("option");
                 optNode.value =data[i].seat_id;
                 optNode.text =data[i].seat_num;
                 sNode.add(optNode);
             }
         }
     });
 }

</script>
</body>
</html>

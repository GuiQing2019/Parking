<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=path %>/Style/MsgStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <title>Insert title here</title>
</head>
<body>

<div class="div_container">
    <div class="searchDiv">
        <form action="/CardHandle" method="get">
            <span>查询条件：</span>
            <select name="condition">
                <option value='card_id'>IC卡编号</option>
                <option value='seat_id'>车位编号</option>
                <option value='user_name'>用户名称</option>
                <option value='user_gender'>用户性别</option>
                <option value='user_addr'>家庭住址</option>
                <option value='car_num'>车牌号码</option>

            </select>

            <span>查询值：</span>
            <input type="hidden" name="type" value="5"/>
            <input type="text" name="value"/>
            <input type="submit" value="查询 "/>
        </form>
    </div>
    <!-- searchDiv End -->

    <table>
        <tr>
            <th>IC卡编号</th>
            <th>车位编号</th>
            <th>用户名称</th>
            <th>用户性别</th>
            <th>家庭住址</th>
            <th>车牌号码</th>

            <th>操作</th>
        </tr>
        <c:forEach var="index" items="${cards}">
            <tr>
                <td>${index.card_id}</td>
                <td>${index.seat_id}</td>
                <td>${index.user_name}</td>
                <td>${index.user_gender}</td>
                <td>${index.user_addr}</td>
                <td>${index.car_num}</td>
                <td>
                    <a href="/card?method=findCard&id=${index.card_id}">修改</a>
                    &nbsp;&nbsp;
                    <a href="#" onclick="delCard('${index.card_id}',this)">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<script>
    //通过ajax删除IC卡信息
    delCard=function (cid,obj) {
    $.ajax({
        type:"post",
        url:"/card",
        data:{
            method:"delCard",
            id:cid
        },
        async:false,
        dataType:"json",
        success:function (data) {
            console.log(data)
            if (data.code ==400){
                alert(data.msg);
                $(obj).parent().parent().remove();
            }
            if (data.code==401) {
                alert(data.msg);
            }
        }
    })


    }
</script>
</body>
</html>

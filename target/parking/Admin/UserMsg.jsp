<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/Style/MsgStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/Script/jquery-1.10.1.js"></script>
    <title>Insert title here</title>
</head>
<body>
<div class="div_container">
    <div class="searchDiv">
        <form action="/user" method="get">
            <input type="hidden" name="method" value="queryAllUser">
            <span>查询条件：</span>
            <select name="condition">
                <option value='user_id'>用户编号</option>
                <option value='role_name'>角色名称</option>
                <option value='user_name'>用户昵称</option>
                <option value='real_name'>真实姓名</option>
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
            <th>用户编号</th>
            <th>角色名称</th>
            <th>用户名称</th>
            <th>真实姓名</th>
            <th>用户密码</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>

        <c:forEach var="index" items="${users}">
            <tr>
                <td>${index.user_id}</td>
                <td>${index.role_id}</td>
                <td>${index.user_name}</td>
                <td>${index.real_name}</td>
                <td>${index.user_pwd}</td>
                <td>${index.user_phone}</td>
                <td>
                    <a href="/user?method=findUser&id=${index.user_id}">修改</a>
                    &nbsp;&nbsp;
                    <a href="#" onclick="delUser('${index.user_id}',this)">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>


<script>
    delUser = function (uid, obj) {
        alert("1")
        //通过ajax进行删除
        $.ajax({
            type: "post",
            url: "/user",
            data: {
                method: "delUser",
                id: uid
            },
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.code == 400) {
                    alert(data.msg);
                    $(obj).parent().parent().remove();
                }

                if (data.code == 401) {
                    alert(data.msg)
                }
            }
        });
    }


</script>
</body>
</html>

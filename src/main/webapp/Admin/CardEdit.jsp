<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../Style/EditStyle.css"/>
    <script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>


</head>
<body>
<form action="/card" method="post">
    <input type="hidden" name="method" value="updateCard">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>IC卡编号：</td>
            <td><input type="text" name="card_id" value="${card.card_id}" readonly/></td>
        </tr>
        <tr>
            <td>车位编号：</td>
            <td>
                <select name="seat_id">
                    <option>请选择</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>用户名称：</td>
            <td><input type="text" name="user_name" value="${card.user_name}"/></td>
        </tr>
        <tr>
            <td>用户性别：</td>
            <td>
                <select name="user_gender">
                    <option>请选择</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>用户地址：</td>
            <td><input type="text" name="user_addr" value="${card.user_addr}" required/></td>
        </tr>
        <tr>
            <td>车牌号码：</td>
            <td><input type="text" name="car_num" value="${card.car_num}" required/></td>
        </tr>

        </tbody>

        <tfoot>
        <tr>
            <td><input type="submit" value="确定" id="btnSure"/></td>
            <td><input type="button" value="取消" id="btnCancel"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>

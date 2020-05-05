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
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="/Style/EditStyle.css"/>


</head>
<body>
<form id="updateRoleId" action="/user" method="post">
    <input type="hidden" name="method" value="updateRole">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>角色编号：</td>
            <td><input type="text" name="role_id" value="${role.role_id}" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>角色名称：</td>
            <td><input type="text" name="role_name" value="${role.role_name}" required/></td>
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


<script>

    //表单通过ajax提交数据
    $("#updateRoleId").ajaxForm({
        beforeSubmit: function () {
            alert("1")
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.code == 400) {
                alert(data.msg);
            } else {
                alert(data.msg)
            }
        }
    });


</script>
</body>
</html>

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
<body>
<form id="addUserId" action="/user" method="post" class="Form">
    <input type="hidden" name="method" value="addRole">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>角色编号：</td>
            <td><input id="roleId" type="text" name="role_id" required/></td>
        </tr>
        <tr>
            <td>角色名称：</td>
            <td><input type="text" name="role_name" required/></td>
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


    //表单通过ajax提交数据
    $("#addUserId").ajaxForm({
            beforeSubmit: function () {

            },
            dataType: "json",  //服务器返回数据类型,转成json对象
            success: function (data) {//服务器返回的数据

                console.log(data);
                if (data.code == 400) { //添加成功,查询所有数据进行显示

                    alert(data.msg);
                    console.log(data.msg)

                } else {
                    //添加失败,直接弹出
                    alert(data.msg);
                }

            }
        }
    );
</script>
</body>
</html>

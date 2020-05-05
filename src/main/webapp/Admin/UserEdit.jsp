<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="../Style/EditStyle.css"/>

</head>
<body onload="findRole()">

<form id="updateUserId" action="/user" method="get">
    <input type="hidden" name="method" value="updateUser">
    <table style=" margin:50px auto;">
        <tbody>
        <tr>
            <td>用户编号：</td>
            <td><input type="text" name="user_id" value="${user.user_id}" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>角色名称：</td>
            <td>
                <select id="rid" name="role_id">
                    <option>选择用户</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>用户昵称：</td>
            <td><input type="text" name="user_name" value="${user.user_name}" required/></td>
        </tr>
        <tr>
            <td>真实姓名：</td>
            <td><input type="text" name="real_name" value="${user.real_name}" required/></td>
        </tr>
        <tr>
            <td>用户密码：</td>
            <td><input type="text" name="user_pwd" value="${user.user_pwd}" required pattern="^\w{6,}$"
                       title="密码长度不能少于6位"/>
            </td>
        </tr>
        <tr>
            <td>联系方式：</td>
            <td><input type="text" name="user_phone" value="${user.user_phone}" pattern="^1[1-9]\d{9}$" title="手机号码有误"/>
            </td>
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
    var rNode = document.getElementById("rid")


    //通过ajax对接服务器
    $("#updateUserId").ajaxForm({
        beforeSubmit: function () {

        },
        dataType: "json",
        success: function (data) {
            console.log(data)
            if (data.code = 400) {
                //更新成功
                alert(data.msg);
            } else {
                //添加失败
                alert(data.msg)
            }
        }
    });

    //获取Select节点id
    function findRole() {
        $.ajax({
            type: "get",  //获取请求参数
            url: "/user", //请求地址
            data: {
                method: "queryAll"
            },
            async: false,    //是否异步
            dataType: "json",//服务器返回的数据类型
            success: function (data) { //请求成功执行的方法
                for (var i = 0; i < data.length; i++) {
                    //创建option
                    var optNode = document.createElement("option");
                    optNode.value = data[i].role_id;
                    optNode.text = data[i].role_name;
                    //将option加进sel
                    rNode.add(optNode);
                }
            }
        });
    }
</script>
</body>
</html>

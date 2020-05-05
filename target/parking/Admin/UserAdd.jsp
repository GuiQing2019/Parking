<%@page import="java.util.*" errorPage="_Error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="true" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <link rel="stylesheet" type="text/css" href="/Style/AddStyle.css"/>
</head>
<body onload="findRole()">
<form id="addUserId" action="/user" method="post" class="Form">
    <input type="hidden" name="method" value="addUser">
    <table style=" margin:50px auto;">
        <tr>
            <td>用户编号：</td>
            <td><input type="text" name="user_id"/></td>
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
            <td><input type="text" name="user_name"/></td>
        </tr>
        <tr>
            <td>真实姓名：</td>
            <td><input id="realnameId" type="text" name="real_name"/></td>
        </tr>
        <tr>
            <td>用户密码：</td>
            <td><input id="passwordId" type="text" name="user_pwd"/></td>
        </tr>
        <tr>
            <td>联系电话：</td>
            <td><input id="phoneId" type="text" name="user_phone"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="确定"/>
                <input type="reset" value="取消"/>
            </td>
        </tr>
    </table>
</form>


<script>
    var rNode = document.getElementById("rid")

    $("#addUserId").ajaxForm({
        beforeSumit: function () {
            alert("1")
        },
        dataType: "json",            //服务器返回数据类型,转成Json
        success: function (data) {   //服务器返回的数据
            console.log(data);
            if (data.code == 400) { //添加成功
                alert(data.msg);
                location.href = "/user?method=queryAllUser";
            } else {               //添加失败
                alert(data.msg);

            }
        }
    });


    /*
  * 验证方法的封装:
  *
  *   objId:       输入框的id
  *   msgId:       错误信息提示id
  *   reg:         判断
  *   success_msg: 正确信息提示
  *   err_msg:     错误信息提示
  *   */

    //验证格式
    function checkInputStr(objId, msgId, reg, success_msg, err_msg) {

        //验证密码
        //1.获取输入框输入内容
        var pwd = $(objId).val();
        console.log(pwd)
        //2.通过正则进行判断
        //3.dom操作
        if (reg.test(pwd)) {//格式正确
            $(msgId).text(success_msg).css("color", "green");
            return true;
        } else {
            //格式错误
            $(msgId).text(err_msg).css("color", "red");
            return false;
        }

    }

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

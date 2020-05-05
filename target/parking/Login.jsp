<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();//获取项目名称
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登陆</title>
    <link rel="stylesheet" type="text/css" href="/Style/Login.css"/>
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
</head>
<body>

<div class="wrapLogin">
    <div class="loginPanel">
        <form id="loginId" action="/login" method="post">
            <h2>智能化停车场管理系统</h2>
            <p><label>用名：</label><input id="usernameId" type="text" name="user_id"/>
                <span id="nameMsg"></span>
            </p>

            <p><label>密码：</label><input id="passwordId" type="password" name="user_pwd"/>
                <span id="pwdMsg"></span>
            </p>
            <p class="btn">
                <input type="submit" class="btnLogin" value="登陆"/>
                <input type="button" class="btnCancel" value="重置"/></p>
        </form>
    </div>
</div>

<script type="text/javascript">

    $("#loginId").ajaxForm({
        beforeSubmit: function () {


                       //检测提交之前的验证
            var name = $("#usernameId").val();
            var pwd = $("#passwordId").val();
            //定义标记
            var uFlag = true;
            var pFlag = true;

            //判断输入不能为空
            if (name.length == 0) {
                $("#nameMsg").text("用户名不能为空!").css("color", "red");
                uFlag = false;
            }

            if (pwd.length == 0) {
                $("#pwdMsg").text("密码不能为空!").css("color", "red");
                pFlag = false;
            }
            return uFlag && pFlag;
        },
        dataType: "json",
        //服务器回写数据转成Json
        success: function (data) {
            //请求成功的方法
            console.log(data);
            if (data.code == 100) {
                //登录成功,页面跳转
                alert(data.msg);
                location.href = "/index.jsp";
            }
            if (data.code == 101) {
                alert(data.msg);
            }
        }
    });
</script>
</body>
</html>
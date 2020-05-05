<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
<form id="updateSeatId" action="/seat" method="get">
    <input type="hidden" name="method" value="updateSeat">
    <table style=" margin:50px auto;">
        <tr>
            <td>车位ID：</td>
            <td><input type="text" name="seat_id" value="${seat.seat_id}" readonly/></td>
        </tr>
        <tr>
            <td>车位编号：</td>
            <td><input type="text" name="seat_num" value="${seat.seat_num}" required/></td>
        </tr>
        <tr>
            <td>车位区域：</td>
            <td>
                <select name="seat_section">
                    <option>请选择</option>
                    <option value="A区">A区</option>
                    <option value="B区">B区</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>车位状态：</td>
            <td>
                <select name="seat_state">
                    <option>请选择</option>
                    <option value="0">空闲</option>
                    <option value="1">占用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>车位备注：</td>
            <td><input type="text" name="seat_tag" value="${seat.seat_tag}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="确定" id="btnSure"/></td>
            <td><input type="button" value="取消" id="btnCancel"/></td>
        </tr>

    </table>
</form>

<script>
    $("#updateSeatId").ajaxForm({
       beforeSubmit: function () {
           alert("11")
       },
        dataType:"json",
        success:function (data) {

            if (data.code ==400) {
                //修改成功
                alert(data.msg);
            } else {
                //修改失败
                alert(data.msg);
            }
        }
    });
</script>
</body>
</html>

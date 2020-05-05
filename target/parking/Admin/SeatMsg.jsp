<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	String path=request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/Script/jquery-1.10.1.js"></script>
<link href="<%=path %>/Style/MsgStyle.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>

</head>
<body>
<div class="div_container">
	<div class="searchDiv">
		<form action="<%=path %>/SeatHandle" method="get">
			<span>查询条件：</span>
			<select name="condition">
			 	<option value='seat_id'>车位ID</option>
				<option value='seat_num'>车位编号</option>
				<option value='seat_section'>所属区域</option>
				<option value='seat_tag'>车位备注</option>
                <option value='seat_state'>车位状态</option>
			</select>
			 	
			<span>查询值：</span>
			<input type="hidden" name="type" value="5" />
			<input type="text" name="value" />
			<input type="submit" value="查询 "/>
		</form>
	</div>
	<!-- searchDiv End -->

	<table>
			<tr>
				<th>车位ID</th>
				<th>车位编号</th>
				<th>所属区域</th>
				<th>车位状态</th>
				<th>车位备注</th>
				<th>操作</th>
			</tr>
			<c:forEach var="index" items="${seats}">
				<tr>
					<td>${index.seat_id}</td>
					<td>${index.seat_num}</td>
					<td>${index.seat_section}</td>
					<td>${index.seat_state}</td>
					<td>${index.seat_tag}</td>
					<td>
						<a href="/seat?method=findSeat&id=${index.seat_id}">修改</a>
						&nbsp;&nbsp;
						<a href="#" onclick="delSeat('${index.seat_id}',this)">删除</a>
					</td>
				</tr>
			</c:forEach>

	</table>
</div>

	<script>
		delSeat = function (sid, obj) {
			//通过ajax进行删除
			$.ajax({
				type:"post",
				url:"/seat",
				data:{
				    method:"delSeat",
					id:sid
				},
				async:false,
				dataType:"json",
				success:function (data) {
					console.log(data)
					if (data.code == 400){
					    //删除成功
					    alert(data.msg);
					    $(obj).parent().parent().remove();

					}
					if (data.code ==401){
					    //删除失败
						alert(data.msg);
                    }
                }
			});
        }
	</script>


</body>
</html>

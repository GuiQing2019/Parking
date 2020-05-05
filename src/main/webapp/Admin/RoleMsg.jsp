<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/Style/MsgStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/Script/jquery-1.10.1.js"></script>
    <title>Insert title here</title>

</head>
<body>
<div class="div_container">
    <div class="searchDiv">
        <div class="row">
            <div class="col-sm-6">
                <div class="dataTables_length" id="DataTables_Table_0_length">
                    <label>每页 10 条记录</label></div>
            </div>
        </div>
    </div>
    <!-- searchDiv End -->
    <table>
        <tr>
            <th>角色编号</th>
            <th>角色名称</th>
            <th>操作</th>
        </tr>

        <c:forEach var="index" items="${page.pageData}">
            <tr>
                <td>${index.role_id}</td>
                <td>${index.role_name}</td>
                <td>
                        <%--
                        method=findUserById&uid=${index.id} 带去user服务器的参数
                        --%>
                    <a href="/user?method=findRole&id=${index.role_id}">修改</a>
                    &nbsp;&nbsp;
                    <a href="#" onclick="delRole('${index.role_id}',this)">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="row">
        <div class="col-sm-6">
            <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria-live="polite"
                 aria-relevant="all">显示 ${page.startIndex+1} 到 ${page.pageSize+page.startIndex}
                项，共 ${page.totalCount} 项
            </div>
        </div>

        <div class="col-sm-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <%--如果是第一页,则不能点击上一页--%>
                    <c:if test="${page.currentPage ==1}">
                        <li class="paginate_button previous disabled" aria-controls="DataTables_Table_0"
                            tabindex="0"
                            id="DataTables_Table_0_previous"><a
                                href="/user?method=findPageUser&currentPage=${page.prePage}&pageSize=${page.pageSize}">上一页</a>
                        </li>
                    </c:if>
                    <%--如果不是第一页，则返回上一页--%>
                    <c:if test="${page.currentPage !=1}">
                        <li class="paginate_button previous" aria-controls="DataTables_Table_0"
                            tabindex="0"
                            id="DataTables_Table_0_previous"><a
                                href="/user?method=findPageUser&currentPage=${page.prePage}&pageSize=${page.pageSize}">上一页</a>
                        </li>

                    </c:if>
                    <c:forEach var="index" begin="${page.statNav}" end="${page.endNav}">


                        <%--选中高亮--%>
                        <c:if test="${index ==page.currentPage}">
                            <li class="paginate_button active" aria-controls="DataTables_Table_0" tabindex="0">
                                <a href="#">${index}</a>
                            </li>
                        </c:if>
                        <%--其他正常显示--%>
                        <c:if test="${index !=page.currentPage}">
                            <li class="paginate_button" aria-controls="DataTables_Table_0" tabindex="0">
                                <a href="/user?method=findPageUser&currentPage=${index}&pageSize=${page.pageSize}">${index}</a>
                            </li>
                        </c:if>
                    </c:forEach>

                    <%--如果位于最后一页无法选中--%>
                    <c:if test="${page.nextPage==page.lastPage}">

                        <li class="paginate_button next disabled" aria-controls="DataTables_Table_0"
                            tabindex="0"
                            id="DataTables_Table_0_next"><a
                                href="/user?method=findPageUser$currentPage=${page.nextPage}&${page.pageSize}">下一页</a>
                        </li>
                    </c:if>

                    <%--不位于最后一页可选--%>
                    <c:if test="${page.nextPage !=page.lastPage}">
                        <li class="paginate_button next" aria-controls="DataTables_Table_0"
                            tabindex="0"
                            id="DataTables_Table_0_next"><a
                                href="/user?method=findPageUser&currentPage=${page.nextPage}&${page.pageSize}">下一页</a>
                        </li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>
</div>


<script>
    delRole = function (rid, obj) {
        //通过ajax进行删除
        $.ajax({
            type: "post",
            url: "/user",
            data: {
                method: "delRole",
                id: rid
            },
            async: false,
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data.code == 400) {
                    //删除成功
                    alert(data.msg);

                    //把a标签这个tr进行删除
                    //通过a标签拿到td 标签通过td标签拿到tr标签把tr标签进行删除
                    //obj是a标签,通过.parent()拿到td标签
                    //在通过td标签的.parent()拿到tr标签 在通过remove移除tr 标签
                    $(obj).parent().parent().remove();
                }
                if (data.code == 401) {
                    //删除失败
                    alert(data.msg);

                }
            }
        });
    }


</script>
</body>
</html>

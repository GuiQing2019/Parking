<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>

<link rel="shortcut icon" href="favicon.ico">
<link href="/js/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="/js/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

<link href="/css/animate.css" rel="stylesheet">
<link href="/css/style.css?v=4.1.0" rel="stylesheet">
<head>
    <title>Title</title>
</head>
<body>

    <div class="ibox-content">

    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper form-inline" role="grid">
        <div class="row">
            <div class="col-sm-6">
                <div class="dataTables_length" id="DataTables_Table_0_length"><label>每页 <select
                        name="DataTables_Table_0_length" aria-controls="DataTables_Table_0"
                        class="form-control input-sm">
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select> 条记录</label></div>
            </div>
            <div class="col-sm-6">
                <div id="DataTables_Table_0_filter" class="dataTables_filter">
                    <label>查找：
                        <input type="search" class="form-control input-sm" aria-controls="DataTables_Table_0">
                    </label>
                </div>
            </div>
        </div>
        <table class="table table-striped table-bordered table-hover dataTables-example dataTable"
               id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
            <tr role="row">
                <th class="sorting_asc" rowspan="1" colspan="1"
                    style="width: 178px;">角色编号
                </th>
                <th class="sorting_asc" rowspan="1" colspan="1"
                    style="width: 178px;">角色名称
                </th>
            </tr>
            <c:forEach var="user" items="${page.pageData}">
                <tr>
                    <th rowspan="1" colspan="1">${user.role_id}</th>
                    <th rowspan="1" colspan="1">${user.role_name}</th>
                    <th></th>
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
                <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
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
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

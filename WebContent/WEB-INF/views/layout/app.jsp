<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--雛形 --%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>日報管理システム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <%-- value='/' = トップページ --%>
                    <h1><a href="<c:url value='/' />">日報管理システム</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_employee != null}">
                    <%-- 管理者なら従業員管理へのリンク表示 --%>
                        <c:if test="${sessionScope.login_employee.admin_flag == 1}">
                            <a href="<c:url value='/employees/index' />">従業員管理</a>&nbsp;
                        </c:if>
                        <c:if test="${sessionScope.login_employee.admin_flag == 2}">
                            <a href="<c:url value='/approvals/index' />">日報承認</a>&nbsp;
                        </c:if>
                        <c:if test="${sessionScope.login_employee.admin_flag == 3}">
                            <a href="<c:url value='/approvals/index' />">日報承認</a>&nbsp;
                        </c:if>

                        <a href="<c:url value='/clients/index' />">顧客管理</a>&nbsp;
                        <a href="<c:url value='/reports/index' />">日報管理</a>&nbsp;
                        <a href="<c:url value='/follows/index' />">フォロー日報</a>&nbsp;
                        <a href="<c:url value='/goods/index' />">いいね日報</a>&nbsp;

                        <a href="<c:url value='/attendances/index' />">出退勤管理</a>&nbsp;
                    </c:if>
                </div>
                <%-- ログアウト用リンク --%>
                <c:if test="${sessionScope.login_employee != null}">
                    <div id="employee_name">
                        <c:out value="${sessionScope.login_employee.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Taro Kirameki.
            </div>
        </div>
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロワー追加画面</h2>
        <h3><c:out value="${report.employee.name}" />さんをフォローしますか？</h3><br /><br />
        <form method="POST" action="<c:url value='/follows/create?id=${report.id}' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
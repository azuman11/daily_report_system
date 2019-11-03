<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロ解除画面</h2>
        <h3><c:out value="${report.employee.name}" />さんへのフォローを解除しますか？</h3><br /><br />
        <form method="POST" action="<c:url value='/follows/destroy?id=${report.employee.id}' />">
            <input type="hidden" name="_token" value="${_token}" />
            <input type="hidden" name="report.employee.id" value="${report.employee.id}" />
            <button type="submit">解除する</button>
        </form>

        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
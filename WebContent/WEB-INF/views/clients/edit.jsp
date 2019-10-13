<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${client != null}">
                <%-- app.jspの${param.content}になる --%>
                <h2>${client.name}様 の顧客情報　編集ページ</h2>

                <form method="POST" action="<c:url value='/clients/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="<c:url value='/clients/index' />">顧客様一覧に戻る</a></p>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

    </c:param>
</c:import>
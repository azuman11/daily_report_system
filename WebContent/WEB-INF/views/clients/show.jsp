<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${client != null}">
                <h2>${client.name}様 の顧客情報　詳細ページ</h2>

                <%--tr row th head td date --%>
                <table>
                    <tbody>
                        <tr>
                            <th>所属企業</th>
                            <td><c:out value="${client.company}" /></td>
                        </tr>
                        <tr>
                            <th>担当者名</th>
                            <td><c:out value="${client.name}" /></td>
                        </tr>
                        <tr>
                            <th>連絡先</th>
                            <td><c:out value="${client.contact_address}" /></td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/clients/edit?id=${client.id}' />">この顧客情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/clients/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- new editで使用--%>

<%--未入力時 --%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<%--入力時 --%>

<%-- value="${message.***}"はリクエストスコープ(controller)でセットされたclientから参照 --%>
<%-- valueに中身が入っている場合は、既に入ってる状態で表示される --%>
<label for="company">所属企業</label><br />
<input type="text" name="company" value="${client.company}" />
<br /><br />

<label for="name">氏名</label><br />
<input type="text" name="name" value="${client.name}" />
<br /><br />

<label for="contact_address">連絡先</label><br />
<input type="text" name="contact_address" value="${client.contact_address}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />

<button type="submit">登録</button>
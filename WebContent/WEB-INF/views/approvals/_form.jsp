<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--未入力時 --%>
<c:if test="${errors != null}">
    <div id="flush_error">
                入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
                             ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<%-- 権限 --%>
<label for="approval_flag">レポート承認</label><br />
<%-- 選択式タブ --%>
<select name="approval_flag">
    <option value="0"<c:if test="${report.approval == 0}"> selected</c:if>>未承認</option>
    <option value="1"<c:if test="${report.approval == 1}"> selected</c:if>>承認</option>
</select>

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
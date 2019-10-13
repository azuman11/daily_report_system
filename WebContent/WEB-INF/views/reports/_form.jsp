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

<%--入力時 --%>
<label for="report_date">日付</label><br />
<%-- ブラウザ依存だが、input type="date"とするとカレンダー入力もできるようになる --%>
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${report.title}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br /><br />


<label for="client">本日の取引相手</label><br />
<select name="client">
<c:forEach var="clients" items="${clients}">
<option  value="${clients.id}"<c:if test="${report.clients_id == clients.id}"> selected</c:if>>${clients.name}</option>
</c:forEach>
</select>
<br /><br />


<label for="clients_content">商談内容</label><br />
<textarea name="clients_content" rows="10" cols="50">${report.clients_content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
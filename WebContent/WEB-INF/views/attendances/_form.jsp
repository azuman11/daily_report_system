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
<h3>現在時刻</h3><br />
<%-- ブラウザ依存だが、input type="date"とするとカレンダー入力もできるようになる --%>
<h3><fmt:formatDate value='${attendance.attendance_date}' pattern='yyyy年MM月dd日' /></h3><br />
<h3><fmt:formatDate value='${attendance.going_time}' pattern='HH時mm分ss秒' /></h3><br />
<br />


<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">出退勤登録</button>
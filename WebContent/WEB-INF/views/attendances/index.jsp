<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>出退勤時間　一覧</h2>
        <table id="attendance_list">
            <tbody>
                <tr>
                    <th class="attendance_date">日付</th>
                    <th class="going_time">出勤時間</th>
                    <th class="leave_time">退勤時間</th>
                </tr>
                <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="attendance_date"><fmt:formatDate value='${attendance.attendance_date}' pattern='yyyy年MM月dd日' /></td>
                        <td class="going_time"><fmt:formatDate value='${attendance.going_time}' pattern='HH時mm分ss秒' /></td>
                        <td class="leave_time"><fmt:formatDate value='${attendance.leave_time}' pattern='HH時mm分ss秒' /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${attendances_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((attendances_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/attendances/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/attendances/new' />">出退勤時間の登録</a></p>

    </c:param>
</c:import>
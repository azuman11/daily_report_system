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
        <h2>フォローしている日報　一覧</h2>
        <table id="follows_reports_list">
            <tbody>
                <tr>
                    <th class="follows_reports_name">氏名</th>
                    <th class="follows_reports_date">日付</th>
                    <th class="follows_reports_title">タイトル</th>
                    <th class="follows_reports_approval">承認状態</th>
                    <th class="follows_reports_action">操作</th>
                </tr>
                 <c:forEach var="follows_reports" items="${follows_reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follows_reports_name"><a href="<c:url value='/follows/edit?id=${follows_reports.id}' />"><c:out value="${follows_reports.employee.name}" /></a></td>
                        <td class="follows_reports_date"><fmt:formatDate value='${follows_reports.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="follows_reports_title">${follows_reports.title}</td>
                        <td class="follows_reports_approval">
                        <c:choose>
                            <c:when test="${follows_reports.approval == 0}">未承認</c:when>
                            <c:otherwise>承認済み</c:otherwise>
                        </c:choose>
                        </td>
                        <td class="follows_reports_action"><a href="<c:url value='/reports/show?id=${follows_reports.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <div id="pagination">
            （全 ${follow_report_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((follow_report_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follows_reports/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>


        <p><a href="<c:url value='/reports/index' />">日報一覧へ</a></p>


    </c:param>
</c:import>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>未承認日報　確認ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        </tr>
                        <tr>
                        <%--本来は中身の改行を、改行記号を <br />に変換しなくてはいけないが <pre>を使いそのままに。--%>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>顧客名</th>
                            <td><c:out value="${report.clients_id.name}" /></td>
                        </tr>
                        <tr>
                        <%--本来は中身の改行を、改行記号を <br />に変換しなくてはいけないが <pre>を使いそのままに。--%>
                            <th>商談内容</th>
                            <td>
                                <pre><c:out value="${report.clients_content}" /></pre>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <%-- ログイン者が作成者の場合のみ表示。 IDが同じかで判断 --%>

                <p><a href="<c:url value='/approvals/edit?id=${report.id}' />">この日報を承認する</a></p>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/approvals/index' />">未承認日報一覧に戻る</a></p>
    </c:param>
</c:import>
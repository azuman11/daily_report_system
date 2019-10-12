package controllers.approvals;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

//EmployeesIndexServletとほぼ一緒
/**
 * Servlet implementation class ReportsIndexServlet
 */
@WebServlet("/approvals/index")
public class ApprovalsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Report> notApprovalreports = em.createNamedQuery("getNotApprovalReports", Report.class)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  // 結果をgetResultList()メソッドで、リスト形式で取得
                                  .getResultList();

        // 全件数を取得
        long notApprovalreports_count = (long)em.createNamedQuery("getNotApprovalCount", Long.class)
                                       .getSingleResult();

        em.close();

        // リクエストスコープ(controller)にセット
        request.setAttribute("notApprovalreports", notApprovalreports);
        request.setAttribute("notApprovalreports_count", notApprovalreports_count);
        request.setAttribute("page", page);

        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/approvals/index.jsp");
        rd.forward(request, response);
    }
}
package controllers.goods;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class GoodsDestroyServlet
 */
@WebServlet("/goods/destroy")
public class GoodsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
            Employee employee = (Employee)request.getSession().getAttribute("login_employee");
            Integer report_id = r.getId();


            try {
                Good g = em.createNamedQuery("checkGoods", Good.class)
                        .setParameter("employee", employee)
                        .setParameter("report_id", report_id)
                        .getSingleResult();

                //DBの方を更新
                em.getTransaction().begin();
                em.remove(g); //
                em.getTransaction().commit();
                em.close();
                //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
                request.getSession().setAttribute("flush", "いいね削除が完了しました。");

                //リダイレクト
                response.sendRedirect(request.getContextPath() + "/goods/index");


            } catch (Exception e) {

                em.close();

                //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
                request.getSession().setAttribute("flush", "いいね削除に失敗しました。");

                //リダイレクト
                response.sendRedirect(request.getContextPath() + "/goods/index");
            }

        }
    }

}

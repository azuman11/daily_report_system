package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsDestroyServlet
 */
@WebServlet("/follows/destroy")
public class FollowsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsDestroyServlet() {
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

            Employee follow_followee = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
            Employee follow_employee = ((Employee)request.getSession().getAttribute("login_employee"));


            Follow follow_date = (Follow)em.createNamedQuery("checkFollows", Follow.class)
                    .setParameter("employee", follow_employee)
                    .setParameter("followee", follow_followee)
                    .getSingleResult();

            //DBの方を更新
            em.getTransaction().begin();
            em.remove(follow_date); //
            em.getTransaction().commit();
            em.close();
            //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
            request.getSession().setAttribute("flush", "フォロー削除が完了しました。");

            //リダイレクト
            response.sendRedirect(request.getContextPath() + "/follows/index");
        }
    }

}
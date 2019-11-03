package controllers.follows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsIndexServlet
 */
@WebServlet("/follows/index")
public class FollowsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsIndexServlet() {
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

        Follow f = new Follow();
        f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
        Employee employee = f.getEmployee();

        List<Follow> follows = em.createNamedQuery("getMyAllFollows", Follow.class)
                .setParameter("employee", employee)
                // 結果をgetResultList()メソッドで、リスト形式で取得
                .getResultList();

        ListIterator<Follow> iterator = follows.listIterator();

        List<Report> follows_reports_all = new ArrayList<Report>();


        while (iterator.hasNext()) {
            Follow fff = iterator.next();
            Employee eee = fff.getFollowee();

            List<Report> lll = em.createNamedQuery("getFollowAllReports", Report.class)
                    .setParameter("employee", eee)
                    .getResultList();

            follows_reports_all.addAll(lll);
        }


        Integer follow_report_count = follows_reports_all.size();

        List<Report> follows_reports = follows_reports_all.subList(15 * (page - 1), Math.min(15 * page, follow_report_count));




        em.close();

        request.setAttribute("follows_reports", follows_reports);
        request.setAttribute("page", page);
        request.setAttribute("follow_report_count", follow_report_count);


        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/index.jsp");
        rd.forward(request, response);
    }
}


package controllers.follows;

import java.io.IOException;

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
 * Servlet implementation class FollowsNewServlet
 */
@WebServlet("/follows/edit")
public class FollowsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        Employee follow_employee = ((Employee)request.getSession().getAttribute("login_employee"));
        Employee follow_followee = r.getEmployee();


        try {
            Follow follow_date = (Follow)em.createNamedQuery("checkFollows", Follow.class)
                    .setParameter("employee", follow_employee)
                    .setParameter("followee", follow_followee)
                    .getSingleResult();



            em.close();
            request.setAttribute("report", r);
            request.setAttribute("follow_date", follow_date);



            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/destroy.jsp");
            rd.forward(request, response);



        } catch (Exception e) {


            em.close();
            //request.setAttribute("follow", f);
            request.setAttribute("report", r);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/edit.jsp");
            rd.forward(request, response);


        }


    }

}
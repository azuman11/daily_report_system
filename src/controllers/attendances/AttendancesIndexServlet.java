package controllers.attendances;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

//EmployeesIndexServletとほぼ一緒
/**
 * Servlet implementation class AttendancesIndexServlet
 */
@WebServlet("/attendances/index")
public class AttendancesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
      //自分の限定になっている。が表示の仕方は同じ。
        List<Attendance> attendances = em.createNamedQuery("getMyAllAttendances", Attendance.class)
                                  .setParameter("employee", login_employee)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long attendances_count = (long)em.createNamedQuery("getMyAttendancesCount", Long.class)
                                     .setParameter("employee", login_employee)
                                     .getSingleResult();

        em.close();

        // リクエストスコープ(controller)にセット
        request.setAttribute("attendances", attendances);
        request.setAttribute("attendances_count", attendances_count);
        request.setAttribute("page", page);

        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/index.jsp");
        rd.forward(request, response);
    }
}

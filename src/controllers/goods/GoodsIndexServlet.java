package controllers.goods;

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
import models.Good;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class GoodsIndexServlet
 */
@WebServlet("/goods/index")
public class GoodsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsIndexServlet() {
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

        Good g = new Good();
        g.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
        Employee employee = g.getEmployee();

        List<Good> goods = em.createNamedQuery("getMyAllGoods", Good.class)
                .setParameter("employee", employee)
                // 結果をgetResultList()メソッドで、リスト形式で取得
                .getResultList();

        ListIterator<Good> iterator = goods.listIterator();
        List<Report> goods_reports_all = new ArrayList<Report>();


        while (iterator.hasNext()) {
            Good ggg = iterator.next();
            Integer eee = ggg.getReport_id();

            Report r = em.find(Report.class, eee);

            goods_reports_all.add(r);
        }


        Integer goods_report_count = goods_reports_all.size();
        List<Report> goods_reports = goods_reports_all.subList(15 * (page - 1), Math.min(15 * page, goods_report_count));


        em.close();

        request.setAttribute("goods_reports", goods_reports);
        request.setAttribute("page", page);
        request.setAttribute("goods_report_count", goods_report_count);


        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/goods/index.jsp");
        rd.forward(request, response);
    }
}

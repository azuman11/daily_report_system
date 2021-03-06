package controllers.clients;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import utils.DBUtil;

/**
 * Servlet implementation class ClientsIndexServlet
 */
@WebServlet("/clients/index")
public class ClientsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Client> clients = em.createNamedQuery("getAllClients", Client.class)
                                     .setFirstResult(15 * (page - 1))
                                     .setMaxResults(15)
                                     // 結果をgetResultList()メソッドで、リスト形式で取得
                                     .getResultList();

        // 全件数を取得
        long clients_count = (long)em.createNamedQuery("getClientsCount", Long.class)
                                       .getSingleResult();

        em.close();

        // リクエストスコープ(controller)にセット
        request.setAttribute("clients", clients);
        request.setAttribute("clients_count", clients_count);
        request.setAttribute("page", page);

        //セッションにフラッシュがあったら、フラをセッションからリクエストに移動し、セッションからは削除。
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/index.jsp");
        rd.forward(request, response);
    }
}
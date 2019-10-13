package controllers.clients;

import java.io.IOException;

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
 * Servlet implementation class ClientsShowServlet
 */
@WebServlet("/clients/show")
public class ClientsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //箱を作る
        EntityManager em = DBUtil.createEntityManager();

        //中身を入れる。
        //パラメーターから得たIDの人の情報をeに詰める。
        Client c = em.find(Client.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        //リクエストスコープで送る。
        request.setAttribute("client", c);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/show.jsp");
        rd.forward(request, response);
    }

}
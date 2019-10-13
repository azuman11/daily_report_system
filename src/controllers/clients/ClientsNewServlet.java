package controllers.clients;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;

/**
 * Servlet implementation class ClientsNewServlet
 */
@WebServlet("/clients/new")
public class ClientsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // CSRF対策 セキュリティー対策
        request.setAttribute("_token", request.getSession().getId());
        // インスタンスの生成 文字数0のデータの生成
        request.setAttribute("client", new Client());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/new.jsp");
        rd.forward(request, response);
    }
}
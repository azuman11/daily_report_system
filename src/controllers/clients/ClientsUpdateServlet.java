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
import models.validators.ClientValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ClientsUpdateServlet
 */
@WebServlet("/clients/update")
public class ClientsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            //EntityManagerの作成 Entityは箱。
            EntityManager em = DBUtil.createEntityManager();

            Client c = em.find(Client.class, (Integer)(request.getSession().getAttribute("client_id")));

            c.setCompany(request.getParameter("company"));
            c.setName(request.getParameter("name"));
            c.setContact_address(request.getParameter("contact_address"));


            // バリデーション
            List<String> errors = ClientValidator.validate(c);
            //編集画面のフォームに戻る
            if(errors.size() > 0) {
                em.close();

                // フォームに変更後の中身は残しつつ、さらにエラーメッセージ付きで編集画面に戻す。
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("client", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/edit.jsp");
                rd.forward(request, response);
            } else {

                //DBの方を更新
                em.getTransaction().begin();
                //commit 登録
                em.getTransaction().commit();
                //リダイレクト時に消えてしまうので、フラッシュメッセージをセッションスコープに保存し、index.jspを呼出時にセッションスコープ表示
                request.getSession().setAttribute("flush", "更新が完了しました。");
                em.close();

                // セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("client_id");

                // indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/clients/index");
            }
            }
        }
    }


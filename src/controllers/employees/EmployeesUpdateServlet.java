package controllers.employees;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/employees/update")
public class EmployeesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesUpdateServlet() {
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

            Employee e = em.find(Employee.class, (Integer)(request.getSession().getAttribute("employee_id")));

            //一度エラーと仮定し、
            Boolean code_duplicate_check = true;
            //codeが一緒ならば、エラー認定解除。
            if(e.getCode().equals(request.getParameter("code"))) {
                code_duplicate_check = false;
            //一緒でなければ、エラーのまま。
            } else {
                e.setCode(request.getParameter("code"));
            }

            // パスワード欄に入力があったら
            // パスワードの入力値チェックを行う指定をする
            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                e.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            }

            e.setName(request.getParameter("name"));
            e.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            e.setDelete_flag(0);

            // バリデーション
            List<String> errors = EmployeeValidator.validate(e, code_duplicate_check, password_check_flag);
            //編集画面のフォームに戻る
            if(errors.size() > 0) {
                em.close();

                // フォームに変更後の中身は残しつつ、さらにエラーメッセージ付きで編集画面に戻す。
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
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
                request.getSession().removeAttribute("employee_id");

                // indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }
    }

}
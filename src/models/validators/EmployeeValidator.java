package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

// 入力内容のチェック(バリデーション) error認識
public class EmployeeValidator {
    public static List<String> validate(Employee e, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        //code_duplicate_check_flagで社員番号入力確認が必要な時だけを判断している。
        String code_error = _validateCode(e.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(e.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        //password_check_flagでpw入力確認が必要な時だけを判断している。
        String password_error = _validatePassword(e.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // 社員番号
    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        // 入力チェック
        if(code == null || code.equals("")) {
            return "社員番号を入力してください。";
        }

        // 既にある社員番号との重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long employees_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("code", code)
                                             .getSingleResult();
            em.close();
            if(employees_count > 0) {
                return "入力された社員番号の情報は既に存在しています。";
            }
        }

        return "";
    }

    // 社員名チェック
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // pwチェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // pwを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
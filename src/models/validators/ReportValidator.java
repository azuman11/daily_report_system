package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;

// バリデーション=入力したかチェック error認識
public class ReportValidator {
    public static List<String> validate(Report r) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(r.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        String clients_content_error = _validateClients(r.getClients_content());
        if(!clients_content_error.equals("")) {
            errors.add(clients_content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }

        return "";
    }

    private static String _validateClients(String clients_content) {
        if(clients_content == null || clients_content.equals("")) {
            return "商談内容を入力してください。";
            }

        return "";
    }

}
package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Client;

// 入力内容のチェック(バリデーション) error認識
public class ClientValidator {
    public static List<String> validate(Client c) {
        List<String> errors = new ArrayList<String>();

        //nameはいつでも確認。
        String company_error = _validateCompany(c.getCompany());
        if(!company_error.equals("")) {
            errors.add(company_error);
        }

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String contact_address_error = _validateContact_address(c.getContact_address());
        if(!contact_address_error.equals("")) {
            errors.add(contact_address_error);
        }

        return errors;
    }

    //企業名
    private static String _validateCompany(String company) {
        if(company == null || company.equals("")) {
            return "企業名を入力してください。";
        }

        return "";
    }
    // 顧客名チェック
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // 社員名チェック
    private static String _validateContact_address(String contact_address) {
        if(contact_address == null || contact_address.equals("")) {
            return "連絡先を入力してください。";
        }

        return "";
    }

}
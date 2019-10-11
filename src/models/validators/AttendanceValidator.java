package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Attendance;

// バリデーション=入力したかチェック error認識
public class AttendanceValidator {
    public static List<String> validate(Attendance a) {
        List<String> errors = new ArrayList<String>();

        String attendance_date_error = _validateAttendance_date(a.getAttendance_date());
        if(!attendance_date_error.equals("")) {
            errors.add(attendance_date_error);
        }
/*
        String going_time_error = _validateGoing_time(a.getGoing_time());
        if(!going_time_error.equals("")) {
            errors.add(going_time_error);
        }
*/

        return errors;
    }

    private static String _validateAttendance_date(Date attendance_date) {
        if(attendance_date == null || attendance_date.equals("")) {
            return "Attendance_dateを入力してください。";
            }

        return "";
    }
/*
    private static String _validateGoing_time(Timestamp going_time) {
        if(going_time == null || going_time.equals("")) {
            return "Going_timeを入力してください。";
            }

        return "";
    }
*/
}
package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "attendances")
@NamedQueries({
    //全てのレポートを取り出す
    @NamedQuery(
            name = "getAllAttendances",
            query = "SELECT a FROM Attendance AS a ORDER BY a.id DESC"
            ),
    //レポートの総数カウント
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a"
            ),
    //トップページに自分の日報の一覧表示用 WHERE r.employee = :employeeで指定。
    //自分の レポートを全て取り出す
    @NamedQuery(
            name = "getMyAllAttendances",
            query = "SELECT a FROM Attendance AS a WHERE a.employee = :employee ORDER BY a.id DESC"
            ),
    @NamedQuery(
            name = "getMyTodayAttendances",
            query = "SELECT a FROM Attendance AS a WHERE a.attendance_date = :attendance_date AND a.employee = :employee"
            ),
    //自分の レポートの総数カウント
    @NamedQuery(
            name = "getMyAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a WHERE a.employee = :employee"
            )
})

@Entity
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 一対多 従業員IDをレポートでも保存することで、作成者情報を引っ張ってこれる。
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    //  年月日までの情報 != timestampミリ秒まで
    @Column(name = "attendance_date", nullable = false)
    private Date attendance_date;

    @Column(name = "going_time", nullable = false)
    private Timestamp going_time;

    @Column(name = "leave_time", nullable = true)
    private Timestamp leave_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public Timestamp getGoing_time() {
        return going_time;
    }

    public void setGoing_time(Timestamp going_time) {
        this.going_time = going_time;
    }

    public Timestamp getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Timestamp leave_time) {
        this.leave_time = leave_time;
    }
}
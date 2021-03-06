package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({
    //全てのレポートを取り出す
    @NamedQuery(
            name = "getAllReports",
            query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
            ),
    //レポートの総数カウント
    @NamedQuery(
            name = "getReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r"
            ),
    @NamedQuery(
            name = "getNotApprovalReports",
            query = "SELECT r FROM Report AS r WHERE r.approval = 0 ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getNotApprovalCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.approval = 0"
            ),
    //トップページに自分の日報の一覧表示用 WHERE r.employee = :employeeで指定。
    //自分の レポートを全て取り出す
    @NamedQuery(
            name = "getMyAllReports",
            query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
            ),
    //フォロワーのレポートを全て取り出す
    @NamedQuery(
            name = "getFollowAllReports",
            query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
            ),
    //id一致のレポート取得
    @NamedQuery(
            name = "getIdReports",
            query = "SELECT r FROM Report AS r WHERE r.id = :id "
            ),
    //フォロワーの レポートの総数カウント
    @NamedQuery(
            name = "getFollowsReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
            ),
    //自分の レポートの総数カウント
    @NamedQuery(
            name = "getMyReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
            )
})
@Entity
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 一対多 従業員IDをレポートでも保存することで、作成者情報を引っ張ってこれる。
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    //  年月日までの情報 != timestampミリ秒まで
    @Column(name = "report_date", nullable = false)
    private Date report_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "approval", nullable = true)
    private Integer approval;

    // @Lob 改行も改行として保存
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @ManyToOne
    @JoinColumn(name = "clients_id", nullable = true)
    private Client clients_id;

    @Lob
    @Column(name = "clients_content", nullable = true)
    private String clients_content;

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

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    public Integer getApproval() {
        return approval;
    }

    public void setApproval( Integer approval) {
        this.approval = approval;
    }

    public Client getClients_id() {
        return clients_id;
    }

    public void setClients_id(Client clients_id) {
        this.clients_id = clients_id;
    }

    public String getClients_content() {
        return clients_content;
    }

    public void setClients_content(String clients_content) {
        this.clients_content = clients_content;
    }

}
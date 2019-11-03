package models;

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


// AND g.report_id = :report_id　
//DTO
@Table(name = "gooods")
//クエリ=問い合わせ あらかじめ名前のついているクエリ=namedquery,
@NamedQueries({
  @NamedQuery(
          name = "checkGoods",
          query = "SELECT g FROM Good AS g WHERE g.employee = :employee AND g.report_id = :report_id"
          ),
  @NamedQuery(
          //自分のフォロー状況
          name = "getMyAllGoods",
          query = "SELECT g FROM Good AS g WHERE g.employee = :employee ORDER BY g.id DESC"
          )
})
@Entity
public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //従業員。
    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    //フォロー相手。
    @Column(name = "report_id", nullable = false)
    private Integer report_id;

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

    public Integer getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }
}
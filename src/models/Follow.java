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

//DTO
@Table(name = "follows")
//クエリ=問い合わせ あらかじめ名前のついているクエリ=namedquery,
@NamedQueries({
    @NamedQuery(
            name = "checkFollows",
            query = "SELECT f FROM Follow AS f WHERE f.employee = :employee AND f.followee = :followee"
            ),
    @NamedQuery(
            //自分のフォロー状況
            name = "getMyAllFollows",
            query = "SELECT f FROM Follow AS f WHERE f.employee = :employee ORDER BY f.id DESC"
            )
})
@Entity
public class Follow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //従業員。
    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    //フォロー相手。
    @ManyToOne
    @JoinColumn(name = "followee", nullable = false)
    private Employee followee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getFollowee() {
        return followee;
    }

    public void setFollowee(Employee followee) {
        this.followee = followee;
    }




}

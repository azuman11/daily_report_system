package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//DTO
@Table(name = "employees")
//クエリ=問い合わせ あらかじめ名前のついているクエリ=namedquery
@NamedQueries({
    @NamedQuery(
            //すべての従業員情報
            name = "getAllEmployees",
            query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
            ),
    @NamedQuery(
            //従業員情報の全件数を取得
            name = "getEmployeesCount",
            query = "SELECT COUNT(e) FROM Employee AS e"
            ),
    @NamedQuery(
            //社員番号がDB内において既知のものか確認
            name = "checkRegisteredCode",
            query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"
            ),
    @NamedQuery(
            //従業員がログイン時、社員番号とpwを確認。
            name = "checkLoginCodeAndPassword",
            query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass"
            )
})
@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //unique = 一意制約, 唯一無二
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    //SHA256は64文字になる
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    //管理者1か否か0→上長2,3
    @Column(name = "admin_flag", nullable = false)
    private Integer admin_flag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    //在籍者0か否か1
    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin_flag() {
        return admin_flag;
    }

    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
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

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
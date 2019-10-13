package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//DTO
@Table(name = "clients")
//クエリ=問い合わせ あらかじめ名前のついているクエリ=namedquery
@NamedQueries({
    @NamedQuery(
            //すべての従業員情報
            name = "getAllClients",
            query = "SELECT c FROM Client AS c ORDER BY c.id DESC"
            ),
    @NamedQuery(
            //従業員情報の全件数を取得
            name = "getClientsCount",
            query = "SELECT COUNT(c) FROM Client AS c"
            )
})
@Entity
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_address", nullable = false)
    private String contact_address;

  //unique = 一意制約, 唯一無二

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }


}
package ua.opinta.core;

//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Administrator on 6/24/2016.
 */
@Entity
@Table(name = "role")
public class Role
//        implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    public Role() {}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

//    @Override
//    public String getAuthority() {
//        return name;
//    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

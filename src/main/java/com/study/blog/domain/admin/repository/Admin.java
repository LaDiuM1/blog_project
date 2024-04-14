package com.study.blog.domain.admin.repository;

import com.study.blog.domain.common.BaseTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Admin extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean status = true;

    public Admin() {}

    public Admin(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void updatePasswordAndName(String password, String name) {
        this.password = password;
        this.name = name;
    }

}

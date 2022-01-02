package com.palash.webXGlobal.entities;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @Id
    @SequenceGenerator(name= "USER_SQ", sequenceName = "USER_SQ_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="USER_SQ")
    private Long id;

    @Column
    @NotEmpty
    private String name;

    @Column(length = 20, unique = true)
    @NotEmpty(message = "*Username cannot be empty")
    private String username;

    @Column
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    public Users(Users appUser) {
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
    }

    public Users() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

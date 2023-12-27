package com.ecommerce.model.user;

import com.ecommerce.util.enums.Role;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;

    private Civility civility;
    private String firstName;

    private String lastName;

    private String email;
    private Set<Role> role = new HashSet<>();

    private String password;

    public static Info toInfo(User user) {
        Info info = new Info();
        info.setCivility(user.getCivility());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        return info;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public Civility getCivility() {
        return civility;
    }
}

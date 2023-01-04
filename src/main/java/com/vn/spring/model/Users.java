package com.vn.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Users")
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Transient
    @Column(name = "age")
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "image")
    private String image;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "macAddress")
    private String macAddress;

    @Column(name = "userAgent", nullable = false)
    private String userAgent;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updateAt")
    private Date updateAt;

    public Users(Long id,
                 String firstName,
                 String lastName,
                 String gender,
                 String email,
                 String username,
                 String password,
                 LocalDate birthDate,
                 String image,
                 String ip,
                 String macAddress,
                 String userAgent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.ip = ip;
        this.macAddress = macAddress;
        this.userAgent = userAgent;
    }

    public Users(String firstName,
                 String lastName,
                 String gender,
                 String email,
                 String username,
                 String password,
                 LocalDate birthDate,
                 String image,
                 String ip,
                 String macAddress,
                 String userAgent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.ip = ip;
        this.macAddress = macAddress;
        this.userAgent = userAgent;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Users users = (Users) o;
        return id != null && Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.vn.spring.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity(name = "Users")
@Table
@NoArgsConstructor
@Setter
@ToString
public class Users {
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getImage() {
        return image;
    }

    public String getIp() {
        return ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getUserAgent() {
        return userAgent;
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

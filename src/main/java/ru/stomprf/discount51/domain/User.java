package ru.stomprf.discount51.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
//    @Column(nullable = false)
//    private int sale;
    @Column(name = "sale")
    private Sale sale;

    public User(String firstName, String phoneNumber, Sale sale) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.sale = sale;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}

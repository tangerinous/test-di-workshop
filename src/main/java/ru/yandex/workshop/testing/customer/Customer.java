package ru.yandex.workshop.testing.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String segment;

    @Column(nullable = false)
    private int loyaltyLevel;

    protected Customer() {
    }

    public Customer(Long id, String email, String name, boolean active, String segment, int loyaltyLevel) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.active = active;
        this.segment = segment;
        this.loyaltyLevel = loyaltyLevel;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getSegment() {
        return segment;
    }

    public int getLoyaltyLevel() {
        return loyaltyLevel;
    }
}

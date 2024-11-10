package com.dean.glot_spot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean is2faEnabled = false; // Default value

    @Column(nullable = true)
    private String twofaBackupEmail;

    @Column(nullable = true)
    private String twofaSecretKey;

    @Column(nullable = true)
    private String magicLinkToken;

    @Column(nullable = true)
    private LocalDateTime magicLinkExpiry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum AccountStatus {
        ACTIVE,
        INACTIVE
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs2faEnabled() {
        return is2faEnabled;
    }

    public void setIs2faEnabled(Boolean is2faEnabled) {
        this.is2faEnabled = is2faEnabled;
    }

    public String getTwofaBackupEmail() {
        return twofaBackupEmail;
    }

    public void setTwofaBackupEmail(String twofaBackupEmail) {
        this.twofaBackupEmail = twofaBackupEmail;
    }

    public String getTwofaSecretKey() {
        return twofaSecretKey;
    }

    public void setTwofaSecretKey(String twofaSecretKey) {
        this.twofaSecretKey = twofaSecretKey;
    }

    public String getMagicLinkToken() {
        return magicLinkToken;
    }

    public void setMagicLinkToken(String magicLinkToken) {
        this.magicLinkToken = magicLinkToken;
    }

    public LocalDateTime getMagicLinkExpiry() {
        return magicLinkExpiry;
    }

    public void setMagicLinkExpiry(LocalDateTime magicLinkExpiry) {
        this.magicLinkExpiry = magicLinkExpiry;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

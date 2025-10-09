package com.matean.hardwaremanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String description;
    private String category; // Added to match DDL
    private String quantity; // Changed from Integer to String to match DDL
    private Double wholesalePrice;
    private Double retailPrice;
    private String wholesalePriceUnit;
    private String retailPriceUnit;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String updatedBy;

    @JdbcTypeCode(SqlTypes.JSON)
    private String location; // Changed to String to match DDL's jsonb (as a string representation)
    private boolean isDeleted = false; // Renamed from 'deleted' to 'isDeleted' and type to primitive boolean to match DDL

    @Column(name = "image_url")
    private String imageUrl;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getWholesalePriceUnit() {
        return wholesalePriceUnit;
    }

    public void setWholesalePriceUnit(String wholesalePriceUnit) {
        this.wholesalePriceUnit = wholesalePriceUnit;
    }

    public String getRetailPriceUnit() {
        return retailPriceUnit;
    }

    public void setRetailPriceUnit(String retailPriceUnit) {
        this.retailPriceUnit = retailPriceUnit;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

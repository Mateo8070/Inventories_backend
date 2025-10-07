package com.matean.hardwaremanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class HardwareDto {
    private UUID id;
    private String description;
    private String quantity;
    private double wholesalePrice;
    private double retailPrice;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private String wholesalePriceUnit;
    private String retailPriceUnit;
    private String updatedBy;
    private String location;
    private CategoryDto category; // Include CategoryDto

    // Constructors
    public HardwareDto() {
    }

    public HardwareDto(UUID id, String description, String quantity, double wholesalePrice, double retailPrice,
                       LocalDateTime updatedAt, boolean isDeleted, String wholesalePriceUnit, String retailPriceUnit,
                       String updatedBy, String location, CategoryDto category) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.wholesalePriceUnit = wholesalePriceUnit;
        this.retailPriceUnit = retailPriceUnit;
        this.updatedBy = updatedBy;
        this.location = location;
        this.category = category;
    }

    // Getters and Setters
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}

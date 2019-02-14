package com.pgmanagement.pgadmin.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

public class ZipCode implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Integer activeFlag;

    private String placeName;

    private Integer zipCode;

    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "id=" + id +
                ", activeFlag=" + activeFlag +
                ", placeName='" + placeName + '\'' +
                ", zipCode=" + zipCode +
                ", city=" + city +
                '}';
    }
}

package com.pgmanagement.pgadmin.model.master;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Integer activeFlag;

    private Integer countryIsdCode;

    private String countryIsoCode;

    private String countryName;

    private String nationality;

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

    public Integer getCountryIsdCode() {
        return countryIsdCode;
    }

    public void setCountryIsdCode(Integer countryIsdCode) {
        this.countryIsdCode = countryIsdCode;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", activeFlag=" + activeFlag +
                ", countryIsdCode=" + countryIsdCode +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}

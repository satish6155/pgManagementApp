package com.pgmanagement.pgadmin.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

public class State implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Integer activeFlag;

    private Integer stateCode;

    private String stateName;

    private Country country;

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

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", activeFlag=" + activeFlag +
                ", stateCode=" + stateCode +
                ", stateName='" + stateName + '\'' +
                ", country=" + country +
                '}';
    }
}

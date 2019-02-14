package com.pgmanagement.pgadmin.model.master;

import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Integer activeFlag;

    private String cityCode;

    private String cityName;

    private String stdCode;

    private State state;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", activeFlag=" + activeFlag +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", stdCode='" + stdCode + '\'' +
                ", state=" + state +
                '}';
    }
}

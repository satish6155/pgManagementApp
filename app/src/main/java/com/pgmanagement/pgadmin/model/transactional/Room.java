package com.pgmanagement.pgadmin.model.transactional;


import java.io.Serializable;
import java.sql.Timestamp;

public class Room implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private String code;

    private Integer noOfSeats;

    private Timestamp creationTimeStamp;

    private Timestamp lastUpdatedTimeStamp;

    private Long createdBy;

    private Long lastUpdatedBy;

    private Integer activeFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {

        if (activeFlag == null) {
            activeFlag = 1;
        }
        this.activeFlag = activeFlag;
    }

    public Timestamp getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Timestamp creationTimeStamp) {

        if (creationTimeStamp == null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            creationTimeStamp = timestamp;
        }
        this.creationTimeStamp = creationTimeStamp;
    }

    public Timestamp getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public void setLastUpdatedTimeStamp(Timestamp lastUpdatedTimeStamp) {

        if (lastUpdatedTimeStamp == null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            lastUpdatedTimeStamp = timestamp;
        }
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", creationTimeStamp=" + creationTimeStamp +
                ", lastUpdatedTimeStamp=" + lastUpdatedTimeStamp +
                ", createdBy=" + createdBy +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", activeFlag=" + activeFlag +
                '}';
    }
}
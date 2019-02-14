package com.pgmanagement.pgadmin.model.transactional;

import com.pgmanagement.pgadmin.model.master.City;
import com.pgmanagement.pgadmin.model.master.Country;
import com.pgmanagement.pgadmin.model.master.District;
import com.pgmanagement.pgadmin.model.master.GenericParameter;
import com.pgmanagement.pgadmin.model.master.State;
import com.pgmanagement.pgadmin.model.master.ZipCode;

import java.io.Serializable;
import java.sql.Timestamp;

public class Address implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private Timestamp creationTimeStamp;

    private Timestamp lastUpdatedTimeStamp;

    private Long createdBy;

    private Long lastUpdatedBy;

    private Integer activeAddress;

    private String addressLine1;

    private String addressLine2;

    private ZipCode zipCode;

    private GenericParameter addresstype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Timestamp creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Timestamp getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public void setLastUpdatedTimeStamp(Timestamp lastUpdatedTimeStamp) {
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

    public Integer getActiveAddress() {
        return activeAddress;
    }

    public void setActiveAddress(Integer activeAddress) {
        this.activeAddress = activeAddress;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public GenericParameter getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(GenericParameter addresstype) {
        this.addresstype = addresstype;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", creationTimeStamp=" + creationTimeStamp +
                ", lastUpdatedTimeStamp=" + lastUpdatedTimeStamp +
                ", createdBy=" + createdBy +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", activeAddress=" + activeAddress +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", zipCode=" + zipCode.getZipCode() +
                ", addresstype=" + addresstype +
                '}';
    }
}

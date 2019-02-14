package com.pgmanagement.pgadmin.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

public class GenericParameter implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    private Long id;

    private String code;

    private String description;

    private String dtype;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "GenericParameter{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", dtype='" + dtype + '\'' +
                ", activeFlag=" + activeFlag +
                '}';
    }
}

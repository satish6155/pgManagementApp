package com.pgmanagement.pgadmin.model.transactional;

import com.pgmanagement.pgadmin.model.master.GenericParameter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


public class User implements Serializable {

    private static final long serialVersionUID = -1232395859408322328L;

    //@SerializedName("ID")   ---if jSon has ID instead of id
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private long mobileNumber;

    private long age;

    private GenericParameter gender;

    private GenericParameter role;

    private String email;

    private Timestamp creationTimeStamp;

    private Timestamp lastUpdatedTimeStamp;

    private Long createdBy;

    private Long lastUpdatedBy;

    private Integer status;

    private List<Address> addresses;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public GenericParameter getGender() {
        return gender;
    }

    public void setGender(GenericParameter gender) {
        this.gender = gender;
    }

    public GenericParameter getRole() {
        return role;
    }

    public void setRole(GenericParameter role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", creationTimeStamp=" + creationTimeStamp +
                ", lastUpdatedTimeStamp=" + lastUpdatedTimeStamp +
                ", createdBy=" + createdBy +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", status=" + status +
                '}';
    }

}

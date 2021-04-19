package com.gametrade.api.presentation.dtos;

import java.util.Set;

public class UserResponse {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<ProductItemResponse> productItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<ProductItemResponse> getProductItems() {
        return productItems;
    }

    public void setProductItems(Set<ProductItemResponse> productItems) {
        this.productItems = productItems;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", productItems=" + productItems +
                '}';
    }
}

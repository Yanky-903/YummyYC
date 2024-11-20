package com.Yashraj.restaurantyummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class CustomerRequest{
    public record CustomerCreateRequest(
            @NotNull(message = "Customer first name should be present")
            @NotEmpty(message = "Customer first name should be present")
            @NotBlank(message = "Customer first name should be present")
            @JsonProperty("first_name")
            String firstName,

            @NotNull(message = "Customer last name should be present")
            @NotEmpty(message = "Customer last name should be present")
            @NotBlank(message = "Customer last name should be present")
            @JsonProperty("last_name")
            String lastName,

            @NotNull(message = "Customer email is required")
            @Email(message = "Email must be in correct format")
            @JsonProperty("email")
            String email,

            @NotNull(message = "Password should be present")
            @NotEmpty(message = "Password should be present")
            @NotBlank(message = "Password should be present")
            @Size(min = 6, max = 12)
            @JsonProperty("password")
            String password,

            @JsonProperty("address")
            String addr,

            @JsonProperty("city")
            String city,

            @JsonProperty("pincode")
            Long pinCode
    ) {
    }


    public record CustomerUpdateRequest(
            @JsonProperty("first_name")
            String firstName,

            @JsonProperty("last_name")
            String lastName,

            @JsonProperty("city")
            String city,

            @JsonProperty("pincode")
            Long pinCode
    ) {
    }
}


// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXRoYUBnbWFpbC5jb20iLCJpYXQiOjE3MzEzOTQ5MDgsImV4cCI6MTczMTM5NDk2OH0.sEa4GDZXBH8C_zWk5Nn9dh14zYw62nbQXofgjrZq7io


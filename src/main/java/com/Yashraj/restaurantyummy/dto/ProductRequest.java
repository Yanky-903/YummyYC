package com.Yashraj.restaurantyummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductRequest {
    public record ProductCreateRequest(
            @NotNull(message = "Product name should be present")
            @NotEmpty(message = "Product name should be present")
            @NotBlank(message = "Product name should be present")
            @JsonProperty("name")
            String name,

            @NotNull(message = "Product name should be present")
            @NotEmpty(message = "Product name should be present")
            @NotBlank(message = "Product name should be present")
            @JsonProperty("price")
            BigDecimal price
    ) {
    }

    public record ProductUpdateRequest(
            @JsonProperty("name")
            String name,

            @JsonProperty("price")
            BigDecimal price
    ) {
    }
}
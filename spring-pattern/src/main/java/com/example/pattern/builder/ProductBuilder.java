package com.example.pattern.builder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBuilder {

    private String name;
    private String price;
    private String place;
    private String color;
    private String auth;

    ProductBuilder(Builder builder) {
        this.name = builder.name;
        this.place = builder.place;
        this.color = builder.color;
        this.auth = builder.auth;
        this.price = builder.price;
    }

    @Getter
    public static class Builder {
        private final String name;

        private final String price;

        private String place;

        private String color;

        private String auth;

        public Builder(String name, String price) {
            this.name = name;
            this.price = price;
        }

        public Builder setPlace(String place) {
            this.place = place;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setAuth(String auth) {
            this.auth = auth;
            return this;
        }

        public ProductBuilder builder() {
            return new ProductBuilder(this);
        }
    }

}

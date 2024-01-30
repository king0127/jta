package com.jsoft.es.domain;

import com.fasterxml.jackson.databind.ObjectReader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocDto {

    private int age;

    private String city;

    private String id;

    private String name;

    private String sex;

}

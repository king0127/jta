package com.example.pattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
    private static final long serialVersionUID = 2623842621173093832L;

    private Integer id;

    private String name;

}

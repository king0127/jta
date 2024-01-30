package com.example.pattern.decorator.mananger.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertifcatePO implements Serializable {
    private static final long serialVersionUID = 228091719604163472L;

    private String name;

    private String id;

}

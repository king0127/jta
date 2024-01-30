package com.example.pattern.domain;

import lombok.Data;

import java.util.List;

@Data
public class PartChangeReq {

    List<Long> ids;
}

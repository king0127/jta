package com.jsoft.rocket.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Base64File implements Serializable {
    private static final long serialVersionUID = 600075673117625417L;

    // 文件名称
    private String fileName;
    // base64文件流
    private String fileContent;


}

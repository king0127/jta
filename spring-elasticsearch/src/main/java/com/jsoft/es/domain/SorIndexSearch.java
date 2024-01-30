package com.jsoft.es.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "sor_index_search")
public class SorIndexSearch {
    private String subjectName;
    private String scheduleName;
    private String packageCode;
    private String partName;
    private String partCode;
}

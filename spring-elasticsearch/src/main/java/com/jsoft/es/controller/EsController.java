package com.jsoft.es.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoft.es.domain.DocDto;
import com.jsoft.es.domain.SorIndexSearch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/es")
public class EsController {


    @Resource
    private RestHighLevelClient restHighLevelClient;


    private static String vpitIndex = "vpit_index_";
    private static String sorIndex = "sor_index_";



    @PostConstruct
    public void initEsMetadata() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowSuffix = dateFormat.format(new Date());
            String index = sorIndex + nowSuffix;
            GetIndexRequest getindexRequest = new GetIndexRequest(index);

            //判断索引是否存在
            if (!restHighLevelClient.indices().exists(getindexRequest, RequestOptions.DEFAULT)) {
                File file = ResourceUtils.getFile("classpath:mapper/vpit_index.json");
                // 读取mapping配置
                String jsonStr = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
                CreateIndexRequest request = new CreateIndexRequest(index);
                XContentBuilder builder = XContentFactory.jsonBuilder().map(JSONObject.parseObject(jsonStr));
                request.mapping(builder);
                restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            }
        } catch(Exception e){
            log.error("init es metadata error:", e);
        }
    }


    @GetMapping("/createIndex/{suffix}")
    public Boolean createIndex(@PathVariable("suffix") String suffix) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(vpitIndex+"_" + suffix);
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
                .build());
        createIndexRequest.mapping("{\n" +
                "  \"properties\": {\n" +
                "    \"city\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"sex\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (IOException e) {

        }
        return false;
    }

    @GetMapping("/createIndex2")
    public Boolean createIndex2() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowSuffix = dateFormat.format(new Date());
        String index = sorIndex + nowSuffix;
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 1; i <= 100; i++) {
            SorIndexSearch sorIndexSearch = SorIndexSearch.builder()
                    .packageCode("PSOR-" + i + "-V00").partCode("1").partName("机动零件-"+i).scheduleName("初版-SOR")
                    .subjectName("X-" + i)
                    .build();
            IndexRequest indexRequest = new IndexRequest(index)
                    .id(String.valueOf(i))
                    .source(JSON.toJSONString(sorIndexSearch), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try {
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return bulkResponse.status().equals(RestStatus.OK);
        } catch (Exception e) {
            System.out.println("异常忽略！");
        }
        return false;

    }


    // 删除索引
    @GetMapping("/delIndex")
    public Boolean delIndex() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(vpitIndex);
        try {
            AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (Exception e) {
            System.out.println("异常忽略！");
        }
        return false;
    }


    // 创建文档
    @GetMapping("/createUserDocument/{suffix}")
    public Boolean createUserDocument(@PathVariable(name = "suffix") String suffix) throws Exception {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        DocDto document = DocDto.builder()
                .age(18).city("北京").sex("男").name("王安" + id)
                .build();
        document.setId(id);
        IndexRequest indexRequest = new IndexRequest(vpitIndex + "_" + suffix)
                .id(document.getId())
                .source(JSON.toJSONString(document), XContentType.JSON);
        // todo: 这里报错了，但是数据却已存储到es中了， es与springboot版本问题  直接catch吞异常
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.status().equals(RestStatus.OK);
        } catch (Exception e) {
            System.out.println("异常忽略！");
        }
        return false;
    }

    // 编辑文档
    @GetMapping("/updateDoc")
    public Boolean updateDoc() {
        DocDto document = DocDto.builder()
                .id("89d0a135-c50c-4beb-bc26-bf39d1b68b0d")
                .name("李白").age(28).city("上海").sex("nan")
                .build();
        UpdateRequest updateRequest = new UpdateRequest(vpitIndex, document.getId());
        updateRequest.doc(JSON.toJSONString(document), XContentType.JSON);
        try {
            UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            return update.status().equals(RestStatus.OK);
        } catch (Exception e) {
            log.error("吞并异常！");
        }
        return true;
    }


    @GetMapping("/query")
    public String queryIndex() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowSuffix = dateFormat.format(new Date());
        String index = sorIndex + nowSuffix;
        searchRequest.indices(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(100);
        sourceBuilder.from(0);
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            String sourceAsString = iterator.next().getSourceAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            SorIndexSearch sorIndexSearch = objectMapper.readValue(sourceAsString, SorIndexSearch.class);
            System.out.println(sorIndexSearch);
        }
        System.out.println(search);
        return null;
    }


}

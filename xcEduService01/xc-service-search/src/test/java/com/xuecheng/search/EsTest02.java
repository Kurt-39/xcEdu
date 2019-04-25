package com.xuecheng.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest02 {
    @Autowired
    RestHighLevelClient client;
    @Autowired
    RestClient restClient;
    //搜索type下的全部记录
    @Test
    public void testSearchAll() throws IOException {
        //获取搜索请求
        SearchRequest searchRequest = new SearchRequest("xc_course");
       //指定类型
        searchRequest.types("doc");
        //建立搜索源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //source原字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","description"},new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hit:hits1) {
            //获得索引名
            String index = hit.getIndex();
            //获得类型
            String type = hit.getType();
            //获得id
            String id = hit.getId();
            //获得成绩
            float score = hit.getScore();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String name = (String)sourceAsMap.get("name");
            String studymodel = (String)sourceAsMap.get("studymodel");
           String description = (String)sourceAsMap.get("description");

            System.out.println(description);
        }



    }
}

package com.xuecheng.search;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest1 {
    @Autowired
    RestHighLevelClient client;
    @Autowired
    RestClient restClient;
    //删除索引库
    /**
     * 获取删除索引请求,将索引名带入
     * ES提供的模板对象获得索引,delete

     */
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("xc_course");
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest);
        boolean acknowledged = deleteIndexResponse.isAcknowledged();
        System.out.println(acknowledged);

    }
    //创建索引库和映射

    /**
     *
     * @throws IOException
     *
     */
    @Test
    public void testBuildIndex() throws IOException {
        //获得创建索引请求
        CreateIndexRequest indexRequest = new CreateIndexRequest("xc_course");
        //指定分片数,副本数
        indexRequest.settings(Settings.builder().put("number_of_shards",1).put("number_of_replicas",0));
        //设置映射
        indexRequest.mapping("doc","{\n" +
                "\"properties\": {\n" +
                "\"description\": {\n" +
                "\"type\": \"text\",\n" +
                "\"analyzer\": \"ik_max_word\",\n" +
                "\"search_analyzer\": \"ik_smart\"\n" +
                "},\n" +
                "\"name\": {\n" +
                "\"type\": \"text\",\n" +
                "\"analyzer\": \"ik_max_word\",\n" +
                "\"search_analyzer\": \"ik_smart\"\n" +
                "},\n" +
                "\"pic\":{\n" +
                "\"type\":\"text\",\n" +
                "\"index\":false\n" +
                "},\n" +
                "\"price\": {\n" +
                "\"type\": \"float\"\n" +
                "},\n" +
                "\"studymodel\": {\n" +
                "\"type\": \"keyword\"\n" +
                "},\n" +
                "\"timestamp\": {\n" +
                "\"type\": \"date\",\n" +
                "\"format\": \"yyyy‐MM‐dd HH:mm:ss||yyyy‐MM‐dd||epoch_millis\"\n" +
                "}\n" +
                "}\n" +
                "}", XContentType.JSON);
        //build index
        IndicesClient indices = client.indices();
        //build response
        CreateIndexResponse indexResponse = indices.create(indexRequest);
        boolean acknowledged = indexResponse.isAcknowledged();
        System.out.println(acknowledged);


    }
    //添加文档
    @Test
    public void testAddDoc() throws IOException {
        //准备JSON
        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "Bootstrap开发");
        jsonMap.put("description", "");
        jsonMap.put("studymodel", "201001");
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy‐MM‐dd HH:mm:ss");
        jsonMap.put("timestamp", dateFormat.format(new Date()));
        jsonMap.put("price", 5.6f);
        //索引请求对象
        IndexRequest indexRequest = new IndexRequest("xc_course", "doc");
         //指定索引文档内容
        indexRequest.source(jsonMap);
        IndexResponse index = client.index(indexRequest);
        DocWriteResponse.Result result = index.getResult();
        System.out.println(result);

    }
    //查询文档
    @Test
    public void getDoc() throws IOException {
        GetRequest getRequest = new GetRequest("xc_course", "doc", "cvviU2oBuEJNEEOHBF-b");
        GetResponse getResponse = client.get(getRequest);
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);

    }
//更新
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("xc_course","doc","cvviU2oBuEJNEEOHBF-b");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","kurt39搏击俱乐部");
        updateRequest.doc(hashMap);
        UpdateResponse update = client.update(updateRequest);
        RestStatus status = update.status();


    }

}

package com.xuecheng.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
    //分页搜索type下的全部记录
    @Test
    public void testSearchPage() throws IOException {
        //获取搜索请求
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //建立搜索源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
       int page=1;
       int size=1;
       //公式:(page-1)*size
        //设置当前页码
        searchSourceBuilder.from((page-1)*size);
        //设置每页参数
        searchSourceBuilder.size(size);
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
    //精确匹配
    @Test
    public void testTermSearchPage() throws IOException {
        //获取搜索请求
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //建立搜索源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //建立索引数组
        String[] strings = {"1", "2"};
        List<String> list = Arrays.asList(strings);

        searchSourceBuilder.query(QueryBuilders.termsQuery("_id",list));
        int page=1;
        int size=2;
        //公式:(page-1)*size
        //设置当前页码
        searchSourceBuilder.from((page-1)*size);
        //设置每页参数
        searchSourceBuilder.size(size);
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
    //全局匹配
    @Test
    public void testMatchSearchPage() throws IOException {
        //获取搜索请求
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //建立搜索源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(QueryBuilders.matchQuery("description","前台页面开发框架").minimumShouldMatch("80%"));
        int page=1;
        int size=2;
        //公式:(page-1)*size
        //设置当前页码
        searchSourceBuilder.from((page-1)*size);
        //设置每页参数
        searchSourceBuilder.size(size);
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
    //多条目匹配
    @Test
    public void testMultiSearchPage() throws IOException {
        //获取搜索请求
        SearchRequest searchRequest = new SearchRequest("xc_course");
        //指定类型
        searchRequest.types("doc");
        //建立搜索源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("spring css","name","description").minimumShouldMatch("50%").field("name",10));

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
    //布尔查询
    @Test
    public void testBoolQuery() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest= new SearchRequest("xc_course");
        searchRequest.types("doc");
//创建搜索源配置对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(new String[]{"name","pic","studymodel","description"},new String[]{});
        String keyword = "spring开发框架";
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("spring框架", "name", "description").minimumShouldMatch("50%");
        multiMatchQueryBuilder.field("name",10);
        //TermQuery

        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel","201001"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(60).lte(100));
        //搜索源中定义排序
        searchSourceBuilder.sort("studymodel", SortOrder.DESC);
        searchSourceBuilder.sort("price",SortOrder.ASC);
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag>");
        highlightBuilder.postTags("</tag>");
        //将需要高亮的对象装进去
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("description"));
        searchSourceBuilder.highlighter(highlightBuilder);
        //设置布尔查询对象
        searchSourceBuilder.query(boolQueryBuilder);
        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        String flag = null;
        for(SearchHit hit:hits1){
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields!=null){
                HighlightField description = highlightFields.get("description");
                if (description!=null){
                    Text[] fragments = description.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text text:fragments){
                        stringBuffer.append(text);
                    }
                     flag=stringBuffer.toString();
                }
            }
            System.out.println(flag);
            

        }
    }
}

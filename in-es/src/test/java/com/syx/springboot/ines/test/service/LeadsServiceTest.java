package com.syx.springboot.ines.test.service;


import com.google.common.collect.Lists;
import com.syx.springboot.ines.test.entity.LeadsEntity;
import com.syx.springboot.ines.test.repository.LeadsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.ParsedCardinality;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static jdk.nashorn.internal.objects.Global.print;


/**
 * @author shaoyx
 * @date 11:16  2019/11/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.syx.springboot.ines"})
public class LeadsServiceTest {

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;

    @Autowired
    private LeadsService leadsService;

    @Autowired
    private LeadsRepository leadsRepository;

    private final static Random random = new Random(1);

    /**
     * 初始化数据
     *
     * @author shaoyx
     */
    @Test
    public void initData() {
        List<LeadsEntity> leadsEntityList = Lists.newArrayListWithCapacity(500);
        for (int i = 0; i < 500; i++) {
            leadsEntityList.add(createInitEntity(i));
        }
        leadsRepository.saveAll(leadsEntityList);
    }

    /**
     * 初始化数据
     *
     * @author shaoyx
     */
    @Test
    public void initDataOne() {
        leadsRepository.save(createInitEntity(1));
    }

    /**
     * 创建初识数据
     *
     * @param leadsId
     * @return
     * @author shaoyx
     */
    private LeadsEntity createInitEntity(Integer leadsId) {
        LeadsEntity leadsEntity = new LeadsEntity();
        leadsEntity.setLeads_id(leadsId);
        leadsEntity.setOut_id(Math.abs(random.nextInt()) % 100);
        leadsEntity.setLevel(Math.abs(random.nextInt()) % 7);
        leadsEntity.setSource(Math.abs(random.nextInt()) % 15);
        leadsEntity.setStatus_a(Math.abs(random.nextInt()) % 3);
        leadsEntity.setStatus_b(Math.abs(random.nextInt()) % 5);
        leadsEntity.setBiz_month(Math.abs(random.nextInt()) % 12);
        leadsEntity.setBiz_date(Math.abs(random.nextInt()) % 31);
        leadsEntity.setBiz_time(new Date());
        return leadsEntity;
    }

    @Test
    public void findAll() {
        List<LeadsEntity> leadsEntityList = Lists.newArrayList(leadsRepository.findAll());
        System.out.println(leadsEntityList);
    }

    @Test
    public void findAllById() {
        List<LeadsEntity> leadsEntityList = Lists.newArrayList(leadsRepository.findAllById(Arrays.asList(1, 2)));
        System.out.println(leadsEntityList);
    }

    @Test
    public void findById() {
        LeadsEntity leads = leadsService.getOne(1);
        System.out.println(leads);
    }

    @Test
    public void findOne() {
        Page<LeadsEntity> leadsEntity = leadsRepository.getLeadsEntitiesByLevel(1, PageRequest.of(1, 10));
        System.out.println(leadsEntity.getTotalElements());
    }

    @Test
    public void delete() {
        leadsRepository.delete(createInitEntity(1));
    }

    @Test
    public void update() {
        LeadsEntity leadsEntity = leadsService.getOne(2);
        leadsEntity.setLevel(1);
        //save:存在则更新，不存在则添加
        leadsService.save(leadsEntity);
    }

    @Test
    public void pageable() {
        //org.springframework.data.domain.sort
        Pageable pageable = PageRequest.of(5, 5, Sort.by(Sort.Order.asc("status_a")));
        Page page = leadsRepository.findAll(pageable);
        for (Object o : page) {
            LeadsEntity leadsEntity = (LeadsEntity) o;
            System.out.println(leadsEntity);
        }
    }

    @Test
    public void multiConditionQuery() {
        //查询status_a=1的数据
        QueryBuilder matchQueryBuilderA = QueryBuilders.matchQuery("status_a", 1);
        List<LeadsEntity> lista = Lists.newArrayList(leadsRepository.search(matchQueryBuilderA));
        System.out.println(lista.size());

        //查询status_b=1的数据
        QueryBuilder matchQueryBuilderB = QueryBuilders.matchQuery("status_b", 1);
        List<LeadsEntity> listb = Lists.newArrayList(leadsRepository.search(matchQueryBuilderB));
        System.out.println(listb.size());

        //查询status_a=1 & status_b=1的数据
        QueryBuilder boolQueryBuilder = QueryBuilders
                .boolQuery()
                .must(matchQueryBuilderA)
                .must(matchQueryBuilderB);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();
        List<LeadsEntity> result = Lists.newArrayList(leadsRepository.search(searchQuery));
        System.out.println(result.size());
        System.out.println(lista.stream().filter(x -> x.getStatus_b().equals(1)).count());
    }


    @Test
    public void countByOne() {
        TermsAggregationBuilder tb = AggregationBuilders.terms("level_count").field("level");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(tb)
                .build();

        Aggregations aggregations =
                esRestTemplate.query(searchQuery, SearchResponse::getAggregations);

        Terms terms = aggregations.get("level_count");
        for (Terms.Bucket item : terms.getBuckets()) {
            System.out.println("key:" + item.getKeyAsString() + ",count:" + item.getDocCount());
        }
    }

    @Test
    public void countByMore() {
        TermsAggregationBuilder tb = AggregationBuilders.terms("level_count").field("level");
        TermsAggregationBuilder tb2 = AggregationBuilders.terms("status_a_count").field("status_a");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(tb)
                .addAggregation(tb2)
                .build();

        Aggregations aggregations =
                esRestTemplate.query(searchQuery, SearchResponse::getAggregations);

        Terms terms = aggregations.get("level_count");
        for (Terms.Bucket item : terms.getBuckets()) {
            System.out.println("key:" + item.getKeyAsString() + ",count:" + item.getDocCount());
        }
        System.out.println("=========");
        Terms terms2 = aggregations.get("status_a_count");
        for (Terms.Bucket item : terms2.getBuckets()) {
            System.out.println("key:" + item.getKeyAsString() + ",count:" + item.getDocCount());
        }
    }

    // term:作为分组条件
    // sum,avg统计条件
    @Test()
    public void countAndSumByOneTerm() {
        // 构建查询体
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.terms("level_count").field("level")
                        .subAggregation(AggregationBuilders.sum("sum_scurce").field("source"))
                        .subAggregation(AggregationBuilders.avg("avg_month").field("biz_month"))
                )
                .build();
        // 发起查询
        Aggregations aggregations = esRestTemplate.query(searchQuery, SearchResponse::getAggregations);
        // 获取聚合结果
        Terms terms = aggregations.get("level_count");
        for (Terms.Bucket item : terms.getBuckets()) {
            ParsedSum sumScurce = item.getAggregations().get("sum_scurce");
            ParsedAvg avgMonth = item.getAggregations().get("avg_month");

            System.out.println("key:" + item.getKeyAsString()
                    + ",count:" + item.getDocCount()
                    + ", sum: " + sumScurce.getValue()
                    + ",avg_month:" + avgMonth.getValue());
        }
    }

    @Test()
    public void distinctSearch() {
        // 构建查询体
        CardinalityAggregationBuilder cardinalityAggregationBuilder = AggregationBuilders
                .cardinality("level_count")
                .field("level");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(cardinalityAggregationBuilder)
                .build();
        // 发起查询
        Aggregations aggregations = esRestTemplate.query(searchQuery, SearchResponse::getAggregations);
        // 获取聚合结果
        ParsedCardinality terms = aggregations.get("level_count");
        System.out.println(terms.getValue());
    }
}

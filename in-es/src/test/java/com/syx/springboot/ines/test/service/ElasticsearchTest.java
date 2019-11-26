package com.syx.springboot.ines.test.service;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Preconditions;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.composite.ParsedComposite;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author shaoyx
 * @date 15:32  2019/11/22
 */
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchRestTemplate esRestTemplate;


    /**
     * group by opportunityStatus,employeeId
     * @param dealerId
     * @param employeeIds
     * @return
     * @author shaoyx
     */
    public List<Integer> getOpptyCountOfLevel4Employee(Integer dealerId, List<Integer> employeeIds) {
        BoolQueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .filter(QueryBuilders.termQuery("dealerId", dealerId))
                .filter(QueryBuilders.termQuery("employeeId", employeeIds))
                .filter(QueryBuilders.termQuery("aliveStatus", 1))
                .filter(QueryBuilders.termQuery("opportunityStatus", Lists.newArrayList(1, 2, 3, 4)))
                .filter(QueryBuilders.rangeQuery("employeeId").gt(0));

        TermsValuesSourceBuilder tvsEmployeeId = new TermsValuesSourceBuilder("employeeId")
                .field("employeeId");

        TermsValuesSourceBuilder tvsOpportunityStatus = new TermsValuesSourceBuilder("opportunityStatus")
                .field("opportunityStatus");

        String subAggName = "filterby";
        FilterAggregationBuilder fabEnterShopStatus = AggregationBuilders.filter(subAggName,
                QueryBuilders.termQuery("enterShopStatus", 1));

        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders
                .composite("groupby", Lists.newArrayList(tvsEmployeeId, tvsOpportunityStatus))
                .subAggregation(fabEnterShopStatus)
                .size(1000);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("opportunity")
                .withQuery(queryBuilder)
                .addAggregation(aggregationBuilder)
                .build();

        Aggregations aggregations = esRestTemplate.query(searchQuery, SearchResponse::getAggregations);
        ParsedComposite parsedComposite = aggregations.get("groupby");
        List<ParsedComposite.ParsedBucket> bucketList = parsedComposite.getBuckets();

        return Lists.newArrayList();
    }
}

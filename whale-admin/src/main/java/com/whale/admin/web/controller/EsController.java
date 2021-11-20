package com.whale.admin.web.controller;

import com.whale.admin.web.repository.model.User;
import com.whale.admin.web.repository.model.es.ProductConditionVo;
import com.whale.admin.web.repository.model.mongo.FinancingSeasonActivityOrganization2021Vo;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.IndexBoost;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Es测试Rest
 *
 * @author trendong
 */
@Api(tags = "Es测试")
@RestController
@RequestMapping(value = "/es")
public class EsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    @GetMapping("/test")
    public void test() {
        logger.debug("debug");
        // <1> 创建 ES 搜索条件
        List<IndexBoost> indicesBoost = new ArrayList<>();
        indicesBoost.add(new IndexBoost("product", 1.0f));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withIndicesBoost(indicesBoost);
        // <2> 筛选
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("芋道",
                "name", "sellPoint", "categoryName"));
        // <3> 聚合
        // 商品分类
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid"));
        // <4> 执行查询
        Flux<SearchHit<ProductConditionVo>> search = reactiveElasticsearchTemplate.search(nativeSearchQueryBuilder.build(), ProductConditionVo.class);
        // <5> 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
        System.out.println(search);
    }

}

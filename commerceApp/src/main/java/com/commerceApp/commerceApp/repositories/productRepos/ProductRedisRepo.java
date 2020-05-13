package com.commerceApp.commerceApp.repositories.productRepos;

import com.commerceApp.commerceApp.models.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class ProductRedisRepo {
    public static final String KEY = "id";
    private RedisTemplate<String, Product> redisTemplate;
    private HashOperations hashOperations;
/*
    public ProductRedisRepo(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

 */

    /*Getting all Items from tSable*/
    public Map<Integer,Product> getAllItems(Pageable pageable){
        return hashOperations.entries(KEY);
    }

}

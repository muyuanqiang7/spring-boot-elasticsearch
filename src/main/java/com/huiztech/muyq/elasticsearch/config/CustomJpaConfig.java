package com.huiztech.muyq.elasticsearch.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/2/23 22:05]
 * @description []
 */
@Configuration
public class CustomJpaConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}

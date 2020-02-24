package com.huiztech.muyq.elasticsearch.repository;

import com.huiztech.muyq.elasticsearch.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/2/23 22:12]
 * @description []
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {
}

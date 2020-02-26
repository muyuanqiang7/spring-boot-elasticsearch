package com.huiztech.muyq.elasticsearch.service;

import com.huiztech.muyq.elasticsearch.domain.QUser;
import com.huiztech.muyq.elasticsearch.domain.User;
import com.huiztech.muyq.elasticsearch.repository.UserRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/2/23 22:19]
 * @description []
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private JPAQueryFactory jpaQueryFactory;

    /**
     * 根据用户名和密码查找（假定只能找出一条）
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    public User findByUsernameAndPassword(String username, String password) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        user.username.eq(username),
                        user.password.eq(password)
                )
                .fetchOne();
    }

    /**
     * 查询所有的实体,根据uIndex字段排序
     *
     * @return List<User>
     */
    public List<User> findAll() {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .orderBy(
                        user.uIndex.asc()
                )
                .fetch();
    }

    /**
     * 分页查询所有的实体,根据uIndex字段排序
     *
     * @return QueryResults<User>
     */
    public QueryResults<User> findAllPage(Pageable pageable) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .orderBy(
                        user.uIndex.asc()
                )
                //起始页
                .offset(pageable.getOffset())
                //每页大小
                .limit(pageable.getPageSize())
                //获取结果，该结果封装了实体集合、分页的信息，需要这些信息直接从该对象里面拿取即可
                .fetchResults();
    }

    public List<User> findByBirthdayBetween(Date start, Date end) {
        QUser user = QUser.user;
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        user.birthday.between(start, end)
                )
                .fetch();
    }

    public Page<User> findByPage() {
        QUser user = QUser.user;
        Predicate predicate = user.userId.intValue().gt(0)
                .and(user.username.like("%b%"));
        //分页排序
        Sort sort = Sort.by(Sort.Direction.ASC, "userId");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        return userRepository.findAll(predicate, pageRequest);
    }
}

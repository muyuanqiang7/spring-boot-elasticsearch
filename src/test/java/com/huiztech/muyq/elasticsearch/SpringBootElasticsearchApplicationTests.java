package com.huiztech.muyq.elasticsearch;

import com.huiztech.muyq.elasticsearch.domain.User;
import com.huiztech.muyq.elasticsearch.repository.UserRepository;
import com.huiztech.muyq.elasticsearch.service.UserService;
import com.querydsl.core.QueryResults;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootElasticsearchApplicationTests {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
//        User user = new User();
//        user.setUserId(1);
//        user.setBirthday(new Date());
//        user.setUIndex(BigDecimal.ZERO);
//        user.setUsername("Bob");
//        user.setPassword("12345678");
//        user.setNickName("Bob");
//        userRepository.save(user);
        userService.findAll().forEach(System.out::println);
        User user = userService.findByUsernameAndPassword("Bob", "12345678");
        System.out.println(user);
    }

    @Test
    void findPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        QueryResults<User> results = userService.findAllPage(pageable);
        results.getResults().forEach(System.out::println);
    }

}

package com.huiztech.muyq.elasticsearch;

import com.huiztech.muyq.elasticsearch.domain.User;
import com.huiztech.muyq.elasticsearch.repository.UserRepository;
import com.huiztech.muyq.elasticsearch.service.UserService;
import com.querydsl.core.QueryResults;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootElasticsearchApplicationTests {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userService;
    private static final String NAMES = "abcdefghijklmnopqrstvuwxyz";
    private static final Random random = new Random();

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
    void saveUsers() {
        List<User> users = Lists.newArrayList();
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (int i = 2; i < 1000000; i++) {
            User user = new User();
            user.setUserId(i);
            user.setBirthday(new Date());
            bigDecimal = bigDecimal.add(BigDecimal.ONE);
            String name = createRandomName();
            user.setUIndex(bigDecimal);
            user.setUsername(name);
            user.setPassword("12345678");
            user.setNickName(name);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Test
    void findPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        QueryResults<User> results = userService.findAllPage(pageable);
        results.getResults().forEach(System.out::println);
    }

    @Test
    void findUserBirthdayBetween() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date start = calendar.getTime();
        Date end = new Date();
        userService.findByBirthdayBetween(start, end).forEach(System.out::println);
    }

    @Test
    void findPage() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            userService.findByPage().get().forEach(System.out::println);
        }
        long end = System.currentTimeMillis();
        System.out.println(convert(end - start));
    }

    public String convert(long milliSeconds) {
        int hrs = (int) TimeUnit.MILLISECONDS.toHours(milliSeconds) % 24;
        int min = (int) TimeUnit.MILLISECONDS.toMinutes(milliSeconds) % 60;
        int sec = (int) TimeUnit.MILLISECONDS.toSeconds(milliSeconds) % 60;
        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }

    public String createRandomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int a = random.nextInt(NAMES.length());
            name.append(NAMES.charAt(a));
        }
        String result = name.toString();
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }

}

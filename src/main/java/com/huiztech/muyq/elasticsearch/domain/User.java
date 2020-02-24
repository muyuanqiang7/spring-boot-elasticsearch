package com.huiztech.muyq.elasticsearch.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/2/23 22:06]
 * @description []
 */

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String nickName;
    private Date birthday;
    /**
     * 排序号
     */
    private BigDecimal uIndex;
}

package com.huiztech.muyq.elasticsearch.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/2/23 22:18]
 * @description []
 */
@Data
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String nickname;
    private String birthday;
}

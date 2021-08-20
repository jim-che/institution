package com.institution.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chenguo
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String name;

    private String avatar;

    private String email;

    private LocalDateTime gmtCreate;
}

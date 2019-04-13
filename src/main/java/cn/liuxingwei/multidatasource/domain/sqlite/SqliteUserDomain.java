package cn.liuxingwei.multidatasource.domain.sqlite;

import lombok.Data;

@Data
public class SqliteUserDomain {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userCreateTime;
    private String userUpdateTime;
}

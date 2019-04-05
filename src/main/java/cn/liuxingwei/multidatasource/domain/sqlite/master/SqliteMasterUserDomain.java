package cn.liuxingwei.multidatasource.domain.sqlite.master;

import lombok.Data;

@Data
public class SqliteMasterUserDomain {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userCreateTime;
    private String userUpdateTime;
}

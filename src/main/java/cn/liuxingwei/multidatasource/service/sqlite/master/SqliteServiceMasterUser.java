package cn.liuxingwei.multidatasource.service.sqlite.master;

import cn.liuxingwei.multidatasource.domain.sqlite.master.SqliteMasterUserDomain;
import cn.liuxingwei.multidatasource.mapper.sqlite.master.SqliteMasterUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqliteServiceMasterUser {
    @Autowired
    private SqliteMasterUserMapper sqliteMasterUserMapper;
    public SqliteMasterUserDomain getUserByUserId(Integer userId) {
        SqliteMasterUserDomain user = sqliteMasterUserMapper.getUserByUserId(userId);
        return user;
    }
}

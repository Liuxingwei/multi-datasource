package cn.liuxingwei.multidatasource.service.sqlite.master;

import cn.liuxingwei.multidatasource.domain.sqlite.SqliteUserDomain;
import cn.liuxingwei.multidatasource.mapper.sqlite.master.SqliteMasterUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqliteMasterUserService {
    @Autowired
    private SqliteMasterUserMapper sqliteMasterUserMapper;
    public SqliteUserDomain getUserByUserId(Integer userId) {
        SqliteUserDomain user = sqliteMasterUserMapper.getUserByUserId(userId);
        return user;
    }
}

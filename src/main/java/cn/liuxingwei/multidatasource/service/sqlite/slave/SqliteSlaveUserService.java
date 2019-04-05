package cn.liuxingwei.multidatasource.service.sqlite.slave;

import cn.liuxingwei.multidatasource.domain.sqlite.master.SqliteMasterUserDomain;
import cn.liuxingwei.multidatasource.mapper.sqlite.slave.SqliteSlaveUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqliteSlaveUserService {
    @Autowired
    private SqliteSlaveUserMapper sqliteSlaveUserMapper;
    public SqliteMasterUserDomain getUserByUserId(Integer userId) {
        SqliteMasterUserDomain user = sqliteSlaveUserMapper.getUserByUserId(userId);
        return user;
    }
}

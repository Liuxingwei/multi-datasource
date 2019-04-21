package cn.liuxingwei.multidatasource.service.sqlite.slave;

import cn.liuxingwei.multidatasource.domain.sqlite.SqliteUserDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SqliteSlaveUserServiceTest {

    @Resource
    private SqliteSlaveUserService sqliteSlaveUserService;

    @Test
    public void getUserByUserId() {
        SqliteUserDomain user = sqliteSlaveUserService.getUserByUserId(1);
        assertEquals("李四光", user.getUserName());
    }
}
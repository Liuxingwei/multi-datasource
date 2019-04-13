package cn.liuxingwei.multidatasource.service.sqlite.master;

import cn.liuxingwei.multidatasource.domain.sqlite.SqliteUserDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class SqliteMasterUserServiceTest {

    @Autowired
    private SqliteMasterUserService sqliteMasterUserService;

    @Test
    public void getUserByUserId() {
        SqliteUserDomain user = sqliteMasterUserService.getUserByUserId(1);
        assertEquals("张三丰", user.getUserName());
    }
}
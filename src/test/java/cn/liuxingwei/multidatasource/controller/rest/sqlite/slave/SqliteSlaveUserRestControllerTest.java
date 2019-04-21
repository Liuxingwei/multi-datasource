package cn.liuxingwei.multidatasource.controller.rest.sqlite.slave;

import cn.liuxingwei.multidatasource.domain.sqlite.SqliteUserDomain;
import cn.liuxingwei.multidatasource.service.sqlite.slave.SqliteSlaveUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(SqliteSlaveUserRestController.class)
public class SqliteSlaveUserRestControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private SqliteSlaveUserService sqliteSlaveUserService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSlaveUserById() throws Exception {
        Integer id = 1;
        String userName = "李四海";
        String userPassword = "aaabbbccc";
        String userCreateTime = "2019080808";
        String userUpdateTime = "2019090909";

        SqliteUserDomain user = new SqliteUserDomain();
        user.setUserId(id);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserCreateTime(userCreateTime);
        user.setUserUpdateTime(userUpdateTime);

        Mockito.when(sqliteSlaveUserService.getUserByUserId(id)).thenReturn(user);

        ResultActions resultAction = mockMvc.perform(get("/user/slave/getUserByUserId").param("user_id", "1"));
        resultAction.andExpect(jsonPath("userId").value(id))
                .andExpect(jsonPath("userName").value(userName))
                .andExpect(jsonPath("userPassword").value(userPassword))
                .andExpect(jsonPath("userCreateTime").value(userCreateTime))
                .andExpect(jsonPath("userUpdateTime").value(userUpdateTime));
    }
}
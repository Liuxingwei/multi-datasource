package cn.liuxingwei.multidatasource.controller.rest.sqlite.master;
import cn.liuxingwei.multidatasource.domain.sqlite.SqliteUserDomain;
import cn.liuxingwei.multidatasource.service.sqlite.master.SqliteMasterUserService;
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
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private SqliteMasterUserService sqliteMasterUserService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMasterUserById() throws Exception {
        Integer id = 1;
        String userName = "张三疯";
        String userPassword = "aaabbbccc";
        String userCreateTime = "2019080808";
        String userUpdateTime = "2019090909";

        SqliteUserDomain user = new SqliteUserDomain();
        user.setUserId(id);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserCreateTime(userCreateTime);
        user.setUserUpdateTime(userUpdateTime);

        Mockito.when(sqliteMasterUserService.getUserByUserId(id)).thenReturn(user);

        ResultActions resultAction = mockMvc.perform(get("/user/getUserByUserId").param("user_id","1"));
        resultAction.andExpect(jsonPath("userId").value(id))
                .andExpect(jsonPath("userName").value(userName))
                .andExpect(jsonPath("userPassword").value(userPassword))
                .andExpect(jsonPath("userCreateTime").value(userCreateTime))
                .andExpect(jsonPath("userUpdateTime").value(userUpdateTime));
        ;
    }
}
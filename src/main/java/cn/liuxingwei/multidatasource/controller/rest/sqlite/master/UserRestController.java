package cn.liuxingwei.multidatasource.controller.rest.sqlite.master;

import cn.liuxingwei.multidatasource.domain.sqlite.master.SqliteMasterUserDomain;
import cn.liuxingwei.multidatasource.service.sqlite.master.SqliteMasterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserRestController {

    @Autowired
    private SqliteMasterUserService sqliteServiceMasterUser;

    @RequestMapping("/user/getUserByUserId")
    public SqliteMasterUserDomain getUserByUserId(@RequestParam(value = "user_id") Integer userId) {
        SqliteMasterUserDomain user = sqliteServiceMasterUser.getUserByUserId(userId);
        log.info(user.getUserName());
        return user;
    }
}

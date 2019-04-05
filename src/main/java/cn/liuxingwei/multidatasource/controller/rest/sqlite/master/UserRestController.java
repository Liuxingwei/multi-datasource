package cn.liuxingwei.multidatasource.controller.rest.sqlite.master;

import cn.liuxingwei.multidatasource.domain.sqlite.master.SqliteMasterUserDomain;
import cn.liuxingwei.multidatasource.service.sqlite.master.SqliteServiceMasterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private SqliteServiceMasterUser sqliteServiceMasterUser;

    @RequestMapping("/user/getUserByUserId")
    public SqliteMasterUserDomain getUserByUserId(@RequestParam(value = "user_id") Integer userId) {
        return sqliteServiceMasterUser.getUserByUserId(userId);
    }
}

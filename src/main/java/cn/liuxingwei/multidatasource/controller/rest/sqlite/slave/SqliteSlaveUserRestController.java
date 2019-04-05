package cn.liuxingwei.multidatasource.controller.rest.sqlite.slave;

import cn.liuxingwei.multidatasource.domain.sqlite.master.SqliteMasterUserDomain;
import cn.liuxingwei.multidatasource.service.sqlite.slave.SqliteSlaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqliteSlaveUserRestController {
    @Autowired
    private SqliteSlaveUserService sqliteSlaveUserService;

    @RequestMapping("/user/slave/getUserByUserId")
    public SqliteMasterUserDomain getUserByUserId(@RequestParam("user_id") Integer userId) {
        return sqliteSlaveUserService.getUserByUserId(userId);
    }
}

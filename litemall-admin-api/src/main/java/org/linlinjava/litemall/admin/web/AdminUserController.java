package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAdmin;
import org.linlinjava.litemall.db.domain.LitemallCredit;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.service.LitemallCreditService;
import org.linlinjava.litemall.db.service.LitemallUserService;
import org.linlinjava.litemall.db.vo.LitemallUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    private final Log logger = LogFactory.getLog(AdminUserController.class);

    @Autowired
    private LitemallUserService userService;

    @Autowired
    private LitemallCreditService creditService;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username, String mobile, String promoter,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        LitemallAdmin currentAdmin = (LitemallAdmin) SecurityUtils.getSubject().getPrincipal();
        Integer cid = 0;
        if (currentAdmin != null) {
            cid = currentAdmin.getCid();
        }

        List<LitemallUserVO> userList = userService.querySelective(cid, username, mobile, promoter, page, limit, sort, order);
        return ResponseUtil.okList(userList);
    }

    @RequiresPermissions("admin:user:credit")
    @RequiresPermissionsDesc(menu = {"用户管理", "信用管理"}, button = "查询")
    @GetMapping("/credit")
    public Object list(Integer id) {
        Long balance = creditService.queryBalance(id);

        return ResponseUtil.ok(BigDecimal.valueOf(balance).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP));
    }

    @RequiresPermissions("admin:user:addCredit")
    @RequiresPermissionsDesc(menu = {"用户管理", "信用管理"}, button = "添加")
    @PostMapping("/addCredit")
    public Object addCredit(@RequestBody String body) {
        Integer userId = JacksonUtil.parseInteger(body, "id");
        String money = JacksonUtil.parseString(body, "money");

        LitemallUser userList = userService.findById(userId);
        if (creditService.add(userId, money, "管理员增加余额", 1) == -1) {
            return ResponseUtil.badArgument();
        } else {
            return ResponseUtil.ok(money);
        }
    }

    @RequiresPermissions("admin:user:userType")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户类型"}, button = "添加")
    @PostMapping("/userType")
    public Object userType(@RequestBody String body) {
        Integer userId = JacksonUtil.parseInteger(body, "id");
        String status = JacksonUtil.parseString(body, "status");

        LitemallUser userList = userService.findById(userId);
        if (userList != null && !StringUtils.isEmpty(status)) {
            userList.setStatus(Byte.parseByte(status));
            if (userService.updateById(userList) == 0) {
                return ResponseUtil.updatedDataFailed();
            }else {
                return ResponseUtil.ok(userId);
            }
        }else {
            return ResponseUtil.badArgument();
        }
    }
}

package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.LogHelper;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAdmin;
import org.linlinjava.litemall.db.service.LitemallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_DELETE_NOT_ALLOWED;
import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_NAME_EXIST;

@RestController
@RequestMapping("/admin/courier")
@Validated
public class AdminCourierController {
    private final Log logger = LogFactory.getLog(AdminCourierController.class);
    @Autowired
    private LogHelper logHelper;
    @Autowired
    private LitemallAdminService adminService;


    @RequiresPermissions("admin:admin:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(name = "cid", required = false)Integer cid,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
//        List<LitemallCourier> courierList = courierService.querySelective(username, page, limit, sort, order);
        LitemallAdmin operator = (LitemallAdmin) SecurityUtils.getSubject().getPrincipal();
        if (null == cid) {
            cid = operator.getCid();
        }
        List<LitemallAdmin> courierList = adminService.queryCourierSelective(username, page, limit,sort, order, cid);
        return ResponseUtil.okList(courierList);
    }


    @RequiresPermissions("admin:admin:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallAdmin admin) {


        String username = admin.getUsername();
        List<LitemallAdmin> courierList = adminService.findAdmin(admin.getUsername());
        if (courierList.size() > 0) {
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "配送员已经存在");
        }
        Integer[] roleId = new Integer[]{4};
        admin.setRoleIds(roleId);

        adminService.add(admin);
        logHelper.logAuthSucceed("添加配送员", username);
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("admin:admin:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallAdmin courier = adminService.findById(id);
        return ResponseUtil.ok(courier);
    }

    @RequiresPermissions("admin:admin:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallAdmin courier) {
       

        Integer anothercourierId = courier.getId();
        if (anothercourierId == null) {
            return ResponseUtil.badArgument();
        }

        // 不允许管理员通过编辑接口修改密码
        courier.setPassword(null);

        if (adminService.updateById(courier) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑管理员", courier.getUsername());
        return ResponseUtil.ok(courier);
    }

    @RequiresPermissions("admin:admin:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallAdmin courier) {
        Integer anothercourierId = courier.getId();
        if (anothercourierId == null) {
            return ResponseUtil.badArgument();
        }

        // 管理员不能删除自身账号
        Subject currentUser = SecurityUtils.getSubject();
        LitemallAdmin currentcourier = (LitemallAdmin) currentUser.getPrincipal();
        if (currentcourier.getId().equals(anothercourierId)) {
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "管理员不能删除自己账号");
        }

        adminService.deleteById(anothercourierId);
        logHelper.logAuthSucceed("删除管理员", courier.getUsername());
        return ResponseUtil.ok();
    }
}

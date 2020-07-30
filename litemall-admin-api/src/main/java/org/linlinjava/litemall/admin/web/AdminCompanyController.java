package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallComment;
import org.linlinjava.litemall.db.domain.TbCompany;
import org.linlinjava.litemall.db.service.LitemallCommentService;
import org.linlinjava.litemall.db.service.LitemallCompanySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/company")
@Validated
public class AdminCompanyController {

    private final Log logger = LogFactory.getLog(AdminCompanyController.class);

    @Autowired
    private LitemallCompanySerivce companySerivce;

//    @RequiresPermissions("admin:comment:list")
//    @RequiresPermissionsDesc(menu = {"商品管理", "评论管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Integer cid,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<TbCompany> commentList = companySerivce.querySelective(cid, page, limit, sort);
        return ResponseUtil.okList(commentList);
    }


}

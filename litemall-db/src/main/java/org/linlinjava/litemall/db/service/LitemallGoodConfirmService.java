package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallGoodConfirmMapper;
import org.linlinjava.litemall.db.dao.LitemallOrderGoodsMapper;
import org.linlinjava.litemall.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGoodConfirmService {
    @Resource
    private LitemallGoodConfirmMapper goodConfirmMapper;

    public List<LitemallGoodConfirm> querySelective(Integer userId, Integer page, Integer limit, String sort, String order) {
        LitemallGoodConfirmExample example = new LitemallGoodConfirmExample();
        LitemallGoodConfirmExample.Criteria criteria = example.createCriteria();


            criteria.andUserIdEqualTo(userId);


        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return goodConfirmMapper.selectByExample(example);
    }
}

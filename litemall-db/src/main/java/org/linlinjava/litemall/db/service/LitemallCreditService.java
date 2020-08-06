package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCreditMapper;
import org.linlinjava.litemall.db.dao.LitemallGoodConfirmMapper;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCreditService {
    @Resource
    private LitemallCreditMapper creditMapper;

    public List<LitemallCredit> querySelective(Integer userId, Integer page, Integer limit, String sort, String order) {
        LitemallCreditExample example = new LitemallCreditExample();
        LitemallCreditExample.Criteria criteria = example.createCriteria();


            criteria.andUserIdEqualTo(userId);


        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return creditMapper.selectByExample(example);
    }

    public Long queryBalance(Integer userId) {
        LitemallCreditExample example = new LitemallCreditExample();
        LitemallCreditExample.Criteria criteria = example.createCriteria();


        criteria.andUserIdEqualTo(userId);


            example.setOrderByClause( "id DESC");


        PageHelper.startPage(1, 1);
         List<LitemallCredit> credits = creditMapper.selectByExample(example);
        if (credits == null || credits.size() == 0) {
            return 0L;
        }else {
            return credits.get(0).getBalance();
        }
    }

    public int add(LitemallCredit credit) {
        credit.setCreateTime(LocalDateTime.now());
        credit.setUpdateTime(LocalDateTime.now());

        Long balance = queryBalance(credit.getUserId());



        credit.setBalance( balance + (credit.getDc() * credit.getAmount()));

         return  creditMapper.insertSelective(credit);

    }

    public int add(Integer userId, String amount, String content, Integer dc) {
        try {
            BigDecimal bigDecimal = new BigDecimal(amount);
            Long balance = queryBalance(userId);


            LitemallCredit credit = new LitemallCredit();
            credit.setUserId(userId);
            credit.setContent(content);
            credit.setDc(dc);
            credit.setAmount((bigDecimal.multiply(new BigDecimal("100"))).longValue());

            credit.setCreateTime(LocalDateTime.now());
            credit.setUpdateTime(LocalDateTime.now());



            credit.setBalance( balance + (credit.getDc() * credit.getAmount()));

            return  creditMapper.insertSelective(credit);
        }catch (NumberFormatException e){
            return -1;
        }



    }

}

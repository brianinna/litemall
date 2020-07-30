package org.linlinjava.litemall.db.service;


import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCommentMapper;
import org.linlinjava.litemall.db.dao.TbCompanyMapper;
import org.linlinjava.litemall.db.domain.LitemallComment;
import org.linlinjava.litemall.db.domain.LitemallCommentExample;
import org.linlinjava.litemall.db.domain.TbCompany;
import org.linlinjava.litemall.db.domain.TbCompanyExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallCompanySerivce {

    @Resource
    private TbCompanyMapper tbCompanyMapper ;



    public List<TbCompany> querySelective(Integer cid, Integer page, Integer size, String sort) {
        TbCompanyExample example = new TbCompanyExample();
        TbCompanyExample.Criteria criteria = example.createCriteria();


        if (null != cid && cid > 0) {
            criteria.andIdEqualTo(cid);
        }
        PageHelper.startPage(page, size);
        return tbCompanyMapper.selectByExample(example);
    }

}

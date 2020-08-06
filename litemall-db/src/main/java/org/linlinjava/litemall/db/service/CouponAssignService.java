package org.linlinjava.litemall.db.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.db.domain.LitemallCoupon;
import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignService {
    private final Log logger = LogFactory.getLog(CouponAssignService.class);


    @Autowired
    private LitemallCouponUserService couponUserService;
    @Autowired
    private LitemallCouponService couponService;

    /**
     * 分发注册优惠券
     *
     * @param userId
     * @return
     */
    public void assignForRegister(Integer userId, String userType,Integer cid) {
        logger.info("开始优惠卷");
        System.out.println("开始优惠卷"+" 1" + userType);

        logger.info("用户type is");
        logger.info(userType);
        logger.info("1" + userType);

        List<LitemallCoupon> couponList = couponService.queryRegister("1" + userType,cid);
        System.out.println("copiuns list size "+ couponList.size());
        for(LitemallCoupon coupon : couponList){
            Integer couponId = coupon.getId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getLimit();
            while(limit > 0){
                LitemallCouponUser couponUser = new LitemallCouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                couponUser.setCid(1001);
                Short timeType = coupon.getTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                }
                else{
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                couponUserService.add(couponUser);

                limit--;
            }
        }

    }

}
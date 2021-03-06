package org.linlinjava.litemall.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallGoodConfirm {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.order_id
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.original_num
     *
     * @mbg.generated
     */
    private Integer originalNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.other_num
     *
     * @mbg.generated
     */
    private Integer otherNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.fund_pay
     *
     * @mbg.generated
     */
    private String fundPay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.fund_price
     *
     * @mbg.generated
     */
    private Integer fundPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.order_time
     *
     * @mbg.generated
     */
    private Integer orderTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.courier_id
     *
     * @mbg.generated
     */
    private Integer courierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.courier_name
     *
     * @mbg.generated
     */
    private String courierName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.order_date
     *
     * @mbg.generated
     */
    private LocalDateTime orderDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.fund_pay_num
     *
     * @mbg.generated
     */
    private Integer fundPayNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.fund_card_num
     *
     * @mbg.generated
     */
    private Integer fundCardNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.total_owe
     *
     * @mbg.generated
     */
    private Integer totalOwe;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.order_good_num
     *
     * @mbg.generated
     */
    private Integer orderGoodNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_good_confirm.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.id
     *
     * @return the value of litemall_good_confirm.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.id
     *
     * @param id the value for litemall_good_confirm.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.order_id
     *
     * @return the value of litemall_good_confirm.order_id
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.order_id
     *
     * @param orderId the value for litemall_good_confirm.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.original_num
     *
     * @return the value of litemall_good_confirm.original_num
     *
     * @mbg.generated
     */
    public Integer getOriginalNum() {
        return originalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.original_num
     *
     * @param originalNum the value for litemall_good_confirm.original_num
     *
     * @mbg.generated
     */
    public void setOriginalNum(Integer originalNum) {
        this.originalNum = originalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.other_num
     *
     * @return the value of litemall_good_confirm.other_num
     *
     * @mbg.generated
     */
    public Integer getOtherNum() {
        return otherNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.other_num
     *
     * @param otherNum the value for litemall_good_confirm.other_num
     *
     * @mbg.generated
     */
    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.fund_pay
     *
     * @return the value of litemall_good_confirm.fund_pay
     *
     * @mbg.generated
     */
    public String getFundPay() {
        return fundPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.fund_pay
     *
     * @param fundPay the value for litemall_good_confirm.fund_pay
     *
     * @mbg.generated
     */
    public void setFundPay(String fundPay) {
        this.fundPay = fundPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.fund_price
     *
     * @return the value of litemall_good_confirm.fund_price
     *
     * @mbg.generated
     */
    public Integer getFundPrice() {
        return fundPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.fund_price
     *
     * @param fundPrice the value for litemall_good_confirm.fund_price
     *
     * @mbg.generated
     */
    public void setFundPrice(Integer fundPrice) {
        this.fundPrice = fundPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.order_time
     *
     * @return the value of litemall_good_confirm.order_time
     *
     * @mbg.generated
     */
    public Integer getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.order_time
     *
     * @param orderTime the value for litemall_good_confirm.order_time
     *
     * @mbg.generated
     */
    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.courier_id
     *
     * @return the value of litemall_good_confirm.courier_id
     *
     * @mbg.generated
     */
    public Integer getCourierId() {
        return courierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.courier_id
     *
     * @param courierId the value for litemall_good_confirm.courier_id
     *
     * @mbg.generated
     */
    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.courier_name
     *
     * @return the value of litemall_good_confirm.courier_name
     *
     * @mbg.generated
     */
    public String getCourierName() {
        return courierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.courier_name
     *
     * @param courierName the value for litemall_good_confirm.courier_name
     *
     * @mbg.generated
     */
    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.order_date
     *
     * @return the value of litemall_good_confirm.order_date
     *
     * @mbg.generated
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.order_date
     *
     * @param orderDate the value for litemall_good_confirm.order_date
     *
     * @mbg.generated
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.fund_pay_num
     *
     * @return the value of litemall_good_confirm.fund_pay_num
     *
     * @mbg.generated
     */
    public Integer getFundPayNum() {
        return fundPayNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.fund_pay_num
     *
     * @param fundPayNum the value for litemall_good_confirm.fund_pay_num
     *
     * @mbg.generated
     */
    public void setFundPayNum(Integer fundPayNum) {
        this.fundPayNum = fundPayNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.fund_card_num
     *
     * @return the value of litemall_good_confirm.fund_card_num
     *
     * @mbg.generated
     */
    public Integer getFundCardNum() {
        return fundCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.fund_card_num
     *
     * @param fundCardNum the value for litemall_good_confirm.fund_card_num
     *
     * @mbg.generated
     */
    public void setFundCardNum(Integer fundCardNum) {
        this.fundCardNum = fundCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.user_id
     *
     * @return the value of litemall_good_confirm.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.user_id
     *
     * @param userId the value for litemall_good_confirm.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.total_owe
     *
     * @return the value of litemall_good_confirm.total_owe
     *
     * @mbg.generated
     */
    public Integer getTotalOwe() {
        return totalOwe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.total_owe
     *
     * @param totalOwe the value for litemall_good_confirm.total_owe
     *
     * @mbg.generated
     */
    public void setTotalOwe(Integer totalOwe) {
        this.totalOwe = totalOwe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.order_good_num
     *
     * @return the value of litemall_good_confirm.order_good_num
     *
     * @mbg.generated
     */
    public Integer getOrderGoodNum() {
        return orderGoodNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.order_good_num
     *
     * @param orderGoodNum the value for litemall_good_confirm.order_good_num
     *
     * @mbg.generated
     */
    public void setOrderGoodNum(Integer orderGoodNum) {
        this.orderGoodNum = orderGoodNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_good_confirm.add_time
     *
     * @return the value of litemall_good_confirm.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_good_confirm.add_time
     *
     * @param addTime the value for litemall_good_confirm.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", originalNum=").append(originalNum);
        sb.append(", otherNum=").append(otherNum);
        sb.append(", fundPay=").append(fundPay);
        sb.append(", fundPrice=").append(fundPrice);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", courierId=").append(courierId);
        sb.append(", courierName=").append(courierName);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", fundPayNum=").append(fundPayNum);
        sb.append(", fundCardNum=").append(fundCardNum);
        sb.append(", userId=").append(userId);
        sb.append(", totalOwe=").append(totalOwe);
        sb.append(", orderGoodNum=").append(orderGoodNum);
        sb.append(", addTime=").append(addTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LitemallGoodConfirm other = (LitemallGoodConfirm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOriginalNum() == null ? other.getOriginalNum() == null : this.getOriginalNum().equals(other.getOriginalNum()))
            && (this.getOtherNum() == null ? other.getOtherNum() == null : this.getOtherNum().equals(other.getOtherNum()))
            && (this.getFundPay() == null ? other.getFundPay() == null : this.getFundPay().equals(other.getFundPay()))
            && (this.getFundPrice() == null ? other.getFundPrice() == null : this.getFundPrice().equals(other.getFundPrice()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getCourierId() == null ? other.getCourierId() == null : this.getCourierId().equals(other.getCourierId()))
            && (this.getCourierName() == null ? other.getCourierName() == null : this.getCourierName().equals(other.getCourierName()))
            && (this.getOrderDate() == null ? other.getOrderDate() == null : this.getOrderDate().equals(other.getOrderDate()))
            && (this.getFundPayNum() == null ? other.getFundPayNum() == null : this.getFundPayNum().equals(other.getFundPayNum()))
            && (this.getFundCardNum() == null ? other.getFundCardNum() == null : this.getFundCardNum().equals(other.getFundCardNum()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTotalOwe() == null ? other.getTotalOwe() == null : this.getTotalOwe().equals(other.getTotalOwe()))
            && (this.getOrderGoodNum() == null ? other.getOrderGoodNum() == null : this.getOrderGoodNum().equals(other.getOrderGoodNum()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOriginalNum() == null) ? 0 : getOriginalNum().hashCode());
        result = prime * result + ((getOtherNum() == null) ? 0 : getOtherNum().hashCode());
        result = prime * result + ((getFundPay() == null) ? 0 : getFundPay().hashCode());
        result = prime * result + ((getFundPrice() == null) ? 0 : getFundPrice().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getCourierId() == null) ? 0 : getCourierId().hashCode());
        result = prime * result + ((getCourierName() == null) ? 0 : getCourierName().hashCode());
        result = prime * result + ((getOrderDate() == null) ? 0 : getOrderDate().hashCode());
        result = prime * result + ((getFundPayNum() == null) ? 0 : getFundPayNum().hashCode());
        result = prime * result + ((getFundCardNum() == null) ? 0 : getFundCardNum().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTotalOwe() == null) ? 0 : getTotalOwe().hashCode());
        result = prime * result + ((getOrderGoodNum() == null) ? 0 : getOrderGoodNum().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        orderId("order_id", "orderId", "INTEGER", false),
        originalNum("original_num", "originalNum", "INTEGER", false),
        otherNum("other_num", "otherNum", "INTEGER", false),
        fundPay("fund_pay", "fundPay", "VARCHAR", false),
        fundPrice("fund_price", "fundPrice", "INTEGER", false),
        orderTime("order_time", "orderTime", "INTEGER", false),
        courierId("courier_id", "courierId", "INTEGER", false),
        courierName("courier_name", "courierName", "VARCHAR", false),
        orderDate("order_date", "orderDate", "TIMESTAMP", false),
        fundPayNum("fund_pay_num", "fundPayNum", "INTEGER", false),
        fundCardNum("fund_card_num", "fundCardNum", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        totalOwe("total_owe", "totalOwe", "INTEGER", false),
        orderGoodNum("order_good_num", "orderGoodNum", "INTEGER", false),
        addTime("add_time", "addTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_good_confirm
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}
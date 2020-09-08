package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallGoodConfirm;
import org.linlinjava.litemall.db.domain.LitemallGoodConfirmExample;

public interface LitemallGoodConfirmMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    long countByExample(LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int insert(LitemallGoodConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int insertSelective(LitemallGoodConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    LitemallGoodConfirm selectOneByExample(LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    LitemallGoodConfirm selectOneByExampleSelective(@Param("example") LitemallGoodConfirmExample example, @Param("selective") LitemallGoodConfirm.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    List<LitemallGoodConfirm> selectByExampleSelective(@Param("example") LitemallGoodConfirmExample example, @Param("selective") LitemallGoodConfirm.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    List<LitemallGoodConfirm> selectByExample(LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    LitemallGoodConfirm selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallGoodConfirm.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    LitemallGoodConfirm selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallGoodConfirm record, @Param("example") LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallGoodConfirm record, @Param("example") LitemallGoodConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallGoodConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_good_confirm
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallGoodConfirm record);

    int goodsTotal(LitemallGoodConfirmExample example);

}
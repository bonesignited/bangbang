package com.zlx.bangbang.service;

import com.zlx.bangbang.domain.Indent;
import com.zlx.bangbang.enums.GenderEnum;
import com.zlx.bangbang.vo.IndentListVO;
import com.zlx.bangbang.vo.IndentVO;

import java.math.BigDecimal;

public interface IndentService {
    /**
     * 创建新的订单
     * 若用户余额不足，则抛异常
     * @param indent
     * @return
     */
    Integer create(Indent indent);

    /**
     * 增加赏金，每次只增加一元
     * 若用户余额不足，则抛异常
     * @param indentId
     * @param userId
     */
    void addIndentPrice(Integer indentId, String userId);

    /**
     * 用户接单接口
     * @param indentId
     * @param userId
     */
    void takeIndent(Integer indentId, String userId);

    /**
     * 接单人送达接口
     * @param indentId
     * @param userId
     */
    void arrivedIndent(Integer indentId, String userId);

    /**
     * 完结订单
     * 以 CompanyIncome 局部变量为返回值
     * @param indentId
     * @param userId
     * @return
     */
    BigDecimal finishedIndent(Integer indentId, String userId);

    /**
     * 取消订单
     * 接单人取消订单，订单重回待接单状态
     * 下单人取消订单，订单取消并退钱
     * @param indentId
     * @param userId
     */
    void canceledIndent(Integer indentId, String userId);

    /**
     * 获取用户已发布的订单
     * @param userId
     * @return 该用户已发布的订单列表
     */
    IndentListVO getUserPublishedIndent(String userId, int pageNum, int pageSize);

    /**
     * 获取用户接的订单
     * @param userId
     * @return 该用户已接订单列表
     */
    IndentListVO getUserPerformedIndent(String userId, int pageNum, int pageSize);

    /**
     * 查询同性别的订单列表，排序列表为 sortType
     * 默认：0，时间：10，价格：20
     * @param sortType
     * @param sexType
     * @return 订单列表
     */
    IndentListVO getWait(Integer sortType, GenderEnum sexType, int pageNum, int pageSize);

    /**
     * 获取指定订单信息
     * @param indentId
     * @param userId
     * @return 订单信息
     */
    IndentVO getIndentDetail(Integer indentId, String userId);
}

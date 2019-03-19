package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.TradeInfo;

public interface TradeInfoMapper {
    int deleteByPrimaryKey(Integer tradeId);

    int insert(TradeInfo record);

    int insertSelective(TradeInfo record);

    TradeInfo selectByPrimaryKey(Integer tradeId);

    int updateByPrimaryKeySelective(TradeInfo record);

    int updateByPrimaryKey(TradeInfo record);
}
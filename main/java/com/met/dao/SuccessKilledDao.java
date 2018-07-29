package com.met.dao;

import com.met.entity.SuccessKilled;
import com.met.enums.SeckillStateEnum;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可以过滤重复
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone, @Param("state") int state);

    /**
     * 根据id查询successKilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @return 成功秒杀的记录
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);

}

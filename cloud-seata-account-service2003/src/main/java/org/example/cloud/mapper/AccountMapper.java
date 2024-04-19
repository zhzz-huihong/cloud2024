package org.example.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.cloud.entities.Account;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
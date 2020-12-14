package com.wuxinhua.mapper;

import com.wuxinhua.model.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Hr> getHrAll( @Param("keyWords") String keyWords , @Param("id") Integer id);

    Integer deleteHrById(@Param("id") Integer id);
}
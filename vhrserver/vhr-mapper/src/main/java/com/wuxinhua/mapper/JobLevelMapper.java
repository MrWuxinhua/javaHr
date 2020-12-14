package com.wuxinhua.mapper;

import com.wuxinhua.model.JobLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);


    List<JobLevel> getJobLevels();

    Integer deleteJobLeveByIds(@Param("ids") Integer[] ids);
}
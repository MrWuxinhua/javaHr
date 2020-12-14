package com.wuxinhua.service.system.basic;

import com.wuxinhua.mapper.JobLevelMapper;
import com.wuxinhua.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 职称级别
 */
@Service
public class JobLevelService {

    @Autowired
    private JobLevelMapper jobLevelMapper;


    /**
     * 获取所有职称级别
     * @return
     */
    public List<JobLevel> getJobLevels() {
        return jobLevelMapper.getJobLevels();
    }

    /**
     * 添加职称级别
     * @param jobLevel
     * @return
     */
    public Integer addjobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    /**
     * 更新职称级别
     * @param jobLevel
     * @return
     */
    public Integer updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKey(jobLevel);
    }

    /**
     * 批量删除职称级别
     * @param ids
     * @return
     */
    public Integer deleteJobLeveByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLeveByIds(ids);
    }

    public Integer daleteJobLevelById(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

}

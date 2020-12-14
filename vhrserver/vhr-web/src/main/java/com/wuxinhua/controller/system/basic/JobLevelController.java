package com.wuxinhua.controller.system.basic;


import com.wuxinhua.model.JobLevel;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.service.system.basic.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {

    @Autowired
    private JobLevelService jobLevelService;


    /**
     * 查询所有职称级别
     * @return
     */
    @GetMapping("/")
    public List<JobLevel> getJobLevels(){
        return jobLevelService.getJobLevels();
    }

    /**
     * 添加职称级别
     * @param jobLevel
     * @return
     */
    @PostMapping("/")
    public RespBean addjobLevel(@RequestBody JobLevel jobLevel){
        Integer i = jobLevelService.addjobLevel(jobLevel);
        if(i == 1){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("删除失败");
        }
    }

    /**
     * 更新职称级别
     * @param jobLevel
     * @return
     */
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        Integer i = jobLevelService.updateJobLevel(jobLevel);
        if(i == 1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    /**
     * 删除职称级别
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean daleteJobLevelById(@PathVariable(value = "id" ,required = true) Integer id){
        Integer i = jobLevelService.daleteJobLevelById(id);
        if(i == 1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
    @DeleteMapping("/ids")
    public RespBean deleteJobLeveByIds(@RequestParam(value = "ids") Integer[] ids){
        Integer i = jobLevelService.deleteJobLeveByIds(ids);
        if(i == ids.length){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
}

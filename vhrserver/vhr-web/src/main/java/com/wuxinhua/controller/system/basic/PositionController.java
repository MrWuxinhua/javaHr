package com.wuxinhua.controller.system.basic;

import com.wuxinhua.model.Position;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.service.system.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 基础信息设置，职位管理
 */
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 获取所有职务信息
     * @return
     */
    @GetMapping("/")
    public List<Position> getAllPosition(){
        return positionService.getAllPosition();
    }

    /**
     * 增加职务信息
     * @param position
     * @return
     */
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
       Integer i =  positionService.addPosition(position);
       if(i == 1){
           return RespBean.ok("添加成功");
       }
       return RespBean.error("添加失败");
    }

    /**
     * 更新职务信息
     * @param position
     * @return
     */
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        Integer i =  positionService.updatePosition(position);
        if(i == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 根据ID删除职务信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable(value = "id" ,required = true) Integer id){
        Integer i =  positionService.deletePositionById(id);
        if(i == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/delePosByIds")
    public RespBean deletePositionByIds(@RequestParam(value = "ids") Integer[] ids){
        Integer i = positionService.deletePositionByIds(ids);
        if(i == ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}

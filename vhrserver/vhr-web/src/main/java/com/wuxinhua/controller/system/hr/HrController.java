package com.wuxinhua.controller.system.hr;

import com.wuxinhua.model.Hr;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.model.Role;
import com.wuxinhua.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    private HrService hrService;

    @GetMapping("/")
    public List<Hr> getHrAll(@RequestParam(value = "keyWords" , required = false ) String keyWords){

        List<Hr> hrAll = hrService.getHrAll(keyWords);
        return hrAll;
    }


    @PutMapping("/")
    public RespBean updateRoleEnabledById(@RequestBody Hr hr){

        if(1==hrService.updateRoleEnabledById(hr)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }


    @GetMapping("/role")
    public List<Role> getAllRole(){
        return hrService.getAllRole();
    }

    @PutMapping("/role")
    public RespBean updateHrRoles(@RequestParam("hid") Integer hid , @RequestParam("rids") Integer[] rids ){
        if(rids.length == hrService.updateHrRoles(hid , rids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 逻辑删除操作员
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable(value = "id") Integer id){
        if(1==hrService.deleteHrById(id)){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");


    }
}

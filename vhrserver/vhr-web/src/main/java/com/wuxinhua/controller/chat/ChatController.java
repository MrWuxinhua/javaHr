package com.wuxinhua.controller.chat;

import com.wuxinhua.model.Hr;
import com.wuxinhua.service.HrService;
import com.wuxinhua.service.utils.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private HrService hrService;

    @GetMapping("/hrs")
    public List<Hr> getAllHrsWhitoutCurrentHr() {
        return hrService.getAllHrsWhitoutCurrentHr(HrUtil.getHr().getId());
    }
}

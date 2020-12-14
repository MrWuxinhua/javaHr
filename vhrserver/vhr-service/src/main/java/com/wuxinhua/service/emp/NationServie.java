package com.wuxinhua.service.emp;

import com.wuxinhua.mapper.NationMapper;
import com.wuxinhua.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServie {

    @Autowired
    private NationMapper nationMapper;


    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();
    }
}

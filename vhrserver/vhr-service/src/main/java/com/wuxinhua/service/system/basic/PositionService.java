package com.wuxinhua.service.system.basic;

import com.wuxinhua.mapper.PositionMapper;
import com.wuxinhua.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 获取所有职务信息
     * @return
     */
    public List<Position> getAllPosition() {
        return positionMapper.getAllPosition();
    }

    /**
     * 添加职务信息
     * @param position
     * @return
     */
    public Integer addPosition(Position position) {
        position.setCreateDate(new Date());
        position.setEnabled(true);
        return positionMapper.insert(position);
    }

    /**
     * 更新职务信息
     * @param position
     * @return
     */
    public Integer updatePosition(Position position) {
       return positionMapper.updateByPrimaryKey(position);
    }

    /**
     * 删除职务信息
     * @param id
     * @return
     */
    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public Integer deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}

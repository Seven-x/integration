package com.syx.springboot.ines.test.service;

import com.google.common.collect.Lists;
import com.syx.springboot.ines.test.entity.LeadsEntity;
import com.syx.springboot.ines.test.repository.LeadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author shaoyx
 * @date 10:58  2019/11/20
 */
@Service
public class LeadsService {

    @Autowired
    private LeadsRepository leadsRepository;

    /**
     * 根据id获取
     * @author shaoyx
     * @param id
     * @return
     */
    public LeadsEntity getOne(Integer id){
        Optional<LeadsEntity> optional = leadsRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 新增
     * @author shaoyx
     * @param leadsEntity
     * @return
     */
    public LeadsEntity save(LeadsEntity leadsEntity){
        return leadsRepository.save(leadsEntity);
    }
}

package com.syx.springboot.ines.test.repository;

import com.syx.springboot.ines.test.entity.LeadsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author shaoyx
 * @date 10:55  2019/11/20
 */
public interface LeadsRepository extends ElasticsearchRepository<LeadsEntity, Integer> {
    /**
     * 根据level 获取entity分页
     * @author shaoyx
     * @param level
     * @param pageable
     * @return
     */
    Page<LeadsEntity> getLeadsEntitiesByLevel(Integer level, Pageable pageable);
}

package com.syx.springboot.inutils.test.utils.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * BeanA <=> BeanB
 *
 * @author shaoyx
 * @date 18:39  2019/11/28
 */
public class BeanMapper {

    private static Mapper mapper = new DozerBeanMapper();

    /**
     * 简单的复制出 新类型对象
     *
     * @return
     * @author shaoyx
     */
    public static <S, D> D map(S source, Class<D> dClass) {
        if (null == source) {
            return null;
        }
        return mapper.map(source, dClass);
    }
}

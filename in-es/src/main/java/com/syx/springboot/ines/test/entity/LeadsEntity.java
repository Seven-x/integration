package com.syx.springboot.ines.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shaoyx
 * @date 10:52  2019/11/20
 */
@Data
@Document(indexName = "test_shaoyx_index.1", type = "employee")
public class LeadsEntity implements Serializable {

    @Id
    @Field(type = FieldType.Integer)
    private Integer leads_id;

    @Field(type = FieldType.Integer)
    private Integer out_id;

    @Field(type = FieldType.Integer)
    private Integer level;

    @Field(type = FieldType.Integer)
    private Integer source;

    @Field(type = FieldType.Integer)
    private Integer status_a;

    @Field(type = FieldType.Integer)
    private Integer status_b;

    @Field(type = FieldType.Integer)
    private Integer biz_month;

    @Field(type = FieldType.Integer)
    private Integer biz_date;

    @Field(type = FieldType.Date)
    private Date biz_time;

}

package com.ajaxjs.data.data_service;

import com.ajaxjs.data.crud.BaseConfig;
import com.ajaxjs.data.crud.FastCRUD;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

/**
 * 通用接口的数据库配置 PO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataServiceConfig extends BaseConfig {
    private Integer id;

    private Integer pid;

    /**
     * 说明
     */
    private String name;

    /**
     * 类型 SINGLE | CRUD
     */
    private String type;

    /**
     * 子配置
     */
    private Map<String, DataServiceConfig> children;

    private Date createDate;
}
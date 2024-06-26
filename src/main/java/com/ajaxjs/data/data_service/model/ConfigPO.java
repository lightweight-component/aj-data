package com.ajaxjs.data.data_service.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 通用接口的数据库配置 PO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConfigPO extends BaseDataServiceConfig {
    private Date createDate;
}
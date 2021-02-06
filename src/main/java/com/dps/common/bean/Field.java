package com.dps.common.bean;

import lombok.Data;

@Data
public class Field {
    /**
     * 对应列名
     */
    private String columnName;
    /**
     * 对应数据类型名称
     */
    private String dataTypeName;
    /**
     * 对应中文注释
     */
    private String remarks;

}

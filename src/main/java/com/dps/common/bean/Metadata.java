package com.dps.common.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class Metadata extends Model<Metadata> {

    @TableId(value="id")
    private Long id;

    @NotBlank(message = "数据库链接名称不可为空")
    private String name;
    @NotBlank(message = "数据库链接URL不可为空")
    private String url;
    private String username;
    private String password;
    @NotBlank(message = "数据库类型不可为空")
    private String databaseType;
    @NotBlank(message = "数据库驱动类型不可为空")
    private String driverClassType;
    private String properties;

}

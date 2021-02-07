package com.dps.common.type;

import com.dps.common.type.TypeMessage;

/**
 * Created by 凌战 on 2021/2/7
 */
public enum DriverClassType implements TypeMessage {

    POSTGRESQL("org.postgresql.Driver"),
    MYSQL("com.mysql.jdbc.Driver"),
    HIVE("org.apache.hive.jdbc.HiveDriver")

    ;

    private String msg;

    private DriverClassType(final String msg) {
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMsg() {
        return this.msg;
    }


}

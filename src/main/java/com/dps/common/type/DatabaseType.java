package com.dps.common.type;

public enum DatabaseType implements TypeMessage{

    POSTGRESQL("POSTGRESQL"),
    MYSQL("MYSQL"),
    HIVE("HIVE");

    private final String msg;

    private DatabaseType(String msg){
        this.msg=msg;
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

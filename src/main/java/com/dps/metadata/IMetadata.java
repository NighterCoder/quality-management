package com.dps.metadata;

import com.dps.common.bean.Field;
import com.dps.common.bean.Metadata;
import com.dps.common.bean.Schema;
import com.dps.common.bean.Table;
import com.dps.utils.Result;

import java.util.List;

/**
 * 获取不同类型数据库信息
 */
public interface IMetadata {

    /**
     * 测试连接是否成功
     * @param metadata 元数据信息
     * @return Result
     */
    Result<String> testConnection(Metadata metadata);

    List<Schema> getSchemas(Metadata metadata);

    List<Table> getTables(Metadata metadata,String schemaName);

    List<Field> getColumns(Metadata metadata,String schemaName,String tableName);



}

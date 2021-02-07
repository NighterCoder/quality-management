package com.dps.metadata;

import com.dps.common.TypeService;
import com.dps.common.bean.Field;
import com.dps.common.bean.Metadata;
import com.dps.common.bean.Schema;
import com.dps.common.bean.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 凌战 on 2021/2/7
 */
@Service
public class MysqlMetadata extends CommonMetadata {

    private final TypeService typeService;

    @Autowired
    public MysqlMetadata(TypeService typeService) {
        this.typeService = typeService;
    }


    @Override
    public List<Schema> getSchemas(Metadata metadata) {
        try {
            List<Schema> schemas=new ArrayList<>();
            Class.forName(this.typeService.getTypeMsg("DriverClassType", metadata.getDriverClassType()));
            try(Connection conn= DriverManager.getConnection(metadata.getUrl() + "?" + metadata.getProperties(), metadata.getUsername(), metadata.getPassword())){
                DatabaseMetaData meta=conn.getMetaData();
                ResultSet rs=meta.getCatalogs();
                while (rs.next()){
                    Schema schema=new Schema();
                    schema.setName(rs.getString(1));
                    schemas.add(schema);
                }
            }
            return schemas;
        } catch (Exception e) {
            throw new RuntimeException("getSchemas", e);
        }
    }

    @Override
    public List<Table> getTables(Metadata metadata, String schemaName) {
        try {
            List<Table> tables = new ArrayList<>();
            Class.forName(this.typeService.getTypeMsg("DriverClassType", metadata.getDriverClassType()));
            try(Connection conn= DriverManager.getConnection(metadata.getUrl()+"/"+schemaName + "?" + metadata.getProperties(), metadata.getUsername(), metadata.getPassword())){
                DatabaseMetaData meta=conn.getMetaData();
                ResultSet rs=meta.getTables(null,schemaName,null,new String[]{"TABLE"});
                while (rs.next()){
                   Table table=new Table();
                   table.setName(rs.getString("TABLE_NAME"));
                   tables.add(table);
                }
            }
            return tables;
        }catch (Exception e) {
            throw new RuntimeException("getTables", e);
        }
    }

    @Override
    public List<Field> getColumns(Metadata metadata, String schemaName, String tableName) {
        try {
            List<Field> fields = new ArrayList<>();
            Class.forName(this.typeService.getTypeMsg("DriverClassType", metadata.getDriverClassType()));
            try(Connection conn= DriverManager.getConnection(metadata.getUrl()+"/"+schemaName + "?" + metadata.getProperties(), metadata.getUsername(), metadata.getPassword())){
                DatabaseMetaData meta=conn.getMetaData();
                ResultSet rs=meta.getColumns(null,schemaName,tableName,null);
                while (rs.next()){
                    Field field=new Field();
                    field.setColumnName(rs.getString("COLUMN_NAME"));
                    field.setDataTypeName(rs.getString("TYPE_NAME"));
                    field.setRemarks(rs.getString("REMARKS"));
                    fields.add(field);
                }
            }
            return fields;
        }catch (Exception e) {
            throw new RuntimeException("getColumns", e);
        }
    }
}

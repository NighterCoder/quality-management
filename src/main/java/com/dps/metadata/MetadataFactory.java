package com.dps.metadata;

import com.dps.common.type.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by 凌战 on 2021/2/7
 */
@Service
public class MetadataFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public IMetadata createMetadata(String databaseType){
        if (DatabaseType.MYSQL.name().equals(databaseType)){
            return (IMetadata) this.applicationContext.getBean("mysqlMetadata");
        }
        if (DatabaseType.HIVE.name().equals(databaseType)) {
            return (IMetadata)this.applicationContext.getBean("hiveMetadata");
        }
        // todo
        throw new RuntimeException("无效的databaseType");
    }



}

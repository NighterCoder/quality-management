package com.dps.metadata;

import com.dps.common.TypeService;
import com.dps.common.bean.Metadata;
import com.dps.utils.Result;
import com.dps.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public abstract class CommonMetadata implements IMetadata {

    @Autowired
    protected TypeService typeService;

    @Override
    public Result<String> testConnection(Metadata metadata) {
        try {
            Class.forName(this.typeService.getTypeMsg("DriverClassType",metadata.getDriverClassType()));
            DriverManager.getConnection(metadata.getUrl()+"?"+metadata.getProperties(), metadata.getUsername(), metadata.getPassword());
            return Result.status(Status.OK);
        } catch (ClassNotFoundException | SQLException e) {
            Result<String> res=Result.status(Status.METADATA_CONN_FAIL);
            res.setData(e.getMessage());
            return res;
        }
    }
}

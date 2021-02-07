package com.dps.metadata.domain;

import com.dps.common.bean.Metadata;
import com.dps.common.bean.Schema;
import com.dps.metadata.MetadataFactory;
import com.dps.metadata.domain.service.IMetadataService;
import com.dps.metadata.domain.service.MetadataWrapperService;
import com.dps.utils.Result;
import com.dps.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 凌战 on 2021/2/7
 */
@Service
public class MetadataService {

    @Autowired
    private IMetadataService metadataService;
    @Autowired
    private MetadataFactory metadataFactory;
    @Autowired
    private MetadataWrapperService wrapperService;

    /**
     * 根据条件查询所有的源数据库
     * @param metadata
     */
    public Result<List<Metadata>> getMetadatas(Metadata metadata){
        Result result=Result.status(Status.OK);
        List<Metadata> metadatas=this.metadataService.list(this.wrapperService.getQueryWrapper(metadata));
        result.setData(metadatas);
        return result;
    }

    /**
     * 根据id查询源数据库
     * @param id 指定的源数据库id
     */
    public Result<Metadata> getMetadata(Long id){
        Result result=Result.status(Status.OK);
        result.setData(this.metadataService.getById(id));
        return result;
    }

    /**
     * 根据源数据库id查询指定的源schema
     * @param id 源数据库id
     */
    public Result<List<Schema>> getSchemas(Long id){
        return null;
    }



}

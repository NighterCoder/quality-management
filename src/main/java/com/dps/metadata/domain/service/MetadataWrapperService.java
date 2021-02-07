package com.dps.metadata.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dps.common.bean.Metadata;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by 凌战 on 2021/2/7
 */
@Service
public class MetadataWrapperService {

    public QueryWrapper getQueryWrapper(Metadata metadata) {
        QueryWrapper wrapper = new QueryWrapper();
        if (Objects.nonNull(metadata.getName()) && !metadata.getName().equals("")) {
            wrapper.like("name", metadata.getName());
        }
        if (Objects.nonNull(metadata.getUrl()) && !metadata.getUrl().equals("")) {
            wrapper.like("url", metadata.getUrl());
        }
        if (Objects.nonNull(metadata.getDatabaseType()) && !metadata.getDatabaseType().equals("")) {
            wrapper.eq("database_type", metadata.getDatabaseType());
        }
        if (Objects.nonNull(metadata.getDriverClassType()) && !metadata.getDriverClassType().equals("")) {
            wrapper.eq("driver_class_type", metadata.getDriverClassType());
        }
        return wrapper;
    }


}

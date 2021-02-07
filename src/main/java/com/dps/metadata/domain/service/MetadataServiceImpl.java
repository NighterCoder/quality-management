package com.dps.metadata.domain.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dps.common.bean.Metadata;
import com.dps.metadata.domain.mapper.MetadataMapper;
import org.springframework.stereotype.Service;

/**
 * Created by 凌战 on 2021/2/7
 */
@Service
public class MetadataServiceImpl extends ServiceImpl<MetadataMapper, Metadata> implements IMetadataService {
}

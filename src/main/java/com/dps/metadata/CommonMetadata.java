package com.dps.metadata;

import com.dps.common.TypeService;
import org.springframework.stereotype.Service;

@Service
public abstract class CommonMetadata implements IMetadata {

    protected TypeService typeService;

}

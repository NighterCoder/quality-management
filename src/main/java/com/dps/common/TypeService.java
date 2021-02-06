package com.dps.common;

import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService implements CommandLineRunner {


    private Map<String, List<JSONObject>> typeMap;

    @Override
    public void run(String... args) throws Exception {
        this.typeMap=new HashMap<>();
        // 加载所有的Type类型
        String typePath="com.dps.common.type";





    }
}

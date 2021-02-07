package com.dps.common;

import com.alibaba.fastjson.JSONObject;
import com.dps.utils.ClassUtils;
import com.dps.utils.Result;
import com.dps.utils.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TypeService implements CommandLineRunner {


    private Map<String, List<JSONObject>> typeMap;

    @Override
    public void run(String... args) throws Exception {
        this.typeMap = new HashMap<>();
        // 加载所有的Type类型
        String typePath = "com.dps.common.type";
        // 获取所有Enum类的对应类和值

        // 1. 先去获取对应类的全类名路径名
        Set<String> enumNames = ClassUtils.getClassName(typePath, true);
        for (String enumName : enumNames) {
            // 2. 使用Class.forName反射加载
            Class<?> classType = Class.forName(enumName);
            // 3. 返回枚举类的元素数组
            Object[] objects = classType.getEnumConstants();

            Method getCode = classType.getDeclaredMethod("getCode");
            Method getMsg = classType.getDeclaredMethod("getMsg");
            List<JSONObject> values = new ArrayList<>();
            if (objects != null && objects.length > 0) {
                for (Object currentEnum : objects) {
                    JSONObject jo = new JSONObject();
                    jo.put("code", getCode.invoke(currentEnum));
                    jo.put("msg", getMsg.invoke(currentEnum));
                    values.add(jo);
                }
            }
            String typeName = enumName.replace(typePath + ".", "");
            this.typeMap.put(typeName, values);
        }
    }


    /**
     * 根据枚举类的类名获取对应的枚举值
     *
     * @param name 枚举类类名
     */
    public Result<List<JSONObject>> getType(String name) {
        Result result = Result.status(Status.OK);
        result.setData(this.typeMap.get(name));
        return (Result<List<JSONObject>>) result;
    }


    /**
     * 获取所有的枚举类型
     */
    public Result<List<JSONObject>> getTypes() {
        Result result = Result.status(Status.OK);
        List<JSONObject> values = new ArrayList<>();
        this.typeMap.forEach((k, v) -> {
            JSONObject jo = new JSONObject();
            jo.put("typeName", k);
            jo.put("typeList", v);
            values.add(jo);
        });
        result.setData(values);
        return result;
    }

    /**
     * 根据枚举类名和枚举值的键,获取对应值
     *
     * @param name 枚举类名
     * @param code 枚举值的键
     */
    public String getTypeMsg(String name, String code) {
        List<JSONObject> obj = this.typeMap.get(name)
                .stream().filter(js -> js.get("code").equals(code))
                .collect(Collectors.toList());
        return obj.get(0).getString("msg");
    }


}

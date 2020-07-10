package com.github.ican2056.doit;

import com.github.ican2056.doit.ParamsU;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ParamsUTest {
    HashMap<String, String> testMap = new HashMap<>();
    {
        testMap.put("k1", "v1");
        testMap.put("k2", "v2");
        testMap.put("k3", "v3");
    }
    String testParamsStr = "k1=v1&k2=v2&k3=v3";

    @Test
    public void testParamsStr2Map() {
        Map<String, String> map = ParamsU.paramsStr2Map(testParamsStr);
        map.entrySet().stream().forEach(item -> Assert.assertEquals(item.getValue(), map.get(item.getKey())));
    }

    @Test
    public void testMap2ParamsStr(){
        String str = ParamsU.map2ParamsStr(testMap);
        Assert.assertEquals(str, testParamsStr);
    }

}

package com.liwenwen.common;

import com.google.gson.*;

import java.util.*;

public class JsonUtils {
    /**
     * 获取JsonObject
     * @param json
     * @return
     */
    public static JsonObject parseJson(String json){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }
    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json){
        System.out.println(json);
        List<Object> list = new ArrayList<Object>();
        for (int i=0; i<json.size(); i++){
            Object value = json.get(i);
            System.out.println(value);
            if(value instanceof JsonArray){
                System.out.println("JsonArray:"+value);
                list.add(toList((JsonArray) value));
            }
            else if(value instanceof JsonObject){
                System.out.println("JsonObject:"+value);
                list.add(toMap((JsonObject) value));
            }
            else{
                Gson gson = new Gson();
                System.out.println(value);
                URL url1 =  gson.fromJson((String) value, URL.class);
                list.add(url1);
            }
        }
        return list;
    }
    /**
     * 将JSONObjec对象转换成Map-List集合
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json)  {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JsonArray)
                map.put( key, toList((JsonArray) value));
            else if(value instanceof JsonObject)
                map.put( key, toMap((JsonObject) value));
            else
                map.put( key, value);
        }

        return map;
    }
}

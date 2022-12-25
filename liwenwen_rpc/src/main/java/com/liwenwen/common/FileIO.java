package com.liwenwen.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.List;
import java.util.Map;


public class FileIO {
    public static String fileName = "register.json";
    public static File file = new File(fileName);
    public static void write(Map<String, List<URL>> map)  {
        Gson JSON= new Gson();
        String json=JSON.toJson(map);
        try
        {   // 判断key 是否存在
            // 写前先读
            if(!json.equals(read())){
                JsonObject jsonObject =new JsonParser().parse(json).getAsJsonObject();
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(jsonObject.toString());
                bw.close();
                read();
            }
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            File file = new File(fileName);
            write(map);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public static String read() {
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            char cbuf[] = new char[10000];
            int len =reader.read(cbuf);
            String text =new String(cbuf,0,len);
            fileReader.close();
            reader.close();
            return text;
//            JsonObject jsonObject =new JsonParser().parse(text).getAsJsonObject();
//            Map<String, Object> stringObjectMap = toMap(jsonObject);

//            System.out.println("read"+stringObjectMap.get("com.liwenwen.HelloService"));
            //return ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




//    public static String getHashValueByStr(String str) {
//        try {
//            // 创建获取一个MessageDigest对象,并且指定计算的类型： MD2|MD5|SHA-1|SHA-256|SHA-384|SHA-512
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            messageDigest.reset();
//            byte[] digest = messageDigest.digest(str.getBytes("utf-8"));
//
//            // hash数组为32位，否则就hash失败
//            if (digest.length != 32) {
//                System.out.println("计算hash值失败");
//                return null;
//            }
//
//            // 将计算得到的字节数组转为十六进制--并转换成字符串
//            return hex(digest);
//        } catch (UnsupportedEncodingException u) {
//            System.exit(-2);
//        } catch (NoSuchAlgorithmException n) {
//            System.exit(-3);
//        }
//        return null;
//    }
//    public static String hex(byte[] bytes) {
//        StringBuilder result = new StringBuilder();
//        for (byte aByte : bytes) {
//            result.append(String.format("%02x", aByte));
//            // upper case
//            // result.append(String.format("%02X", aByte));
//        }
//        return result.toString();
//    }





}

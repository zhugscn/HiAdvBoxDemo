package cn.zhuguangsheng.hiadvbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvMyStringUtil {
    public static List<String> strArr2strList(String[] strArr){
        if(strArr==null){
            return new ArrayList<String>();
        }
        List<String> list = Arrays.asList(strArr);
        return list;
    }

    public static String[] strList2StrArr(List<String> list){
        String[] arr = list.toArray(new String[list.size()]);
        return arr;
    }

    public static String getFileNameFromUrl(String url){
        if(url==null || url.isEmpty()){
            return "";
        }

        String fileName = "";
        try {
            fileName = url.substring(url.lastIndexOf("/"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return fileName;
    }

    /**
     * 把字符串中间部分隐藏
     * @param info
     * @param hideLength 替换位数
     * @return
     */
    public static synchronized String getStringFirstAndEnd(String info, int hideLength){
        if(info==null || info.isEmpty()){
            return "";
        }

        String replaceSymbol = "*";//替换符号，这里用“*”为例

        //hideLength过长
        if(hideLength >= info.length()){
            StringBuffer sb = new StringBuffer("");
            for(int i=0;i<info.length();i++){
                sb.append(replaceSymbol);
            }
            return sb.toString();
        }

        //正常的长度
        int startIndex = info.length()/2-hideLength/2;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < info.length(); i++){
            char number = info.charAt(i);
            if (i >= startIndex-1 && i < startIndex + hideLength){
                stringBuilder.append(replaceSymbol);
            }else {
                stringBuilder.append(number);
            }
        }
        return stringBuilder.toString();
    }
}

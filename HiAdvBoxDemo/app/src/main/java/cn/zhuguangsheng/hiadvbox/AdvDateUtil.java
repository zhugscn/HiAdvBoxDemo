package cn.zhuguangsheng.hiadvbox;

import java.util.Date;

public class AdvDateUtil {
    public static long diffSecond(Date startDate, Date endDate) {
        long diff = (endDate.getTime() - startDate.getTime()) / 1000;//这样得到的差值是微秒级别
        return diff;
    }
}

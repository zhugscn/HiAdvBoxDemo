package cn.zhuguangsheng.hiadvbox;

import java.util.Date;

/**
 * 播放事件的接口
 */
public interface IAdvPlayEventListener {
    //播放记录成功
    public void onPlayAdvItemResult(boolean isSucceed,
                                    String resourceId,
                                     int resourceType,
                                     int actualDuration,
                                     Date startTime,
                                     Date endTime);

}

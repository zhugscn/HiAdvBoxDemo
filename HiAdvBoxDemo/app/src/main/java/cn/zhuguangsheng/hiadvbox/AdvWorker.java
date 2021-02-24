package cn.zhuguangsheng.hiadvbox;

import androidx.viewpager2.widget.ViewPager2;

public class AdvWorker {

    public static final int EVENT_IMAGE_PLAY_SUCCESS = 1001;
    public static final int EVENT_VIDEO_PLAY_SUCCESS = 1002;

    private ViewPager2 mVp2;
    private int mPosition = 0;

    public AdvWorker(ViewPager2 vp2) {
        mVp2 = vp2;
    }

    public void restart(){
        mPosition = 0;
    }

    /**
     * 发送事件
     * @param eventType
     * @param paramInt
     * @param paramStr
     * @param obj
     */
    public void sendEvent(int eventType, int paramInt, String paramStr, Object obj){
        switch (eventType){
            case EVENT_IMAGE_PLAY_SUCCESS:
                mPosition ++;
                mVp2.setCurrentItem(mPosition);

                break;
            case EVENT_VIDEO_PLAY_SUCCESS:
                mPosition ++;
                mVp2.setCurrentItem(mPosition);

                break;
            default:
                break;
        }
    }

}

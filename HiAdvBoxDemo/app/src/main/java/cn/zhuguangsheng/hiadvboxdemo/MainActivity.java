package cn.zhuguangsheng.hiadvboxdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.zhuguangsheng.hiadvbox.HiAdvBox;
import cn.zhuguangsheng.hiadvbox.HiAdvItem;
import cn.zhuguangsheng.hiadvbox.IAdvPlayEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    HiAdvBox hi_adv_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //startPlay();
    }

    private void init(){
        AndPermission.with(this)
                .runtime()
                .permission(new String[]{Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE})
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                    //grantOnTop();
                    startPlay();
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                })
                .start();
    }

    private void startPlay(){
        hi_adv_box = findViewById(R.id.hi_adv_box);
        hi_adv_box.init(this);

        List<HiAdvItem> list = new ArrayList<>();
        //请先把myres资源手动拷贝到/mnt/sdcard/advpub 目录下。真正运行时，应该是从网上下载并自动放于/mnt/sdcard/advpub目录的
        list.add(new HiAdvItem(UUID.randomUUID().toString(), 0, 2, "/mnt/sdcard/advpub/1.jpg"));
        list.add(new HiAdvItem(UUID.randomUUID().toString(), 0, 3, "/mnt/sdcard/advpub/2.jpg"));
        list.add(new HiAdvItem(UUID.randomUUID().toString(), 1, 0, "/mnt/sdcard/advpub/wildlife.mp4"));
        //hi_adv_box.setData(list);
        //hi_adv_box.startWork();
        hi_adv_box.restartWork(list);
        hi_adv_box.setAdvEventListener(new IAdvPlayEventListener() {
            @Override
            public void onPlayAdvItemResult(boolean isSucceed, String resourceId, int resourceType, int actualDuration, Date startTime, Date endTime) {
                Log.i(TAG, "外部外部 播放一条 item played. resourceType=" + resourceType
                        + ", actualDuration=" + actualDuration
                        + ", startTime=" + startTime
                        + ", endTime=" + endTime
                );
            }
        });
    }

}
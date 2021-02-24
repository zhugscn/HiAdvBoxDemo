package cn.zhuguangsheng.hiadvbox;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.histonepos.hiadvpub.R;
//import com.histonepos.hiadvpub.bean.BaseEventBean;
//import com.histonepos.hiadvpub.bean.EventActions;
//import com.histonepos.hiadvpub.util.DateUtil;
//import com.yanzhenjie.permission.AndPermission;
//
//import org.greenrobot.eventbus.EventBus;

import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.util.Date;

import cn.zhuguangsheng.hiadvboxdemo.R;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    private static final String TAG = VideoFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //@BindView(R.id.vv1)
    VideoView vv1;

//    @BindView(R.id.playerView)
//    PlayerView playerView;
    //private String mFilename;
    HiAdvItem mAdvItem;
    IAdvPlayEventListener mListener;
    Date startTime;
    Date endTime;

    //static WeakReference<AdvWorker> mWorkerRef;

    public VideoFragment(){

    }

    public VideoFragment(HiAdvItem item, IAdvPlayEventListener listener){
        mAdvItem = item;
        mListener = listener;
    }

    public static Fragment newInstance(HiAdvItem item, IAdvPlayEventListener listener) {
        //mWorkerRef = new WeakReference<AdvWorker>(worker);
        return new VideoFragment(item,listener);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        if(AndPermission.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //执行业务
            //Toast.makeText(getActivity(), "有文件读写权限", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "有文件读写权限");
        }else {
            //申请权限
            //Toast.makeText(getActivity(), "无文件读写权限!!!", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "无文件读写权限");
        }

        //EventBus.getDefault().register(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 在Fragment中这句话不能注释，否则Fragment接收不到获取权限的通知。
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.i(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.i(TAG, "onStart()");


    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause()");
        vv1.stopPlayback();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        if(vv1==null){
            Log.e(TAG, "videoView is null!");
        }
        if(!new File(mAdvItem.getLocalResourceFilePath()).exists()){
            Log.e(TAG, "要播放的文件不存在" + mAdvItem.getLocalResourceFilePath());
            startTime = new Date();
            endTime = startTime;
            mListener.onPlayAdvItemResult(false,
                    mAdvItem.getResourceId(),
                    AdvConstants.RES_TYPE_VIDEO,
                    (int) AdvDateUtil.diffSecond(startTime, endTime),
                    startTime,
                    endTime
            );
            return;
        }
        try {
            vv1.setVideoPath(mAdvItem.getLocalResourceFilePath());
            vv1.seekTo(0);
            vv1.start();
            startTime = new Date();
            vv1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i(TAG, "play video end");
                    endTime = new Date();
                    mListener.onPlayAdvItemResult(true,
                            mAdvItem.getResourceId(),
                            AdvConstants.RES_TYPE_VIDEO,
                            (int) AdvDateUtil.diffSecond(startTime, endTime),
                            startTime,
                            endTime
                    );
                }
            });
            vv1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.w(TAG, "play video error");
                    endTime = new Date();
                    mListener.onPlayAdvItemResult(false,
                            mAdvItem.getResourceId(),
                            AdvConstants.RES_TYPE_VIDEO,
                            (int) AdvDateUtil.diffSecond(startTime, endTime),
                            startTime,
                            endTime
                    );
                    return false;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

//        Bundle bundleParams = new Bundle();
//        String startTimeStr = DateUtil.getStandardDateTimeString(new Date());
//        vv1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                //post event to switch next
//                Log.i(TAG, "play a video end, mFilename=" + mFilename);
//                String endTimeStr = DateUtil.getStandardDateTimeString(new Date());
//                long playtime = DateUtil.diffSecond(endTimeStr, startTimeStr);
//                bundleParams.putString("type", "1");    //"1": video
//                bundleParams.putString("startTimeStr", startTimeStr);
//                bundleParams.putString("playtime", String.valueOf(playtime));
//                bundleParams.putString("endTimeStr", endTimeStr);
//                bundleParams.putString("isFullPlay", "1");
//                EventBus.getDefault().post(new BaseEventBean(EventActions.ADV_FRONT_VIEWPAGER_NEXTPAGE, bundleParams));
//            }
//        });
//        vv1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                String endTimeStr = DateUtil.getStandardDateTimeString(new Date());
//                long playtime = DateUtil.diffSecond(endTimeStr, startTimeStr);
//                bundleParams.putString("type", "1");    //"1": video
//                bundleParams.putString("startTimeStr", startTimeStr);
//                bundleParams.putString("playtime", String.valueOf(playtime));
//                bundleParams.putString("endTimeStr", endTimeStr);
//                bundleParams.putString("isFullPlay", "0");
//                EventBus.getDefault().post(new BaseEventBean(EventActions.ADV_FRONT_VIEWPAGER_NEXTPAGE, bundleParams));
//                return false;
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        //ButterKnife.bind(this, view);

        Log.i(TAG, "onCreateView()");
        vv1 = view.findViewById(R.id.vv1);
        //vv1.setVideoPath(mAdvItem.getLocalResourceFilePath());
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}

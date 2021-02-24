# HiAdvBoxDemo
一个适合商业广告机轮播图片和视频资源的控件

Android Widget for play multi advs on Android Advertisement Device
multi advertisement with different time duration, resource range from image to video

> 1. 需求提出

* 需要适合商业广告机，能自动轮播
* 要能支持各种酷炫切换效果
* 需要播放图片和视频
* 需要播放从服务器下发下来的资源，考虑时延必须先下载再在本地播放

> 2. 传统方案痛点

一般网上的控件，例如
https://github.com/youth5201314/banner， 是适合手机主页广告的，每个资源自动轮播时间是一样长。不适合广告机。

首先广告机可能有视频，得以视频播放完为准
再一个广告机每个图片广告时长可能不同，和计费有关


> 3. 本方案特点

* 可以每个广告资源分配不同的时间播放的长度
* 提供播放记录接口。 自定义开发，以便于存储和上传。
* 可以给viewPager自定义轮播方法setPageTransformer()并且已经提供了21个方法，基本够用。满足客户层出不同的花样需求

> 4. 使用方法

- 先把myres下的几个资源拷贝到 /mnt/sdcard/advpub/目录下
  实际软件是应该从服务器下载并放于此目录的。这里我们模拟一下

- 把cn.zhuguangsheng.hiadvbox拷贝到您的源码目录
- 配置AndroidManifest.xml权限
  ```
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> <!-- internet -->
    <uses-permission android:name="android.permission.INTERNET" />
  ```
- 配置外部库
  ```
    //viewpager翻页器
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    
    //图片加载库
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'

    //andpermission
    implementation 'com.yanzhenjie:permission:2.0.3'
  ```
- 调用方法在这里 MainActivity.java

```
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
```

> 5 运行效果
<iframe height=498 width=510 src="./HiAdvBoxDemo/myres/intro1.mp4">


因为时间有限，暂时没有把控件去抽离。以后有时间再抽离上传中央仓库

```
简书 青岛大桥 https://www.jianshu.com/u/488d4c562cc7

公众号 搜索 大桥说说--大桥兄弟和你说说 软件、创业、文化、金融二三事

```

以上源码在github地址
https://github.com/zhugscn/HiAdvBoxDemo

如果喜欢，请star, 感谢。
茫茫网海，能和你相遇，已经是很不可思议了，快留下足迹^-^

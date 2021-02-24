package cn.zhuguangsheng.hiadvbox.pager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class SimpleFadeTransformer implements ViewPager2.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        float alpha = 0.0f;
        if (0.0f <= position && position <= 1.0f) {
            alpha = 1.0f - position;
        } else if (-1.0f <= position && position < 0.0f) {
            alpha = position*0.5f + 1.0f;
        }
        page.setAlpha(alpha);
    }
}

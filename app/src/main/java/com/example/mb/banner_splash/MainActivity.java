package com.example.mb.banner_splash;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private List<MyView> mList;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private ImageView[] diandianArray;//点数组
    private ImageView imageView;//显示数组图片的View
    private TextView oneTitle;//大标题
    private TextView twoTitle;//小标题
    private ImageView leftImage;//左边虚线
    private ImageView rightImage;
    private TextView mxperience;//立即体验
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        getSupportActionBar().hide();
        mLinearLayout =findViewById(R.id.layout);
        mxperience = findViewById(R.id.tv_skip);

        //设置点点的view
        diandianArray = new ImageView[5];
        for (int i = 0; i < diandianArray.length; i++) {
            imageView = new ImageView(this);
            diandianArray[i] = imageView;
            if (i == 0) {
                diandianArray[i].setBackgroundResource(R.drawable.guide_point_sel);
            } else {
                diandianArray[i].setBackgroundResource(R.drawable.guide_point_nor);
            }

            //此处通过方法来设置点点的布局属性，并将点点的布局属性放入到xml中的mLinearLayout，显示出来
            LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.width = 15;
            linearLayout.height = 15;
            linearLayout.leftMargin = 15;
            linearLayout.rightMargin = 15;

            mLinearLayout.addView(imageView, linearLayout);
        }
        mList = new ArrayList<>();
        for (int j=0;j<5;j++){
            MyView myView =new MyView(this,j);
            mList.add(myView);
        }
        mViewPager =findViewById(R.id.vp_photo);
        mViewPager.setAdapter(new ContentAdapter());
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        for (int j = 0; j< diandianArray.length; j++) {
            mxperience.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            diandianArray[j].setBackgroundResource(R.drawable.guide_point_sel);
            if (j != i) {
                diandianArray[j].setBackgroundResource(R.drawable.guide_point_nor);
            }
        }

        if (i == diandianArray.length - 1) {
            mLinearLayout.setVisibility(View.GONE);
            mxperience.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class ContentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view== o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }
    }
}

package com.example.mb.banner_splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private LinearLayout mLinearLayout;
    private ViewPager viewPager;
    private ImageView imageView;//显示数组图片的View
    private ImageView[] diandianArray;//点数组
    private TextView mxperience;//立即体验

    private List<Fragment> mFragments;
    private GuideAdapter mGuideAdapter;
    private TextView oneTitle;//大标题
    private TextView twoTitle;//小标题
    private ImageView leftImage;//左边虚线
    private ImageView rightImage;
    private ImageView ivImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mxperience = findViewById(R.id.tv_skip);
        mFragments = new ArrayList<Fragment>();

        mFragments.add(GuideFragment.newInstance("喵喵机4.0", "全新设计、极致体验", R.drawable.guide_img__one,R.drawable.guide_line_left_short, R.drawable.guide_line_right_short));
        mFragments.add(GuideFragment.newInstance("丰富错题打印功能", "拍题优化、更快更准", R.drawable.guide_img_two, R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
        mFragments.add(GuideFragment.newInstance("广场社区全新升级", "优质内容推荐、使用技巧分享", R.drawable.guide_img_three, R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
        mFragments.add(GuideFragment.newInstance("消息动态、实时通知", "广场动态分秒更新，时刻掌握最新消息", R.drawable.guide_img__four, R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
        mFragments.add(GuideFragment.newInstance(" 积分福利社 ", "喵币积分乐园 花式福利派送", R.drawable.guide_img_five, R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
        mGuideAdapter = new GuideAdapter(getSupportFragmentManager(), mFragments);

        //xml中的LinearLayout布局
        mLinearLayout = (LinearLayout) findViewById(R.id.layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(this);
//        mList = new ArrayList<>();
//        mList.add(new GuideData(new ImageView(this), R.drawable.guide_img__one, "喵喵机4.0", "全新设计、极致体验", R.drawable.guide_line_left_short, R.drawable.guide_line_right_short));
//        mList.add(new GuideData(new ImageView(this), R.drawable.guide_img_two, "丰富错题打印功能", "拍题优化、更快更准", R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
//        mList.add(new GuideData(new ImageView(this), R.drawable.guide_img_three, "广场社区全新升级", "优质内容推荐、使用技巧分享", R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
//        mList.add(new GuideData(new ImageView(this), R.drawable.guide_img__four, "消息动态、实时通知", "广场动态分秒更新，时刻掌握最新消息", R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));
//        mList.add(new GuideData(new ImageView(this), R.drawable.guide_img_five, "   积分福利社   ", "喵币积分乐园 花式福利派送", R.drawable.guide_line_left_long, R.drawable.guide_line_right_long));

        //设置点点的view
        diandianArray = new ImageView[5];
        for (int i = 0; i < diandianArray.length; i++) {
            imageView = new ImageView(this);
            diandianArray[i] = imageView;
            if (i == 0) {
                diandianArray[i].setImageResource(R.drawable.guide_point_sel);
            } else {
                diandianArray[i].setImageResource(R.drawable.guide_point_nor);
            }

            //此处通过方法来设置点点的布局属性，并将点点的布局属性放入到xml中的mLinearLayout，显示出来
            LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.leftMargin = 15;
            linearLayout.rightMargin = 15;

            mLinearLayout.addView(imageView, linearLayout);
        }
        viewPager.setAdapter(mGuideAdapter);
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
//        //设置大标题
//        oneTitle.setText(mList.get(position).getTitleOne());
//        leftImage.setImageResource(mList.get(position).getLeftImage());
//        rightImage.setImageResource(mList.get(position).getRightImage());

        for (int i = 0; i < diandianArray.length; i++) {
            mxperience.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            diandianArray[position].setImageResource(R.drawable.guide_point_sel);
            if (i != position) {
                diandianArray[i].setImageResource(R.drawable.guide_point_nor);
            }
        }

//        twoTitle.setText(mList.get(position).getTitleTwo());
        if (position == diandianArray.length - 1) {
            mLinearLayout.setVisibility(View.GONE);
            mxperience.setVisibility(View.VISIBLE);
        }
    }

//    public class ContentAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mList.get(position).getImageView());
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mList.get(position).getImageView(), 0);
//            return mList.get(position).getImageView();
//        }
//    }


}

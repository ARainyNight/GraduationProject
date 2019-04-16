package com.edu.shg_android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.edu.shg_android.R;
import com.edu.shg_android.adapter.CommodityAdapter;
import com.edu.shg_android.entity.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class HomeFragment extends Fragment {


    private List<Commodity> commodityList = new ArrayList<>();

    //轮播图
    private SliderLayout mSlider;
    private String[] imgName = {"新书上架", "电脑", "热门服装", "日用电器"};
    private int[] imgId = {R.drawable.shu, R.drawable.diannao, R.drawable.fuzhuang, R.drawable.dianqi};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        //初始化轮播图
        initBanner(view);
        //初始化Recyclerview;
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {

        //初始化商品信息
        initCommodity();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.home_fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        CommodityAdapter adapter  = new CommodityAdapter(commodityList);
        recyclerView.setAdapter(adapter);
    }

    private void initCommodity() {

        for (int i = 0; i <6 ; i++) {
            Commodity commodity1 = new Commodity("二手苹果电脑 九成新","5000.0","2019-5-7",R.mipmap.ic_launcher,"卖家1");
            Commodity commodity2 = new Commodity("二手相机 九成新","3500.0","2019-6-1",R.mipmap.ic_launcher,"卖家2");
            Commodity commodity3 = new Commodity("SpringBoot开发大全","100.0","2019-4-1",R.mipmap.ic_launcher,"卖家3");
            Commodity commodity4 = new Commodity("SSM框架集合 ","60.0","2018-10-10",R.mipmap.ic_launcher,"卖家4");

            commodityList.add(commodity1);
            commodityList.add(commodity2);
            commodityList.add(commodity3);
            commodityList.add(commodity4);
        }
    }

    private void initBanner(View view) {
        mSlider = view.findViewById(R.id.slider);

        for (int i = 0; i < 4; i++) {
            TextSliderView textSliderView = new TextSliderView(this.getActivity());
            textSliderView.image(imgId[i]);
            textSliderView.description(imgName[i]);
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
            mSlider.addSlider(textSliderView);
        }
        //默认的indicator
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //自定义的indicator
        //mSliderLayout.setCustomIndicator(indicator);
        mSlider.setCustomAnimation(new DescriptionAnimation());//动画效果
        mSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSlider.setDuration(3000);

        mSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSlider.stopAutoCycle();
    }
}

package com.edu.shg_android.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.edu.shg_android.R;
import com.edu.shg_android.adapter.CommodityAdapter;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.CommodityJs;

import com.edu.shg_android.ui.activity.CommodityShowActivity;
import com.edu.shg_android.ui.activity.SearchActivity;
import com.edu.shg_android.ui.activity.SortActivity;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private SearchView mSearchView;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private String[] data = {"java", "C", "C++", "C#", "入门"};


    private List<CommodityJs.DataBean> commodityList = new ArrayList<>();
    private CommodityAdapter adapter;
    private RecyclerView recyclerView;

    private TextView tof_city_text;
    private List<HotCity> hotCities;
    private int anim;
    private int theme;
    private boolean enable;
    private static final String KEY = "current_theme";

    //分类
    private LinearLayout phone_layout;
    private LinearLayout book_layout;
    private LinearLayout digital_layout;
    private LinearLayout clothing_layout;
    private LinearLayout computer_layout;
    private LinearLayout electric_layout;
    private LinearLayout office_layout;
    private LinearLayout other_layout;


    //轮播图
    private SliderLayout mSlider;
    private String[] imgName = {"新书上架", "电脑", "热门服装", "日用电器"};
    private int[] imgId = {R.drawable.shu, R.drawable.diannao, R.drawable.fuzhuang, R.drawable.dianqi};

    private LoadingDialog mDialog;

    private BaseApplication baseApplication;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(KEY);
            getActivity().setTheme(theme > 0 ? theme : R.style.DefaultCityPickerTheme);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {

        //初始化轮播图
        initBanner(view);
        //初始化Recyclerview;
        initRecyclerView(view);
//        //初始化地图选择器
//        initCityPicker(view);
        //初始化分类
        ininSort(view);

        //初始化搜素框
        initSearch(view);
    }

    private void initSearch(View view) {
        mListView = (ListView)view.findViewById(R.id.searchListView);
        mAdapter =new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);

        mSearchView =(SearchView)view.findViewById(R.id.tof_search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("searchname",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void ininSort(View view) {
        view.findViewById(R.id.phone_layout).setOnClickListener(this);
        view.findViewById(R.id.book_layout).setOnClickListener(this);
        view.findViewById(R.id.digital_layout).setOnClickListener(this);
        view.findViewById(R.id.clothing_layout).setOnClickListener(this);
        view.findViewById(R.id.computer_layout).setOnClickListener(this);
        view.findViewById(R.id.electric_layout).setOnClickListener(this);
        view.findViewById(R.id.office_layout).setOnClickListener(this);
        view.findViewById(R.id.other_layout).setOnClickListener(this);
    }

//    private void initCityPicker(View view) {
//        tof_city_text = (TextView) view.findViewById(R.id.tof_city_text);
//        tof_city_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CityPicker.getInstance()
//                        .setFragmentManager(getActivity().getSupportFragmentManager())
//                        .enableAnimation(enable)
//                        .setAnimationStyle(anim)
//                        .setLocatedCity(null)
//                        .setHotCities(hotCities)
//                        .setOnPickListener(new OnPickListener() {
//                            @Override
//                            public void onPick(int position, City data) {
//                                tof_city_text.setText(data.getName());
//                            }
//
//                            @Override
//                            public void onLocate() {
//                                //开始定位，这里模拟一下定位
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        CityPicker.getInstance().locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
//                                    }
//                                }, 3000);
//                            }
//                        })
//                        .show();
//            }
//        });
//    }

    private void initRecyclerView(View view) {

        mDialog = new LoadingDialog(getContext());
        mDialog.setLoadingText("加载中...")
                .setSuccessText("加载成功")
                .setFailedText("加载失败")
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
                .setRepeatCount(0)
                .setDrawColor(Color.WHITE)
                .show();

        //初始化商品信息
        initCommodity();

        recyclerView = (RecyclerView) view.findViewById(R.id.home_fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initCommodity() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.LoadingCommodity)
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("......请求调用失败");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.loadFailed();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jsonToEntity(json);
                            mDialog.loadSuccess();
                        }
                    });
                }
            }
        });
    }

    private void jsonToEntity(String json) {

        Gson gson = new Gson();
        final CommodityJs commodityJs = gson.fromJson(json, new TypeToken<CommodityJs>() {
        }.getType());

        commodityList = commodityJs.getData();

        adapter = new CommodityAdapter(commodityList);
        adapter.setOnItemClickListener(new CommodityAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                CommodityJs.DataBean commodityJs1 = commodityList.get(position);
                Intent intent = new Intent(getActivity(), CommodityShowActivity.class);
                L.d("+++++++++++++" + commodityJs1.getCname());
                intent.putExtra("name", commodityJs1.getCname());
                intent.putExtra("price", commodityJs1.getCprice());
                intent.putExtra("category", commodityJs1.getCategory());
                intent.putExtra("username", commodityJs1.getUser().getUname());
                intent.putExtra("userpun", commodityJs1.getUser().getUpnum());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), SortActivity.class);
        switch (view.getId()) {
            case R.id.phone_layout:
                intent.putExtra("category", "手机");
                startActivity(intent);
                break;
            case R.id.book_layout:
                intent.putExtra("category", "图书");
                startActivity(intent);
                break;
            case R.id.digital_layout:
                intent.putExtra("category", "数码");
                startActivity(intent);
                break;
            case R.id.clothing_layout:
                intent.putExtra("category", "服装鞋帽");
                startActivity(intent);
                break;
            case R.id.computer_layout:
                intent.putExtra("category", "电脑");
                startActivity(intent);
                break;
            case R.id.electric_layout:
                intent.putExtra("category", "电器");
                startActivity(intent);
                break;
            case R.id.office_layout:
                intent.putExtra("category", "办公用品");
                startActivity(intent);
                break;
            case R.id.other_layout:
                intent.putExtra("category", "其它");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

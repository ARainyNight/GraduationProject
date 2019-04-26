package com.edu.shg_android.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.entity.Commodity;
import com.edu.shg_android.json.CommodityJs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/4/10.
 * 描述:
 */
public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHoler> {

    private List<CommodityJs.DataBean> mCommodityList;
    private CommodityJs.DataBean dataBean;


    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commodity_item, parent, false);
        ViewHoler holer = new ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        dataBean = mCommodityList.get(position);

        holder.cimg.setImageResource(R.mipmap.ic_launcher);
        holder.cuname.setText(dataBean.getUser().getUname());
        holder.cprice.setText(dataBean.getCprice());
        holder.cname.setText(dataBean.getCname());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.cdate.setText(simpleDateFormat.format(new Date(dataBean.getCdate())));

    }

    @Override
    public int getItemCount() {
        return mCommodityList.size();
    }

    static class ViewHoler extends RecyclerView.ViewHolder {

        ImageView cimg;
        TextView cname;
        TextView cprice;
        TextView cuname;
        TextView cdate;

        public ViewHoler(View itemView) {
            super(itemView);
            cimg = (ImageView) itemView.findViewById(R.id.commodity_cimg);
            cname = (TextView) itemView.findViewById(R.id.commodity_cname);
            cprice = (TextView) itemView.findViewById(R.id.commodity_price);
            cuname = (TextView) itemView.findViewById(R.id.commodity_cuname);
            cdate = (TextView) itemView.findViewById(R.id.commodity_cdate);
        }
    }

    public CommodityAdapter(List<CommodityJs.DataBean> commodities) {
        mCommodityList = commodities;
    }
}

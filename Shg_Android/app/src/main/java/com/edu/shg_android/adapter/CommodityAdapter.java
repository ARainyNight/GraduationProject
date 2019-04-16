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

import java.util.List;

/**
 * Created by lin on 2019/4/10.
 * 描述:
 */
public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHoler> {

    private List<Commodity> mCommodityList;

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commodity_item,parent,false);
        ViewHoler holer = new ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
            Commodity commodity = mCommodityList.get(position);

            holder.cimg.setImageResource(commodity.getCimg());
            holder.cuname.setText(commodity.getCuname());
            holder.cprice.setText(commodity.getCprice());
            holder.cname.setText(commodity.getCname());
            holder.cdate.setText(commodity.getCdate());

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

    public CommodityAdapter(List<Commodity> commodities) {
        mCommodityList = commodities;
    }
}

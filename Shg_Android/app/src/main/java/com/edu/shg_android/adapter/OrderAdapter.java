package com.edu.shg_android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.json.OrderJs;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/7.
 * 描述:
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHoler> {

    private List<OrderJs.DataBean> mOrderList;
    private OrderJs.DataBean dataBean;
    private Context mContext;

    private OnItemClickListener mListener;


    public interface OnItemClickListener {

        public void onClick(View view, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        mContext = parent.getContext();
        ViewHoler holer = new ViewHoler(view,mListener);
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        dataBean = mOrderList.get(position);

        if (dataBean.getTcimg()==null){
            holder.img.setImageResource(R.mipmap.ic_launcher);
        }else {
            Picasso.with(mContext).load(StaticClass.PhotoLoading+dataBean.getTcimg()).into(holder.img);
        }
//        Picasso.with(mContext).load(StaticClass.PhotoLoading+dataBean.getTcimg()).into(holder.img);
        holder.commoname.setText(dataBean.getTcname());
        holder.price.setText(dataBean.getTcprice());
        holder.username.setText(dataBean.getSellername());

        String status_str = dataBean.getStatus();
        L.d("-------------------status"+status_str);
        switch (status_str){
            case "0":
                holder.status.setText("未发货");
                break;
            case "1":
                holder.status.setText("已发货");
                break;
            case "2":
                holder.status.setText("交易完成");
                break;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.date.setText(simpleDateFormat.format(new Date(dataBean.getTdate())));
        holder.buyname.setText(dataBean.getBuyname());
        holder.buyaddress.setText(dataBean.getBuyaddress());
        holder.buynum.setText(dataBean.getBuynum());
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView username;
        TextView status;
        ImageView img ;
        TextView commoname;
        TextView price;
        TextView date ;

        TextView buyname;
        TextView buynum ;
        TextView buyaddress;

        private OnItemClickListener mListener;

        public ViewHoler(View itemView,OnItemClickListener listener) {
            super(itemView);

            mListener = listener;
            username = (TextView)itemView.findViewById(R.id.order_sellerusername);
            status = (TextView)itemView.findViewById(R.id.order_status);
            img = (ImageView)itemView.findViewById(R.id.order_img);
            commoname=(TextView)itemView.findViewById(R.id.order_commoname);
            price =(TextView)itemView.findViewById(R.id.order_price);
            date = (TextView)itemView.findViewById(R.id.order_date);
            buyname =(TextView)itemView.findViewById(R.id.order_buyname);
            buynum =(TextView)itemView.findViewById(R.id.order_buynum);
            buyaddress=(TextView)itemView.findViewById(R.id.order_buyaddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener!=null){
                        mListener.onClick(view,getAdapterPosition());
                    }
                }
            });
        }
    }

    public OrderAdapter(List<OrderJs.DataBean> dataBeans){
        mOrderList = dataBeans;
    }
}

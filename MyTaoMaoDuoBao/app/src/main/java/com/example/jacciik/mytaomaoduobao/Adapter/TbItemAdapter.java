package com.example.jacciik.mytaomaoduobao.Adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacciik.mytaomaoduobao.Activity.AliSdkWebViewProxyActivity;
import com.example.jacciik.mytaomaoduobao.R;
//import com.bukailiu.taomaoduobao.activity.AliSdkWebViewProxyActivity;
import com.example.jacciik.mytaomaoduobao.Activity.TbItemTab;
import com.example.jacciik.mytaomaoduobao.Bean.NTbkItem;
import com.bumptech.glide.Glide;


import java.util.List;


/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class TbItemAdapter extends RecyclerView.Adapter<TbItemAdapter.TbItemViewHolder> {

    private List<NTbkItem> mTbkItems;
    TbItemTab tbItemTab;


    public TbItemAdapter(List<NTbkItem> tbkItems, TbItemTab tbkItemActivity) {
        mTbkItems = tbkItems;
       tbItemTab = tbkItemActivity;
    }


    static class TbItemViewHolder extends RecyclerView.ViewHolder {
        View tbkView;
        ImageView itemImage;
        TextView title;
        TextView volumn;
        TextView price;
        TextView beforeP;


        public TbItemViewHolder(View view) {
            super(view);
            tbkView = view;
            itemImage = (ImageView) view.findViewById(R.id.small_images);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            beforeP = (TextView) view.findViewById(R.id.beforeP);
            volumn = (TextView) view.findViewById(R.id.volume);

        }
    }

    @Override
    public TbItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tb_item, parent, false);
        return new TbItemViewHolder(view);
    }

    public void addData(int position, List<NTbkItem> tbkItem) {
        int j = 0;
        for (int i = position; i < (position + 20); i++) {
            mTbkItems.add(position, tbkItem.get(j));
            j++;
        }
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mTbkItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(final TbItemViewHolder holder, int position) {
        NTbkItem tbkItem = mTbkItems.get(position);
        String url = tbkItem.getPictUrl();
        final String num_iid = tbkItem.getNumIid();
        int volume = tbkItem.getVolume();
        String nick = tbkItem.getNick();
        Glide
                .with(tbItemTab)
                .load(url).crossFade()
                .into(holder.itemImage);

        holder.title.setText(tbkItem.getTitle());
        holder.volumn.setText("月销: " + volume + "笔" + "   " + nick);
        holder.price.setText("优惠价:￥" + tbkItem.getZkFinalPrice());
        holder.beforeP.setText("￥" + tbkItem.getReservePrice());
        holder.beforeP.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
       /* holder.itemImage.setOnClickListener((View view) -> {

        });*/

        holder.tbkView.setOnClickListener((View view) -> {
            Intent intent = new Intent(tbItemTab, AliSdkWebViewProxyActivity.class);
            intent.putExtra("num_iid", num_iid);
            tbItemTab.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mTbkItems.size();
    }

}

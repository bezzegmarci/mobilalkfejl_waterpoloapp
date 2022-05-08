package com.example.waterpolo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChampionshipItemAdapter extends RecyclerView.Adapter<ChampionshipItemAdapter.ViewHolder> implements Filterable{
    private ArrayList<ChampionshipItem> mChampionshipItemData;
    private ArrayList<ChampionshipItem> mChampionshipItemDataAll;
    private Context mContext;
    private int lastPosition = -1;
    Button b;

    ChampionshipItemAdapter(Context context, ArrayList<ChampionshipItem> itemsData) {
        this.mChampionshipItemData = itemsData;
        this.mChampionshipItemDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public ChampionshipItemAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(ChampionshipItemAdapter.ViewHolder holder, int position) {
        ChampionshipItem currentItem = mChampionshipItemData.get(position);

        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mChampionshipItemData.size();
    }


    /**
     * RecycleView filter
     * **/
    @Override
    public Filter getFilter() {
        return browsingFilter;
    }

    private Filter browsingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<ChampionshipItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mChampionshipItemDataAll.size();
                results.values = mChampionshipItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(ChampionshipItem item : mChampionshipItemDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mChampionshipItemData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mItemImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.itemInfo);
            mItemImage = itemView.findViewById(R.id.itemImage);

        }

        void bindTo(ChampionshipItem currentItem){
            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getInfo());

            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);

            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((ChampionshipsActivity)mContext).deleteItem(currentItem));
        }
    }

}
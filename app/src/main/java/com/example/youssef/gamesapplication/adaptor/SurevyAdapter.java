package com.example.youssef.gamesapplication.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youssef.gamesapplication.DataModel.Survey;
import com.example.youssef.gamesapplication.R;

import java.util.List;

public class SurevyAdapter extends RecyclerView.Adapter<SurevyAdapter.ViewHolder> {
    private Context mContext;
    private List<Survey> arrayList;





    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView userName, date, useruserReview;
        private RatingBar rate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            date = itemView.findViewById(R.id.date);
            useruserReview = itemView.findViewById(R.id.userReview);
            rate = itemView.findViewById(R.id.usrrateing);
        }
    }

    public void setList(List<Survey> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public SurevyAdapter(Context context, List<Survey> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_list_item, parent, false);
        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Survey survey = arrayList.get(position);
        Glide.with(mContext)
                .load(survey.getUserImage())
                .placeholder(R.drawable.user_placeholder)
                .into(holder.userImage);

        holder.userName.setText(survey.getUserName());
        holder.date.setText(survey.getCreatedDate());
        holder.rate.setRating(survey.getRate());
        holder.useruserReview.setText(survey.getReview());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
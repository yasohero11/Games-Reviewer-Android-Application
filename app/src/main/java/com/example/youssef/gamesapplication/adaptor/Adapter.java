package com.example.youssef.gamesapplication.adaptor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.bumptech.glide.Glide;
import com.example.youssef.gamesapplication.Activity.EditGameActivity;
import com.example.youssef.gamesapplication.Activity.SingleGame;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.DataModel.Survey;
import com.example.youssef.gamesapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context mContext;
    private List<Game> arrayList;
    SharedPreferences prefs ;
    String role;
    public static Game lastClickedGame;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textGameName, textGamePrice;
        private FloatingActionButton edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gameImage);
            textGameName = itemView.findViewById(R.id.gameName);
            textGamePrice = itemView.findViewById(R.id.gamePrice);
            edit = itemView.findViewById(R.id.editGame);
        }
    }

    public void setList(List<Game> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public Adapter(Context context, List<Game> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;
        prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        role = prefs.getString("role", "");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_infelator, parent, false);
        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Game game = arrayList.get(position);


        holder.itemView.setOnClickListener((view)->{
            lastClickedGame = game;
            Intent in = new Intent(mContext, SingleGame.class);
            mContext.startActivity(in);
        });
        holder.textGameName.setText(game.getName());
        holder.textGamePrice.setText(game.getPrice());
        Glide.with(mContext)
                .load(game.getLogo())
                .placeholder(R.drawable.game_placeholder)
                .into(holder.imageView);

        if(role.equals("admin")) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.edit.setOnClickListener(view -> {
                lastClickedGame = game;
                Intent in = new Intent(mContext, EditGameActivity.class);
                mContext.startActivity(in);
            });
        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
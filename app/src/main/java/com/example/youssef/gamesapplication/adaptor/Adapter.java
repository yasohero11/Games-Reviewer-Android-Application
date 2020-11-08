package com.example.youssef.gamesapplication.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youssef.gamesapplication.Games;
import com.example.youssef.gamesapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context mContext;
    private List<Games.Game> arrayList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textGameName, textGamePrice;
        private Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gameImage);
            textGameName = itemView.findViewById(R.id.gameName);
            textGamePrice = itemView.findViewById(R.id.gamePrice);
            edit = itemView.findViewById(R.id.editGame);
            delete = itemView.findViewById(R.id.deleteGame);
        }
    }

    public void setList(List<Games.Game> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public Adapter(Context context, List<Games.Game> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;
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
        Games.Game game = arrayList.get(position);
        holder.textGameName.setText(game.getName());
        holder.textGamePrice.setText(Double.toString(game.getPrice()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do your DB edit magic here
                Toast.makeText(mContext, "edit postion" + position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do your DB delete magic here
                Toast.makeText(mContext, "delete " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
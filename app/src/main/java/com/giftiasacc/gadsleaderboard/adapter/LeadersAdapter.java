package com.giftiasacc.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.giftiasacc.gadsleaderboard.R;
import com.giftiasacc.gadsleaderboard.model.LearningLeader;

import java.util.List;

public class LeadersAdapter extends RecyclerView.Adapter<LeadersAdapter.LeadersHolder> {
    private List<LearningLeader> leaders;
    private Context context;
    public LeadersAdapter(Context context, List<LearningLeader> leaders){
        this.leaders =leaders;
        this.context = context;
    }
    @NonNull
    @Override
    public LeadersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.learning_leader_item,parent,false);
        return new LeadersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersHolder holder, int position) {
        LearningLeader leader = leaders.get(position);
        holder.name.setText(leader.getName());
        holder.hours.setText(leader.getHours()+" leaning hours, ");
        holder.country.setText(leader.getCountry());
        Glide.with(context)
                .load(leader.getBadgeUrl())
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class LeadersHolder  extends RecyclerView.ViewHolder{
        TextView name,hours,country;
        ImageView badge;
        public LeadersHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.leader_name);
            hours  = itemView.findViewById(R.id.leader_hours);
            country  = itemView.findViewById(R.id.leader_country);
            badge  = itemView.findViewById(R.id.leader_badge);
        }
    }
}

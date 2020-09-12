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
import com.giftiasacc.gadsleaderboard.model.SkillIQLeader;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillsHolder> {
    private List<SkillIQLeader> leaders;
    private Context context;
    public SkillsAdapter(Context context, List<SkillIQLeader> leaders){
        this.leaders =leaders;
        this.context = context;
    }
    @NonNull
    @Override
    public SkillsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.skill_iq_leader_item,parent,false);
        return new SkillsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsHolder holder, int position) {
        SkillIQLeader leader = leaders.get(position);
        holder.name.setText(leader.getName());
        holder.score.setText(leader.getScore()+" Skill IQ Score, ");
        holder.country.setText(leader.getCountry());
        Glide.with(context)
                .load(leader.getBadgeUrl())
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class SkillsHolder  extends RecyclerView.ViewHolder{
        TextView name,score,country;
        ImageView badge;
        public SkillsHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.skill_name);
            score  = itemView.findViewById(R.id.skill_score);
            country  = itemView.findViewById(R.id.skill_country);
            badge  = itemView.findViewById(R.id.skill_badge);
        }
    }
}

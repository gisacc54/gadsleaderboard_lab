package com.giftiasacc.gadsleaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.giftiasacc.gadsleaderboard.adapter.LeadersAdapter;
import com.giftiasacc.gadsleaderboard.adapter.SkillsAdapter;
import com.giftiasacc.gadsleaderboard.datasource.PostInterface;
import com.giftiasacc.gadsleaderboard.model.LearningLeader;
import com.giftiasacc.gadsleaderboard.model.SkillIQLeader;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SkillIQLeadersFragment extends Fragment {

    private RecyclerView skillList;
    public static SkillIQLeadersFragment getInstance(){
        return new SkillIQLeadersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        skillList = view.findViewById(R.id.list_skills);

        loadSkillLeaders();
        return view;
    }

    public void loadSkillLeaders() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface postInterface = retrofit.create(PostInterface.class);
        Call<List<SkillIQLeader>> call = postInterface.getSkillIQLeader();


        call.enqueue(new Callback<List<SkillIQLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeader>> call, Response<List<SkillIQLeader>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Fail to load Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<SkillIQLeader> skillIQLeaders = response.body();
                skillIQLeaders.sort(Comparator.comparing(SkillIQLeader::getScore).reversed());

                SkillsAdapter skillsAdapter = new SkillsAdapter(getContext(),skillIQLeaders);
                skillList.setAdapter(skillsAdapter);
                skillList.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<SkillIQLeader>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
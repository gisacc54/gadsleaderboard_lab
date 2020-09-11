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
import com.giftiasacc.gadsleaderboard.datasource.PostInterface;
import com.giftiasacc.gadsleaderboard.model.LearningLeader;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LearningLeadersFragment extends Fragment {

    private RecyclerView leadersList;
    public static LearningLeadersFragment getInstance(){
        return new LearningLeadersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        leadersList = view.findViewById(R.id.list_leaders);

        loadLearningLeaders();
        return view;
    }



    private void loadLearningLeaders(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface postInterface = retrofit.create(PostInterface.class);
        Call<List<LearningLeader>> call = postInterface.getLearningLeader();


        call.enqueue(new Callback<List<LearningLeader>>() {
            @Override
            public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Fail to load Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<LearningLeader> learningLeaders = response.body();
                learningLeaders.sort(Comparator.comparing(LearningLeader::getHours).reversed());

                LeadersAdapter leadersAdapter = new LeadersAdapter(getContext(),learningLeaders);
                leadersList.setAdapter(leadersAdapter);
                leadersList.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
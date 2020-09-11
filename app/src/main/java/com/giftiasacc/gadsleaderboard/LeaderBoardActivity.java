package com.giftiasacc.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.giftiasacc.gadsleaderboard.adapter.ViewPagerAddapter;
import com.giftiasacc.gadsleaderboard.datasource.PostInterface;
import com.giftiasacc.gadsleaderboard.model.LearningLeader;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // set tabs
        getTabs();

        //UI
        submitBtn = findViewById(R.id.submit_activity_btn);



        //listeners
        submitBtn.setOnClickListener(this);
    }

    public void getTabs(){
        final ViewPagerAddapter viewPagerAddapter = new ViewPagerAddapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPagerAddapter.addFragment(LearningLeadersFragment.getInstance(),"Learning Leaders");
                viewPagerAddapter.addFragment(SkillIQLeadersFragment.getInstance(),"Skill IQ Leaders");

                viewPager.setAdapter(viewPagerAddapter);

                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == submitBtn){
            startActivity(new Intent(this,ProjectSubmissionActivity.class));
        }
    }
}
package com.giftiasacc.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.giftiasacc.gadsleaderboard.datasource.PostInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmissionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button submitBtn;
    private ImageView backBtn;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText githubProjectLinkInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        //UI
        submitBtn = findViewById(R.id.form_submit_btn);
        backBtn = findViewById(R.id.back_btn);
        firstNameInput = findViewById(R.id.first_name);
        lastNameInput = findViewById(R.id.last_name);
        emailInput = findViewById(R.id.email);
        githubProjectLinkInput = findViewById(R.id.github_project_link);

        // set listeners
        submitBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == submitBtn){
            submitProject();
        }
        else if (view == backBtn){
            onBackPressed();
        }
    }

    private void submitProject(){
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String githubProjectUrl = githubProjectLinkInput.getText().toString();
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || githubProjectUrl.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://docs.google.com/forms/d/e/")
                    .build();
            PostInterface postInterface = retrofit.create(PostInterface.class);

            postInterface.submitProject(firstName,lastName,email,githubProjectUrl).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful())
                        Toast.makeText(ProjectSubmissionActivity.this, "Fail to submit"+response.code(), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ProjectSubmissionActivity.this, "Submit message: "+response.toString()+" "+githubProjectUrl, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ProjectSubmissionActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
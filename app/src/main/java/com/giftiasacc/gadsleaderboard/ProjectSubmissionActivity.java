package com.giftiasacc.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
            createConfirmAlertDialog();
        }
        else if (view == backBtn){
            onBackPressed();
        }
    }

    private void validateData(){
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String githubProjectUrl = githubProjectLinkInput.getText().toString();
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || githubProjectUrl.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            submitProject(firstName,lastName,email,githubProjectUrl);
        }
    }
    private void submitProject(String firstName,String lastName,String email,String githubProjectUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();
        PostInterface postInterface = retrofit.create(PostInterface.class);

        postInterface.submitProject(firstName,lastName,email,githubProjectUrl).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    createSuccessAlertDialog();
                else
                    createErrorAlertDialog();
                System.out.println(response.toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                createErrorAlertDialog();
            }
        });
    }

    public void createConfirmAlertDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        View layout = getLayoutInflater().inflate(R.layout.confirm_alert_dialog,null);
        ImageView close = layout.findViewById(R.id.confirm_close);
        Button okBtn = layout.findViewById(R.id.confirm_btn);
        builder.setView(layout);
        AlertDialog dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                validateData();
            }
        });
    }

    public void createSuccessAlertDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        View layout = getLayoutInflater().inflate(R.layout.success_alert_dialog,null);
        builder.setView(layout);
        AlertDialog dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void createErrorAlertDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        View layout = getLayoutInflater().inflate(R.layout.error_alert_dialog,null);
        builder.setView(layout);
        AlertDialog dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
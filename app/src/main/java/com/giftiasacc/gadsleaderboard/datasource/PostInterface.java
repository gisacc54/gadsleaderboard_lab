package com.giftiasacc.gadsleaderboard.datasource;


import com.giftiasacc.gadsleaderboard.model.LearningLeader;
import com.giftiasacc.gadsleaderboard.model.Project;
import com.giftiasacc.gadsleaderboard.model.SkillIQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostInterface {
    @GET("hours")
    Call<List<LearningLeader>> getLearningLeader();
    @GET("skilliq")
    Call<List<SkillIQLeader>> getSkillIQLeader();
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submitProject(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String githubProjectUrl
    );
}

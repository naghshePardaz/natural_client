package com.example.frw;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.ProjectsList;
import com.example.frw.request.RetrofitClient;
import com.example.frw.request.SharedPref;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProfileActivity extends AppCompatActivity {

    private List<String> sLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Lookup recycler view in activity layout
        RecyclerView rvProjects = findViewById(R.id.rvProjects);

        // Read token from SharedPref and execute new Request/Response
        String mToken = SharedPref.getPreferences(getApplicationContext())
                .getString("token", null);
        getProjectList(mToken);


        // Retrieve Projects data from response

        // Create adapter and passing project data
        ProjectAdaptor adapter = new ProjectAdaptor(sLists);

        // Attach adaptor to recycler view to populate data
        rvProjects.setAdapter(adapter);
        // set layout manager to position item
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getProjectList(String token) {
        RetrofitClient.createApi().proj(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ProjectResponse pr) {
                        decodeProjectList(pr);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void decodeProjectList(ProjectResponse pr) {
        List<ProjectsList> pLists = pr.getProjecList();
        sLists = new ArrayList<>();

        for (ProjectsList pList : pLists) {
            sLists.add(pList.getProjectName());
        }
    }
}

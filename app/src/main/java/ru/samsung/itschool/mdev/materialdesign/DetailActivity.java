package ru.samsung.itschool.mdev.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Установка Collapsing Toolbar на экран
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        int postion = getIntent().getIntExtra(POSITION, 0);
        Resources resources = getResources();
        String[] projects = resources.getStringArray(R.array.projects);
        collapsingToolbar.setTitle(projects[postion % projects.length]);

        String[] projectDetails = resources.getStringArray(R.array.project_details);
        TextView projectDetail = findViewById(R.id.project_detail);
        projectDetail.setText(projectDetails[postion % projectDetails.length]);

        String[] projectLocations = resources.getStringArray(R.array.project_locations);
        TextView projectLocation = findViewById(R.id.project_location);
        projectLocation.setText(projectLocations[postion % projectLocations.length]);

        TypedArray projectPictures = resources.obtainTypedArray(R.array.projects_picture);
        ImageView projectPicutre = findViewById(R.id.image);
        projectPicutre.setImageDrawable(projectPictures.getDrawable(postion % projectPictures.length()));

        projectPictures.recycle();
    }
}
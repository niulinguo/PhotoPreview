package com.lingo.photo_preview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoPreviewActivity extends AppCompatActivity {

    private PhotoView mPhotoView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo_preview);

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }

        if (TextUtils.isEmpty(url)) {
            finish();
            return;
        }

        mPhotoView = findViewById(R.id.photo_view);

        Glide
                .with(this)
                .load(url)
                .into(mPhotoView);

        mPhotoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {

                ActivityCompat.finishAfterTransition(PhotoPreviewActivity.this);

            }
        });
    }
}

package com.lingo.photo_preview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

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

        mPhotoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {

                ActivityCompat.finishAfterTransition(PhotoPreviewActivity.this);

            }
        });

        findViewById(R.id.cl_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.finishAfterTransition(PhotoPreviewActivity.this);

            }
        });

        loadUrl();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra("url")) {
            url = intent.getStringExtra("url");
        }

        if (TextUtils.isEmpty(url)) {
            finish();
            return;
        }

        loadUrl();
    }

    private void loadUrl() {

        Glide
                .with(this)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        mPhotoView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);

                        Toast.makeText(PhotoPreviewActivity.this, "图片已损坏", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}

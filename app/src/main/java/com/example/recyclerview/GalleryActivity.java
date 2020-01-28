package com.example.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for coming intents.");
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name") && getIntent().hasExtra("image_desc")){
            Log.d(TAG, "getIncomingIntent: found intent extra");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName= getIntent().getStringExtra("image_name");
            String imageDesc= getIntent().getStringExtra("image_desc");

            setImage(imageUrl, imageName, imageDesc);
        }
    }

    private void setImage(String imageUrl, String imageName, String imageDesc){
        Log.d(TAG, "setImage: setting image and name to windgets");

        TextView name = findViewById(R.id.image_name);
        TextView namedesc = findViewById(R.id.image_desc);
        name.setText(imageName);
        namedesc.setText(imageDesc);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }
}

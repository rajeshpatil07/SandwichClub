package com.capgemini.sandwichclub;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    private TextView mTxtDescription;
    private ImageView mSandwichImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        String fileName = bundle.getString("imagename");
        setTitle(name);
        mTxtDescription = (TextView) findViewById(R.id.description);
        mTxtDescription.setText(description);
        mSandwichImage = (ImageView) findViewById(R.id.sandwich_img);
        AssetManager assetManager = getAssets();

        try {

            InputStream inputStream = assetManager.open(fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mSandwichImage.setImageBitmap(bitmap);
        } catch (IOException ex)

        {
            ex.printStackTrace();
        }

    }

}

package com.example.trial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchPicture extends AppCompatActivity {
    private TextView Result;
    private TextView Descrip;
    private TextView Date;
    private TextView Copyright;
    private ImageView picture;


    String mTitles;
    String mDescript;
    String mDate;
    ArrayList<String> mUrl=new ArrayList<>();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_picture);
        Result=findViewById(R.id.Result);
        Date=findViewById(R.id.Date);
        Descrip=findViewById(R.id.Description);
        Copyright=findViewById(R.id.Copyright);
        picture=findViewById(R.id.imageView);


        mTitles=getIntent().getStringExtra("Picture Title");
        mDescript=getIntent().getStringExtra("Picture Descript");
        mDate=getIntent().getStringExtra("Date");
        url=getIntent().getStringExtra("Picture url");


        Result.setText(mTitles);
        Date.setText(mDate);
        url=url.replace("http","https");
        Copyright.setText(url);

        Glide.with(SearchPicture.this).load(url).into(picture);
        //Descrip.setText(mDescript);





    }
}

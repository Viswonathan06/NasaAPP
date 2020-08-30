package com.example.trial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trial.Model.Data;
import com.example.trial.Retrofit.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPictureDetails extends AppCompatActivity {
    Data data;

    private TextView Result;
    private TextView Descrip;
    private TextView Date;
    private TextView Copyright;
    private TextView vid_link;


    private Data one;
    String date;
    String api_key;
    private ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date=getIntent().getExtras().getString("Date");
        setContentView(R.layout.activity_get_picture_details);
        Result=findViewById(R.id.Result);
        Date=findViewById(R.id.Date);
        Descrip=findViewById(R.id.Description);
        Copyright=findViewById(R.id.Copyright);
        vid_link=findViewById(R.id.vid_link);


        api_key="sycXRheJiirJrAjUYSZfm9q1t7r89bIjNamgqpmr";
        picture=findViewById(R.id.imageView);
        picture.setClickable(false);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        Call<Data> call=jsonPlaceHolderApi.getData(api_key,date);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (!response.isSuccessful()) {
                    Result.setText("Code :" + response.code());
                    return;
                }
                else {
                    data= response.body();


                    //Toast.makeText(GetPictureDetails.this,data.getUrl() , Toast.LENGTH_SHORT).show();
                    Result.setText(data.getTitle());
                    Descrip.setText(data.getExplanation());
                    Copyright.setText("      Copyright -"+data.getCopyright());
                    Date.setText("Date  ="+data.getDate());
                    if(data.getMedia_type().equals("video")) {
                        vid_link.setText(data.getUrl());
                        picture.setImageResource(R.drawable.youtube_play_button_transparent_png_15);
                        picture.setClickable(true);
                        picture.setScaleType(ImageView.ScaleType.FIT_XY);
                        picture.setAdjustViewBounds(true);

                    }
                    else{
                        Glide.with(GetPictureDetails.this).load(data.getUrl()).into(picture);

                    }
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Result.setText(t.getMessage());

            }
        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(Intent.ACTION_VIEW);

                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse(data.getUrl()));

                startActivity(intent);
            }
        });


    }
}

package com.example.trial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trial.Model.Data;

import com.example.trial.Retrofit.JsonPlaceHolderApi;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Context context;
    Button showPic;
    Button Search;
    String date;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showPic=findViewById(R.id.showPic);
        Search=findViewById(R.id.Search);

        mDisplayDate=findViewById(R.id.selectDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.i("Message!" "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = year + "-" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };
        showPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GetPictureDetails.class);
                intent.putExtra("Date",date);
                startActivity(intent);
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchDetails.class);
                startActivity(intent);

            }
        });


        /* call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (!response.isSuccessful()) {
                    Result.setText("Code :" + response.code());
                    return;
                }
            }
                DataList datalist= (DataList) response.body();
                List datas=datalist.getDatas();

                String content="";
               // content+= "Date :"+ datas.get(1).getDate() +"/n";
                //content+="Title :"+datas.get(1).getTitle() +"/n";
                //content+="Explaination :"+datas.get(1).getExplanation() +"/n";
                //content+="Url :"+datas.get(1).getUrl() +"/n /n";
                //Result.append(content);
               // for(Data data:datas){



                //}


            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Result.setText(t.getMessage());
            }
        });

         */

    }
}

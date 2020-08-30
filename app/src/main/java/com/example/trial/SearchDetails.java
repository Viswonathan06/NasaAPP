package com.example.trial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.TextView;

import com.example.trial.Model.Collection;
import com.example.trial.Model.Data;
import com.example.trial.Model.Items;
import com.example.trial.Model.Links;
import com.example.trial.Model.Root;
import com.example.trial.Model.SearchData;
import com.example.trial.Retrofit.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDetails extends AppCompatActivity {
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView RESULT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        RESULT=findViewById(R.id.textView2);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Root> call=jsonPlaceHolderApi.getRoot();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    RESULT.setText("Code :" + response.code());
                    return;
                }
                else {
                    Root result = response.body();
                    Collection collection=result.getCollection();
                    List<Items> Item=collection.getItems();
                    Items one=Item.get(1);
                    String s=one.getHref();
                    RESULT.setText(s);

                    List<SearchData> Datas=one.getData();
                    int m=Datas.size();
                    RESULT.setText(Datas.get(0).getTitle());

                    //RESULT.setText(Items.get(1).getData().get(0).getDescription());


                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                RESULT.setText(t.getMessage());

            }
        });

    }
}

package com.example.trial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trial.Model.Collection;
import com.example.trial.Model.Data;
import com.example.trial.Model.Items;
import com.example.trial.Model.Links;
import com.example.trial.Model.Root;
import com.example.trial.Model.SearchData;
import com.example.trial.Retrofit.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDetails extends AppCompatActivity {
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView RESULT;
    EditText SearchBox;
    Button Search;
    String query;


    private ArrayList<String> mTitles=new ArrayList<>();
    private ArrayList<String> mHrefs=new ArrayList<>();
    private ArrayList<String> mDates=new ArrayList<>();
    private ArrayList<String> mNasaID=new ArrayList<>();
    private ArrayList<String> mDescript=new ArrayList<>();



    String m="";
    private void initRecyclerView(){
        Log.d("initRecyclerView","Recycler VIew Init'd");
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        RecycleViewAdapter adapter=new RecycleViewAdapter(SearchDetails.this,mTitles,mHrefs,mDates,mNasaID,mDescript);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchDetails.this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        SearchBox=findViewById(R.id.searchbox);
        Search=findViewById(R.id.Search);
        RESULT=findViewById(R.id.result);
        RESULT.setText("");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        SearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                RESULT.setText("");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RESULT.setText("");
                mDates.clear();
                mHrefs.clear();
                mTitles.clear();
                initRecyclerView();
                query=SearchBox.getText().toString();
                query.replace(" ","%");
                Call<Root> call=jsonPlaceHolderApi.getRoot(query);
                call.enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        if (!response.isSuccessful()) {
                            if(!SearchBox.getText().toString().isEmpty()) {
                                RESULT.setText("Code :" + response.code());
                            }
                            return;
                        }
                        else {
                            Root result = response.body();
                            Collection collection=result.getCollection();
                            List<Items> Item=collection.getItems();

                            for(Items items:Item){
                                mTitles.add(items.getData().get(0).getTitle());
                                mDates.add(items.getData().get(0).getDate_created());
                                mHrefs.add(items.getHref());
                                mNasaID.add(items.getData().get(0).getNasa_id());
                                mDescript.add(items.getData().get(0).getDescription());

                            }
                            initRecyclerView();
                            if(mTitles.isEmpty()){
                                Toast.makeText(SearchDetails.this, "No search result for your query", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }



                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        RESULT.setText(t.getMessage());

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                RESULT.setText("");

            }
        });
       /* Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        */



    }
}

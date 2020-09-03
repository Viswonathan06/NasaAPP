package com.example.trial;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.trial.Model.ClickSearch.Root;
import com.example.trial.Retrofit.JsonPlaceHolderApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<String> mTitles=new ArrayList<>();
    private ArrayList<String> mHrefs=new ArrayList<>();
    private ArrayList<String> mDates=new ArrayList<>();
    private ArrayList<String> mNasaID=new ArrayList<>();
    private ArrayList<String> mDescript=new ArrayList<>();


    Root root;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private Context mContext;

    public RecycleViewAdapter(Context mContext, ArrayList<String> mTitles, ArrayList<String> mHrefs, ArrayList<String> mDates,ArrayList<String> mNasaID,ArrayList<String> mDescript) {
        this.mTitles = mTitles;
        this.mHrefs = mHrefs;
        this.mDates = mDates;
        this.mNasaID=mNasaID;
        this.mContext = mContext;
        this.mDescript = mDescript;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
         holder.title.setText(mTitles.get(position));
        holder.href.setText(mNasaID.get(position));
        holder.Date.setText(mDates.get(position));

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Root> call=jsonPlaceHolderApi.getRoot("asset",mNasaID.get(position));
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mContext, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Root result=response.body();

                    String m=result.getCollection().getItems().get(0).getHref();
                    m=m.replace("http","https");
                    Glide.with(mContext)
                            .load(m)
                            .apply(new RequestOptions().override(400,400))
                            .apply(new RequestOptions().circleCrop())
                            .apply(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .into(holder.imageView);

                    holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: clicked on"+mTitles.get(position));
                            Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(mContext,SearchPicture.class);
                            intent.putExtra("Picture Title",mTitles.get(position));
                            intent.putExtra("Picture Descript",mDescript.get(position));
                            intent.putExtra("Date",mDates.get(position));

                            String m=result.getCollection().getItems().get(0).getHref();
                            intent.putExtra("Picture url",m);
                            mContext.startActivity(intent);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(mContext, "Code: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




        //Glide.with(mContext).load().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView Date;
        TextView href;
        ImageView imageView;
        LinearLayout parentlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title);
            Date=itemView.findViewById(R.id.date);
            href=itemView.findViewById(R.id.href);
            parentlayout=itemView.findViewById(R.id.parent_layout);
            imageView=itemView.findViewById(R.id.imageView);


        }
    }
}

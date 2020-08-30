package com.example.trial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<String> mTitles=new ArrayList<>();
    private ArrayList<String> mHrefs=new ArrayList<>();
    private ArrayList<String> mDates=new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapter(Context mContext, ArrayList<String> mTitles, ArrayList<String> mHrefs, ArrayList<String> mDates) {
        this.mTitles = mTitles;
        this.mHrefs = mHrefs;
        this.mDates = mDates;
        this.mContext = mContext;
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
        holder.href.setText(mHrefs.get(position));
        holder.Date.setText(mDates.get(position));

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d(TAG, "onClick: clicked on"+mTitles.get(position));
                 Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView Date;
        TextView href;
        LinearLayout parentlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title);
            Date=itemView.findViewById(R.id.date);
            href=itemView.findViewById(R.id.href);
            parentlayout=itemView.findViewById(R.id.parent_layout);


        }
    }
}

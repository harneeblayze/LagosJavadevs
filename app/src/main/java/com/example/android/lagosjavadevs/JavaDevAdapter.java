package com.example.android.lagosjavadevs;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.lagosjavadevs.dataclasses.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HARNY on 8/30/2017.
 */

public class JavaDevAdapter extends RecyclerView.Adapter<JavaDevAdapter.JavaDevAdapterViewHolder> {
    Context myContext;
    List<Item> allUsers;
    @Override
    public JavaDevAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_user_view,parent,false);
        return new JavaDevAdapterViewHolder(view);
    }
    public JavaDevAdapter(Context context, List<Item> users){
        myContext = context;
        allUsers = users;
    }
    @Override
    public void onBindViewHolder(final JavaDevAdapterViewHolder holder, final int position) {
        String picUrl = allUsers.get(position).getAvatarUrl();
        String username = allUsers.get(position).getLogin();
        holder.usernameTextView.setText(username);
        Picasso.with(myContext).load(picUrl)
                .placeholder(R.color.colorPrimary)
                .fit().into(holder.userImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("intent_user",(Parcelable) allUsers.get(position));
                s
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public class JavaDevAdapterViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView userImageView;
        private TextView usernameTextView;
        public JavaDevAdapterViewHolder(View itemView) {
            super(itemView);
            userImageView =(CircleImageView) itemView.findViewById(R.id.user_image_view);
            usernameTextView = (TextView) itemView.findViewById(R.id.user_name_text_view);

        }
    }
}

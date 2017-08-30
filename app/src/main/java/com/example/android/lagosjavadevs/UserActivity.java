package com.example.android.lagosjavadevs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lagosjavadevs.dataclasses.Item;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {
    Item user;

    ImageView userImageView;
    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userImageView = (ImageView) findViewById(R.id.java_dev_toolbar_image_view);
        linkTextView = (TextView) findViewById(R.id.github_repo_link);
        user = new Item();
        String username = "";
        String pageLink = "";
        Intent receivedIntent = getIntent();
        if(receivedIntent!=null) {
            user = receivedIntent.getParcelableExtra("intent_user");
            String link = user.getHtmlUrl();
            String picUrl = user.getAvatarUrl();
            username = user.getLogin();
            pageLink = link;
            getSupportActionBar().setTitle(username);
            linkTextView.setText(link);
            Picasso.with(getApplicationContext()).load(picUrl)
                    .placeholder(R.color.colorPrimary)
                    .fit().into(userImageView);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final String finalUsername = username;
        final String finalPageLink = pageLink;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = String.format(" Check out this awesome developer %s, %s", finalUsername, finalPageLink);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share using"));

            }
        });
    }
}

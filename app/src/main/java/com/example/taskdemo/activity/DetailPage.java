package com.example.taskdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.taskdemo.R;

import java.util.ArrayList;

public class DetailPage extends AppCompatActivity {
TextView lastupdatetxt,titletxt,desctxt,fulldesctxt,contentTxt,seemoreTxt,creatorTxt,categoryTxt;
ImageView imageUrl;
String imgUrl;
ArrayList<String> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        lastupdatetxt = findViewById(R.id.lastupdateTxt);
        titletxt = findViewById(R.id.titleTxt);
        desctxt = findViewById(R.id.descTxt);
        fulldesctxt=findViewById(R.id.fulldescTxt);
        contentTxt=findViewById(R.id.contentTxt);
        seemoreTxt=(TextView) findViewById(R.id.seemoreUrlTxt);
        creatorTxt=findViewById(R.id.creator);
        categoryTxt=findViewById(R.id.categoryTxt);
        imageUrl=findViewById(R.id.imgsrc);
        Bundle bundle = getIntent().getExtras();
        String creatorStr = bundle.getString("creator");
        String seemoreStr = bundle.getString("seemoreurl");

        String mess = getResources().getString(R.string.seemore);
        String medesss = getString(R.string.desc);

       String updateee=  "<b>Updateddd : </b> <u><i>H?</i></u>";

        categories = bundle.getStringArrayList("category");
        lastupdatetxt.setText(bundle.getString("userId"));
        titletxt.setText(bundle.getString("title"));
        desctxt.setText(bundle.getString("desc"));
        fulldesctxt.setText(bundle.getString("fulldesc"));
        contentTxt.setText(bundle.getString("content"));
        seemoreTxt.setText(seemoreStr);
        creatorTxt.setText(creatorStr);
        imgUrl = bundle.getString("imgurl");
        Glide.with(getApplicationContext())
                .applyDefaultRequestOptions(new RequestOptions()
                        .placeholder(R.drawable.download)
                        .error(R.drawable.download))
                .load(imgUrl)
                .thumbnail(0.5f)
                .into(imageUrl);
        StringBuilder builder = new StringBuilder();
        for (String s: categories) {
            builder.append(s);
            builder.append(", ");
        }

        categoryTxt.setText(builder.toString().trim());

    }
}
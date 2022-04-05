package com.example.taskdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.taskdemo.R;
import com.example.taskdemo.adapters.NewsItemAdapter;
import com.example.taskdemo.models.NewsModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsArticalsActivity extends AppCompatActivity {
   // ArrayList<NewsModel> postList=new ArrayList<>();
    String url="https://newsdata.io/api/1/news?apikey=pub_6218349a64f67debfb1ce618ee8c3a39d488";
//    PosterAdapter adapter;
    ArrayList<NewsModel> newsList = new ArrayList<NewsModel>();
    ArrayList<NewsModel> filterList=new ArrayList<>();
    RecyclerView recyclerView;
    NewsItemAdapter newsItemAdapter;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_articals);
        search=findViewById(R.id.search);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        getData();
        getNewsData();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filterList.clear();
                if(s.toString().isEmpty()){
                    recyclerView.setAdapter(new NewsItemAdapter(getApplicationContext(),newsList));
                    newsItemAdapter.notifyDataSetChanged();

                }
                else{
                    Filter(s.toString());
                }
            }
        });
    }

    private void Filter(String text) {
        text = text.toLowerCase();

        for(NewsModel post:newsList){
            if(post.getTitle().toLowerCase().contains(text)){
                filterList.add(post);
            }
        }
        recyclerView.setAdapter(new NewsItemAdapter(getApplicationContext(),filterList));
        newsItemAdapter.notifyDataSetChanged();
    }
    private void getNewsData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        String urls="ss";

        progressDialog.show();
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int p=0; p<jsonArray.length(); p++){
                        progressDialog.dismiss();
                        JSONObject jsonObject = jsonArray.getJSONObject(p);
                        NewsModel rvdata = gson.fromJson(String.valueOf(jsonObject), NewsModel.class);
                        newsList.add(rvdata);

                    }
                    newsItemAdapter = new NewsItemAdapter(getApplicationContext(), newsList);
                    recyclerView.setAdapter(newsItemAdapter);
                    newsItemAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                JSONArray jsonArray = response.get (Integer.parseInt("results"));
//                for(int i=0;i<=jsonArray.length();i++){
//                    try {
//                        JSONObject jsonObject =jsonArray.getJSONObject(i);
//                        postList.add(new PostModel(
//                                jsonObject.getInt("userId"),
//                                jsonObject.getString("title"),
//                                jsonObject.getString("body")
//                        ));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                }
//                adapter=new PosterAdapter(getApplicationContext(),postList);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(NewsArticalsActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(NewsArticalsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

//    private void getData() {
//        String myUrl = "https://jsonplaceholder.typicode.com/todos/1";
//        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
//                response -> {
//                    try{
//                        Log.d("responseee",response.toString());
//                        //Create a JSON object containing information from the API.
//                        JSONObject myJsonObject = new JSONObject(response);
////                        t1.setText(myJsonObject.getString("userId"));
////                        t2.setText(myJsonObject.getString("title"));
////                        t3.setText(myJsonObject.getString("completed"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Log.d("errorExpeceee",e.toString());
//                    }
//                },
//                volleyError -> Toast.makeText(NewsArticalsActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
//        );
//    }
}
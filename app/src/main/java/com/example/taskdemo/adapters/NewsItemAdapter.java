package com.example.taskdemo.adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.taskdemo.R;
import com.example.taskdemo.activity.DetailPage;
import com.example.taskdemo.models.NewsModel;

import java.util.ArrayList;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {
    ArrayList<NewsModel> postList;
    Context context;
    String img;
    public NewsItemAdapter(Context context, ArrayList<NewsModel> postList) {
        this.postList=postList;
        this.context=context;
    }

//    public void filterList(ArrayList<NewsModel> filterllist) {
//        // below line is to add our filtered
//        // list in our course array list.
//        postList = filterllist;
//        // below line is to notify our adapter
//        // as change in recycler view data.
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitems,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.lastupdate.setText("Last Update : "+postList.get(position).getPubDate().toString());
        holder.title.setText(postList.get(position).getTitle());
        holder.postdesc.setText(postList.get(position).getDescription());

        try{
             img = String.valueOf(postList.get(position).getImageUrl());
        if(img.isEmpty()||img == null){
            holder.imageUrl.setImageResource(R.drawable.download);
        }else{
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions()
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download))
                    .load(img)
                    .thumbnail(0.5f)
                    .into(holder.imageUrl)
          ;
        }}catch (Exception e){
            e.printStackTrace();
            holder.imageUrl.setImageResource(R.drawable.download);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailPage.class);



try{

    String lastupdate = postList.get(position).getPubDate();
    String title = String.valueOf(holder.title.getText());
    String desc = String.valueOf(holder.postdesc.getText());
    String content = postList.get(position).getContent();
    String fulldesc = postList.get(position).getFullDescription();
    String creator = String.valueOf(postList.get(position).getCreator());
    String imgurl = String.valueOf(postList.get(position).getImageUrl());
    ArrayList<String> category = (ArrayList<String>) postList.get(position).getCategory();
    String seemoreurl = postList.get(position).getLink();
    if(category.size()>0){
        i.putExtra("category",category);
    }else{
        Log.i("errorCategory",category.toString());
    }


    i.putExtra("userId",lastupdate);
    i.putExtra("title",title);
    i.putExtra("desc",desc);
    i.putExtra("content",content);
    i.putExtra("fulldesc",fulldesc);
    i.putExtra("creator",creator);
    i.putExtra("imgurl",postList.get(position).getImageUrl().toString());

    i.putExtra("seemoreurl",seemoreurl);

}catch (Exception e){

}
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


//                DetailPage fragment = DetailPage.getSupportFragmentManager().findFragmentById(R.id.nav_host_farmerfragment);
//                getActivity().getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
//                DetailPage fragment = (DetailPage) ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.container_frame_layout);
//                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();


//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new DetailPage();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, myFragment).addToBackStack(null).commit();

                //   v.getContext().startActivity(new Intent(v.getContext(), NewsArticalsActivity.class));

            }
        });

    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,postdesc,lastupdate;
        ImageView imageUrl;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.postTitle);
            postdesc=itemView.findViewById(R.id.postDesc);
            lastupdate=itemView.findViewById(R.id.lastupdate);
            imageUrl=itemView.findViewById(R.id.item_img);
            cardView = itemView.findViewById(R.id.card);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


}
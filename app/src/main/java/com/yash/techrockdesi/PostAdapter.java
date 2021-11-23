package com.yash.techrockdesi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final Context context;
    private final List<Item> items;

    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        Item item = items.get(position);
        holder.postTitle.setText(item.getTitle());

/**
 * Temporary solution for visible the IMAGES
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = p.matcher(item.getContent());
        List<String> tokens = new ArrayList<>();
        while (m.find()){
            String token = m.group(1);
            tokens.add(token);
        }
*/
        /**
           this is for access the html content
        // our content is now in document
        // so we fetch our content it
        */
        Document document = Jsoup.parse(item.getContent());

        holder.postDescription.setText(document.text());

        Elements elements = document.select("img");
        Log.d("CODE","Image - "+ elements.get(0).attr("src"));

        Log.d("TEXT",document.text());

        //Glide use for Image retrieve
        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);

        //clicked event in our post
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("url", item.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        ImageView postImage;
        TextView postTitle;
        TextView postDescription;

        public PostViewHolder(View itemView) {
            super(itemView);

            postImage = (ImageView) itemView.findViewById(R.id.postImage);
            postTitle = (TextView) itemView.findViewById(R.id.postTitle);
            postDescription = (TextView) itemView.findViewById(R.id.postDescription);
        }
    }
}

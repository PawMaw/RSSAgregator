package ru.pawmaw.rssagregator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pawmaw.rssagregator.Interface.NewsItemClickListener;
import ru.pawmaw.rssagregator.Model.RSSObject;
import ru.pawmaw.rssagregator.R;

class NewsFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView newsTextTitle, newsPublicationDate, newsTextContent; // Параметры лока RSS ленты
    private NewsItemClickListener newsItemClickListener;

    public void setNewsItemClickListener(NewsItemClickListener newsItemClickListener) {
        this.newsItemClickListener = newsItemClickListener;
    }

    public NewsFeedViewHolder(@NonNull View itemView) {
        super(itemView);

        newsTextTitle = itemView.findViewById(R.id.newsTextTitle);
        newsPublicationDate = itemView.findViewById(R.id.newsPublicationDate);
        newsTextContent = itemView.findViewById(R.id.newsTextContent);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        newsItemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        newsItemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedViewHolder> {

    private final RSSObject rssObject;
    private final Context mContext;
    private final LayoutInflater inflater;

    public NewsFeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_news_item, parent, false);
        return new NewsFeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        holder.newsTextTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.newsPublicationDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.newsTextContent.setText(rssObject.getItems().get(position).getContent());

        holder.setNewsItemClickListener((view, position1, isLongClick) -> {
            if (!isLongClick) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position1).getLink()));
                mContext.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}

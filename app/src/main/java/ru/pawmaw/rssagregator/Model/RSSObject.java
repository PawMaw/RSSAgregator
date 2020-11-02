package ru.pawmaw.rssagregator.Model;

import java.util.List;

public class RSSObject {
    public NewsFeed feed;
    public List<NewsItem> items;

    public RSSObject(NewsFeed feed, List<NewsItem> items) {
        this.feed = feed;
        this.items = items;
    }

    public List<NewsItem> getItems() {
        return items;
    }
}

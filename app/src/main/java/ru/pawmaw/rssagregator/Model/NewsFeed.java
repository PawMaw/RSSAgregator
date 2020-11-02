package ru.pawmaw.rssagregator.Model;

public class NewsFeed {
    public String url;
    public String title;
    public String link;
    public String description;

    public NewsFeed(String url, String title, String link, String description) {
        this.url = url;
        this.title = title;
        this.link = link;
        this.description = description;
    }
}

package ru.pawmaw.rssagregator.Model;

import android.text.Html;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class NewsItem {
    public String title;
    public String date_published;
    public String url;
    public String summary;

    public NewsItem(String title, String date_published, String url, String summary) {
        this.title = title;
        this.date_published = date_published;
        this.url = url;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Переводит дату из RSS в понятный для человека вид
     * @return date
     */
    public String getPubDate()
    {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(date_published, inputFormatter);
        return outputFormatter.format(date);
    }

    public String getLink() {
        return url;
    }

    /**
     * Удаляет Html спецсимволы из описания новости
     * @return String
     */
    public String getContent() {
        return Html.fromHtml(summary, 0).toString();
    }
}

package ru.pawmaw.rssagregator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import ru.pawmaw.rssagregator.Adapter.NewsFeedAdapter;
import ru.pawmaw.rssagregator.Common.HTTPDataHandler;
import ru.pawmaw.rssagregator.Model.RSSObject;

import static android.widget.ProgressBar.*;

public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    private final String lenta_RSS_Link = "https://lenta.ru/rss/news", medusa_RSS_Link = "https://meduza.io/rss2/en/all", tproger_RSS_Link = "https://tproger.ru/feed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void loadRSS(String rss_link) {
        @SuppressLint("StaticFieldLeak")

        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressBar progressBar = findViewById(R.id.progressBar);

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(VISIBLE);
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler httpDataHandler = new HTTPDataHandler();
                result = httpDataHandler.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                rssObject = new Gson().fromJson(s, RSSObject.class);
                NewsFeedAdapter adapter = new NewsFeedAdapter(rssObject, getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(INVISIBLE);
            }
        };
        loadRSSAsync.execute("https://feed2json.org/convert?url=" + rss_link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.lenta_news) {
            loadRSS(lenta_RSS_Link);
        } else if (item.getItemId() == R.id.medusa_news) {
            loadRSS(medusa_RSS_Link);
        } else if (item.getItemId() == R.id.tproger_news) {
            loadRSS(tproger_RSS_Link);
        }
        return true;
    }
}
package ru.pawmaw.rssagregator.Interface;

import android.view.View;

public interface NewsItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}

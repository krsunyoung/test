package com.sun.test.app.ui.Search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.sun.test.R;

public class SearchActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        textView = (TextView) findViewById(R.id.search_result_text);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent ) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {

        }
        String query = intent.getDataString();

        if (query == null) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }
        textView.setText(query);

        SearchRecentSuggestions suggestions =  new SearchRecentSuggestions(this, SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
        suggestions.saveRecentQuery(query, null);
    }


}

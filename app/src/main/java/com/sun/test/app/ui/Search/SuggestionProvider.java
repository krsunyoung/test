package com.sun.test.app.ui.Search;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by BIT on 2017-01-23.
 */

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    static final String TAG = SuggestionProvider.class.getSimpleName();
    public static final String AUTHORITY = SuggestionProvider.class.getName();
    public static final int MODE = DATABASE_MODE_QUERIES;
    private static final String[] COLUMNS = { "_id", SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA, SearchManager.SUGGEST_COLUMN_INTENT_ACTION,
            SearchManager.SUGGEST_COLUMN_SHORTCUT_ID };

    private String[] _list = { "진창훈", "김태희", "문근영", "유이", "임수정", "리지", "보람", "강민경", "지연", "은정", "소연", "순규", "탱구"};


    public SuggestionProvider()
    {
        setupSuggestions(AUTHORITY, MODE);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        String query = selectionArgs[0];
        if (query == null || query.length() == 0)
        {
            return null;
        }

        MatrixCursor cursor = new MatrixCursor(COLUMNS);

        try
        {
            // 내가 가진 목록 중에 검색어와 일치하는 글자가 있는것만 보여주기
            int kLen = _list.length;
            int kIdx = 0;
            for (int i = 0; i < kLen; i++)
            {
                if (_list[i].indexOf(query) >= 0)
                {
                    cursor.addRow(createRow(kIdx, query, _list[i], _list[i]));
                    kIdx++;
                }
            }
        } catch (Exception e)
        {
            Log.e(TAG, "Failed to lookup " + query, e);
        }
        return cursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        Log.w("###", "insert");
        return Uri.parse("http://google.com");
        // throw new UnsupportedOperationException();
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        Log.w("###", "delete");
        return 0;
        // throw new UnsupportedOperationException();
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        throw new UnsupportedOperationException();
    }


    private Object[] createRow(Integer id, String text1, String text2, String name)
    {
        return new Object[] { id, text2, text2, "android.intent.action.SEARCH", SearchManager.SUGGEST_NEVER_MAKE_SHORTCUT };
    }
}

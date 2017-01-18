package com.sun.test.app.core.service;


import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.test.android.network.JSONResult;
import com.sun.test.app.core.vo.UserVo;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by BIT on 2017-01-16. 01047467587
 */

public class UserService {
    public List<UserVo> UserList(){
        String url="http://192.168.1.14:8888/modeal-api/users/list";

        HttpRequest httpRequest = HttpRequest.get(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode=httpRequest.code();
        if(responseCode != HttpURLConnection.HTTP_OK){
            throw new RuntimeException("HTTP Response :" +responseCode);
        }


        JSONResultUserList jsonResultUserList = fromJSON(httpRequest, JSONResultUserList.class);

        return jsonResultUserList.getData();
    }

    private class JSONResultUserList extends JSONResult<List<UserVo>>{
    }

    protected  <V> V fromJSON(HttpRequest request, Class<V> target) {
        V v = null;
        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = request.bufferedReader();
            v = gson.fromJson(reader, target);

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return v;
    }
}

package com.sun.test.app.ui.User;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sun.test.R;
import com.sun.test.android.network.SafeAsyncTask;
import com.sun.test.app.core.service.UserService;
import com.sun.test.app.core.vo.UserVo;

import java.util.List;

/**
 * Created by BIT on 2017-01-17.
 */

public class UserListActivity extends ListActivity {
    private UserService userService = new UserService();
    private UserListArrayAdapter userListArrayAdapter = null;


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
         new UserListAsyncTask().execute(); //통신이 중간에 멈추지 않도록(실행)
   }

    private class UserListAsyncTask extends SafeAsyncTask<List<UserVo>> {
        @Override
        public List<UserVo> call() throws Exception {
            List<UserVo> list=  userService.UserList();
            return list;
        }

        @Override
        protected void onSuccess(List<UserVo> users) throws Exception { //데이터 처리 성공시
            //통신 결과 처리
            userListArrayAdapter.add(users);
            //  super.onSuccess(users);
        }

        @Override
        protected void onException(Exception e) throws RuntimeException { //데이터 처리 실패시 에러를 보여줌
            Log.d("=====!",""+e);
            throw new RuntimeException(e);
            //       super.onException(e);
        }
    }

}

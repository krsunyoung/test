package com.sun.test.app.ui.User;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sun.test.R;
import com.sun.test.android.network.SafeAsyncTask;
import com.sun.test.app.core.service.UserService;
import com.sun.test.app.core.vo.UserVo;

import java.util.List;

public class UsersListActivity extends ListActivity {

    private UserListArrayAdapter userListArrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        userListArrayAdapter = new UserListArrayAdapter(this);
        setListAdapter(userListArrayAdapter); //세팅 필수 없으면 에러로 간다 21,22번째를 쓰기위해 ListActivity를 상속

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        new UserListAsyncTask().execute();
    }

    public class UserListAsyncTask extends SafeAsyncTask<List<UserVo>> {


        @Override
        public List<UserVo> call() throws Exception {
            UserService userService = new UserService();
            List<UserVo> list = userService.UserList();

            return list;
        }

        @Override
        protected void onSuccess(List<UserVo> userVos) throws Exception {
            for (int i = 0; i < userVos.size(); i++) {
                Log.d((i + 1) + "번째 =>", "" + userVos.get(i));
            }
            userListArrayAdapter.add(userVos);

            super.onSuccess(userVos);
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            Log.d("====", "error :" + e);
            super.onException(e);
        }
    }
}

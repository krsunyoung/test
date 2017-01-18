package com.sun.test.app.ui.User;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sun.test.R;
import com.sun.test.app.core.vo.UserVo;

import java.util.List;

/**
 * Created by BIT on 2017-01-17.
 */

public class UserListArrayAdapter extends ArrayAdapter<UserVo> {
    private LayoutInflater layoutInflater;

    public UserListArrayAdapter(Context context) {
        super(context, R.layout.activity_users_list);
        layoutInflater = LayoutInflater.from(context);
        Log.d("------1","eeee");
        }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("1111","dfff");
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.row_user_list, parent, false);
        }

        //위치에 맡게 userVo에 받아온값 저장해서 뿌려주는 작업.
        UserVo userVo = getItem(position);

        TextView textView=(TextView)view.findViewById(R.id.textView);
        TextView textView1=(TextView)view.findViewById(R.id.textView2);
        TextView textView2=(TextView)view.findViewById(R.id.textView3);

        textView.setText(userVo.getId());
        textView1.setText(userVo.getLocation());
        textView2.setText(userVo.getBirth());

        return view;
       // return super.getView(position, convertView, parent);
    }

    public void add(List<UserVo> users){

        if(users==null || users.size()==0){
            return;
        }
        for (UserVo userVo : users){
            Log.d("test","123");
            add(userVo);
        }
    }
}

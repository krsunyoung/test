package com.sun.test.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sun.test.R;
import com.sun.test.app.ui.Search.SearchActivity;
import com.sun.test.app.ui.User.UsersListActivity;
import com.sun.test.app.ui.searchNext.ResultActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바 추가 (170123/상욱추가)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 타이틀에 있는 앱이름 숨기기

        // 네비게이션 추가(170123/상욱추가)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 버튼 클릭시 리스트로 받오올 장소로 이동
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });

        //serarch 버튼 클릭시 이동
        findViewById(R.id.search_imagebutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.search_result2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);

            }
        });
    }
    public void onSerchImageClicked(View view){
        onSearchRequested();
    }

    // 단순히 액션바부터 네비게이션 돋보기 추가하기 위해서는 여기서 부터~~~
    // 뒤로가기 버튼 누를때... (170123/상욱추가)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 액션바 맨 오른쪽 돋보기 추가 (170123/상욱추가)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    // 돋보기 눌렀을때 효과... (170123/상욱추가)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 이곳에 돋보기 눌렀을때 검색액티비티로 이동할 코드 구현!!!
        Toast.makeText(this, "오오오됐어~", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    // 네비게이션 (170123/상욱추가)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myPage) {

        } else if (id == R.id.nav_bookmark) {

        } else if (id == R.id.nav_setup) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // 여기까지 복붙~~
}

package com.goe_apps.android.timetowork;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private boolean running;
    private MyTimer timer;
    private int theTime;
    private RecyclerView logRecyclerView;
    private RecyclerView.Adapter logAdapter;
    private RecyclerView.LayoutManager logLayoutManager;
    private ArrayList<Integer> logDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        running = false;
        timer= new MyTimer();
        theTime = 0;
        logDataset = new ArrayList<Integer>();
//        logDataset = new String[]{
//                "olga",
//                "alireza",
//                "olga"
//        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    theTime = timer.stop();
                    running = false;
                    Snackbar.make(view, "Timer is stopped. the Time is: "+ theTime, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    logDataset.add(new Integer(theTime));
                    logAdapter.notifyItemInserted(logDataset.size() - 1);
                }else{
                    timer.start();
                    timer.sendEmptyMessage(0);
                    running = true;
                    Snackbar.make(view, "Timer is started.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        logRecyclerView = (RecyclerView) findViewById(R.id.loglist);
        logRecyclerView.setHasFixedSize(true);

        logLayoutManager = new LinearLayoutManager(this);
        logRecyclerView.setLayoutManager(logLayoutManager);

        logAdapter = new LogListAdapter(logDataset);
        logRecyclerView.setAdapter(logAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static class MyTimer extends Handler
    {

        public static final int START = 0;
        public static final int STOP = 1;
        public static boolean isRunning = false;
        public static int counter = 0;

        public void start(){
            isRunning = true;
        }

        public int stop(){
            isRunning = false;
            return counter;
        }

        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case START:
                    // Do something etc.
                    Log.d("TimerExample", "Timer 1");
                    if (isRunning) {
                        counter++;
                        sendEmptyMessageDelayed(START, 1000);
                    }else{
                        sendEmptyMessage(STOP);
                    }
                    break;
                case STOP:
                    counter = 0;
                    Log.d("TimerExample", "Timer 2");
                    break;
                default:
                    removeMessages(START);
                    removeMessages(STOP);
                    break;
            }
        }
    }
}

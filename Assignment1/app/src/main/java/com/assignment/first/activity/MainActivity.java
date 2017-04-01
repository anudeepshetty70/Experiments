package com.assignment.first.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.first.R;
import com.assignment.first.adapter.ViewPagerAdapter;
import com.assignment.first.fragment.FirstFragment;
import com.assignment.first.fragment.FourthFragment;
import com.assignment.first.fragment.SecondFragment;
import com.assignment.first.fragment.ThirdFragment;
import com.assignment.first.utils.AppConstants;
import com.robohorse.pagerbullet.PagerBullet;

public class MainActivity extends AppCompatActivity implements AppConstants {

    private TabLayout headerTabs;
    private TextView txtTabSelectedData;
    private PagerBullet viewPager;
    private Activity activity;
    private LinearLayout rltPoint5;
    private Button redBtn;
    private Button blueBtn;
    private Button greenBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initUI();
        initStartUpContent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(fragmentClickedReceiver, new IntentFilter(FragmentClickBroadCast));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(fragmentClickedReceiver);
    }

    private void initUI() {

        headerTabs = (TabLayout) findViewById(R.id.tabs);
        txtTabSelectedData = (TextView) findViewById(R.id.txt_tab_selected_data);
        viewPager = (PagerBullet) findViewById(R.id.viewpager);
        rltPoint5 = (LinearLayout)findViewById(R.id.rlt_point5);
        redBtn = (Button) findViewById(R.id.redBtn);
        blueBtn = (Button) findViewById(R.id.blueBtn);
        greenBtn = (Button) findViewById(R.id.greenBtn);
    }

    private void initStartUpContent() {

        populateTabs();

        txtTabSelectedData.setText(headerTabs.getTabAt(0).getText());

        setListeners();

        populateViewPager();

        setPoint5Background(redBtn);
    }

    private void setListeners() {
        headerTabs.setOnTabSelectedListener(new TabOnClicklistener());
        redBtn.setOnClickListener(new RedButtonOnclickListener());
        greenBtn.setOnClickListener(new GreenButtonOnclickListener());
        blueBtn.setOnClickListener(new BlueButtonOnclickListener());

    }

    private void populateTabs() {
        headerTabs.addTab(headerTabs.newTab().setText(getString(R.string.tab_item1)));
        headerTabs.addTab(headerTabs.newTab().setText(getString(R.string.tab_item2)));
        headerTabs.addTab(headerTabs.newTab().setText(getString(R.string.tab_item3)));
        headerTabs.addTab(headerTabs.newTab().setText(getString(R.string.tab_item4)));
        headerTabs.addTab(headerTabs.newTab().setText(getString(R.string.tab_item5)));
    }

    private void populateViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment());
        adapter.addFragment(new SecondFragment());
        adapter.addFragment(new ThirdFragment());
        adapter.addFragment(new FourthFragment());
        viewPager.setAdapter(adapter);
    }

    private class TabOnClicklistener implements TabLayout.OnTabSelectedListener {


        @Override
        public void onTabSelected(TabLayout.Tab tab) {

            txtTabSelectedData.setText(tab.getText());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private BroadcastReceiver fragmentClickedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            int fragmentNumber = viewPager.getViewPager().getCurrentItem();
            if (activity != null) {
                Toast.makeText(activity, "Fragment: "+String.valueOf(fragmentNumber), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class RedButtonOnclickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            setPoint5Background(v);

        }
    }

    private class BlueButtonOnclickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            setPoint5Background(v);
        }
    }

    private class GreenButtonOnclickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            setPoint5Background(v);
        }
    }

    public void setPoint5Background(View view){

        resetButtonSelection();
        Button pressedButton = (Button)view;
        pressedButton.setBackgroundResource(R.drawable.btn_state_selected);
        String buttonText = pressedButton.getText().toString();
        int backgroundColor = ContextCompat.getColor(activity, R.color.white);

        if(buttonText.contains(getString(R.string.btn_red))){

            backgroundColor = ContextCompat.getColor(activity, R.color.red);

        }if(buttonText.contains(getString(R.string.btn_green))){

            backgroundColor = ContextCompat.getColor(activity, R.color.green);

        }if(buttonText.contains(getString(R.string.btn_blue))){

            backgroundColor = ContextCompat.getColor(activity, R.color.blue);
        }

        rltPoint5.setBackgroundColor(backgroundColor);
    }

    private void resetButtonSelection(){

        redBtn.setBackgroundResource(R.drawable.btn_state_normal);
        blueBtn.setBackgroundResource(R.drawable.btn_state_normal);
        greenBtn.setBackgroundResource(R.drawable.btn_state_normal);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);

            // Checks the orientation of the screen
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                headerTabs.setTabMode(TabLayout.MODE_FIXED);

            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

                headerTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }
}

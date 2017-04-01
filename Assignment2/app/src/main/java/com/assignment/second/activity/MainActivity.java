package com.assignment.second.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.assignment.second.R;
import com.assignment.second.application.ApplicationController;
import com.assignment.second.dialog.TwoButtonActionDialog;
import com.assignment.second.model.PlaceModel;
import com.assignment.second.model.PlaceNameAdapter;
import com.assignment.second.utils.AppConstants;
import com.assignment.second.utils.CommonProgressDailog;
import com.assignment.second.utils.NetworkUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AppConstants {

    private Spinner spnNames;
    private TextView txtModeCar;
    private TextView txtModeTrain;
    private ArrayList<PlaceModel> placeListModel;
    private Button btnNavigate;
    private int selectedItemPosition;
    private CommonProgressDailog mCommonProgressDailog;
    private TwoButtonActionDialog twoButtonActionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setListeners();
        getDataFromServer();

    }

    private void initUI() {

        spnNames = (Spinner) findViewById(R.id.spn_names);

        txtModeCar = (TextView) findViewById(R.id.txt_mode_car);

        txtModeTrain = (TextView) findViewById(R.id.txt_mode_train);

        btnNavigate = (Button) findViewById(R.id.btnNavigate);

        mCommonProgressDailog = new CommonProgressDailog(this, getString(R.string.please_wait));

    }

    private void setListeners() {

        spnNames.setOnItemSelectedListener(new PlaceNameOnClickListener());

        btnNavigate.setOnClickListener(new NavigatiButtonOnClickListener());
    }

    private void getDataFromServer() {

        if(NetworkUtils.isNetworkAvailable(this)) {

            mCommonProgressDailog.showDailog();

            JsonArrayRequest request = new JsonArrayRequest(URL_TO_DOWNLOAD_DATA, new SucessListener(), new ErrorListener());

            ApplicationController.getInstance().addToRequestQueue(request);
        }else {


            showTwoButtonDialog();
        }
    }



    private class SucessListener implements Response.Listener<JSONArray>{


        @Override
        public void onResponse(JSONArray response) {

            mCommonProgressDailog.closeDailog();
            //VolleyLog.d("API Response", response);
            System.out.println("API Response:" + response.toString());

            placeListModel = new ArrayList<>();

            System.out.println("Parsed data:" + placeListModel);

            try {

                for (int i = 0; i < response.length(); i++) {

                    JSONObject jsonObj = (JSONObject) response.get(i);

                    PlaceModel placeModel = new Gson().fromJson(jsonObj.toString(), PlaceModel.class);

                    placeListModel.add(placeModel);


                }

                populateSpinner(placeListModel);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ErrorListener implements Response.ErrorListener{


        @Override
        public void onErrorResponse(VolleyError volleyError) {

            mCommonProgressDailog.closeDailog();

        }
    }
    private void populateSpinner(ArrayList<PlaceModel> placeListModels) {

        if (placeListModels != null) {

            String namesList[] = new String[placeListModels.size()];

            for (int i = 0; i < placeListModels.size(); i++) {

                namesList[i] = placeListModels.get(i).getName();
            }

            PlaceNameAdapter adapter = new PlaceNameAdapter(this, namesList);
            spnNames.setAdapter(adapter);
        }

    }

    private class PlaceNameOnClickListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            selectedItemPosition = position;

            updateViews(position);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class NavigatiButtonOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            double latitude = placeListModel.get(selectedItemPosition).getLocation().getLatitude();

            double longitude = placeListModel.get(selectedItemPosition).getLocation().getLongitude();

            navigateToLocation(latitude, longitude);


        }
    }


    private void updateViews(int position) {

        String carInfo = placeListModel.get(position).getFromcentral().getCar();
        String trainInfo = placeListModel.get(position).getFromcentral().getTrain();

        if (carInfo != null) {

            txtModeCar.setVisibility(View.VISIBLE);
            txtModeCar.setText(getString(R.string.mode_car) + " " + carInfo);

        } else {

            txtModeCar.setVisibility(View.GONE);
        }

        if (trainInfo != null) {

            txtModeTrain.setVisibility(View.VISIBLE);
            txtModeTrain.setText(getString(R.string.mode_Train) + " " + trainInfo);
        } else {
            txtModeTrain.setVisibility(View.GONE);
        }


    }

    private void navigateToLocation(double latitude, double longitude) {

        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private void showTwoButtonDialog(){

        twoButtonActionDialog =
                new TwoButtonActionDialog(this,getString(R.string.retry),getString(R.string.exit),getString(R.string.no_network),new RetryButtonClickListener(),new ExitButtonOnClickListener());
        twoButtonActionDialog.show();

    }

    private class RetryButtonClickListener implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            twoButtonActionDialog.dismiss();
            getDataFromServer();
        }
    }

    private class ExitButtonOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            twoButtonActionDialog.dismiss();
            finish();

        }
    }

}

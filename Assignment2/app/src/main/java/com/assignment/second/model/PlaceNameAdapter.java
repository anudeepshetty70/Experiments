package com.assignment.second.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.assignment.second.R;


public class PlaceNameAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private Context mContext;

    public String[] countryList;

    public PlaceNameAdapter(Context context,
                            String[] tempList) {
        super(context, R.layout.item_place_name, tempList);
        this.mContext = context;
        this.countryList = tempList;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_place_name, parent,
                    false);

            holder.txtName = (TextView) convertView.findViewById(R.id.txt_row);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(countryList[position]);
        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_place_name, parent,
                    false);

            holder.txtName = (TextView) convertView.findViewById(R.id.txt_row);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(countryList[position]);
        return convertView;
    }

    public class ViewHolder {

        TextView txtName;

        public TextView getTxtName() {
            return txtName;
        }

        public void setTxtName(TextView txtName) {
            this.txtName = txtName;
        }


    }

}

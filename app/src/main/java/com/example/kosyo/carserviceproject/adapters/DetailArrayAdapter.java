package com.example.kosyo.carserviceproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kosyo.carserviceproject.R;
import com.example.kosyo.carserviceproject.models.VehicleAtribute;

import java.util.ArrayList;

/**
 * Created by kosyo on 20.11.15.
 */
public class DetailArrayAdapter extends ArrayAdapter<VehicleAtribute> {
    private Activity activity;
    private ArrayList<VehicleAtribute> mCarInsurance;

    public DetailArrayAdapter(Activity context, ArrayList<VehicleAtribute> carInsurance) {
        super(context, R.layout.detail_layout, carInsurance);
        this.activity = context;
        this.mCarInsurance = carInsurance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        //reuse views
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.detail_layout, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvDetailAtribure = (TextView) rowView.findViewById(R.id.tvDetailAtribute);
            viewHolder.tvDetailExparationDate = (TextView) rowView.findViewById(R.id.tvDetailExparationDate);
            viewHolder.ivDetailEdit = (ImageView) rowView.findViewById(R.id.ivDetailEdit);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        VehicleAtribute mCarInsuranceObject = mCarInsurance.get(position);
        holder.tvDetailAtribure.setText(mCarInsuranceObject.getmName());
        holder.tvDetailExparationDate.setText(mCarInsuranceObject.getmValue());
        holder.ivDetailEdit.setImageResource(R.mipmap.ic_editor_border_color);

        return rowView;
    }

    static class ViewHolder {
        public TextView tvDetailAtribure;
        public TextView tvDetailExparationDate;
        public ImageView ivDetailEdit;
    }
}

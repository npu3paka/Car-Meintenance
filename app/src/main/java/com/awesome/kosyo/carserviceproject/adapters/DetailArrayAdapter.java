package com.awesome.kosyo.carserviceproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.awesome.kosyo.carserviceproject.R;
import com.awesome.kosyo.carserviceproject.activities.MainActivity;
import com.awesome.kosyo.carserviceproject.models.VehicleAttribute;

import java.util.ArrayList;

/**
 * Created by kosyo on 20.11.15.
 */
public class DetailArrayAdapter extends ArrayAdapter<VehicleAttribute> {
    public final static String TAG = DetailArrayAdapter.class.getSimpleName();
    private Activity activity;
    private ArrayList<VehicleAttribute> mVehicleAtributeList;
    private VehicleAttribute mVehicleAttributeObj;
    private UpdatingListListener updatingListener;

    public DetailArrayAdapter(Activity context, ArrayList<VehicleAttribute> vehicleAtributeList) {
        super(context, R.layout.list_item_detail, vehicleAtributeList);
        this.activity = context;
        this.mVehicleAtributeList = vehicleAtributeList;
    }

    public void setUpdatingListener(UpdatingListListener updatingListener) {
        this.updatingListener = updatingListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final ViewHolder holder;
        //reuse views
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_detail, parent, false);

            // configure view holder
            holder = new ViewHolder();
            holder.tvDetailAtribure = (TextView) rowView.findViewById(R.id.tvDetailAtribute);
            holder.tvDetailExparationDate = (TextView) rowView.findViewById(R.id.tvDetailExparationDate);
            holder.ivDetailEdit = (ImageView) rowView.findViewById(R.id.ivDetailEdit);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        // fill data
        mVehicleAttributeObj = mVehicleAtributeList.get(position);
        holder.tvDetailAtribure.setText(mVehicleAttributeObj.getmName());
        String userFriendlyDate = MainActivity.toUserFriendlyFormatDate( mVehicleAttributeObj.getmValue());
        holder.tvDetailExparationDate.setText(userFriendlyDate);
        holder.ivDetailEdit.setImageResource(R.mipmap.ic_editor_border_color);

        // If Edit Image was clicked open Date Picker
        holder.ivDetailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != ListView.INVALID_POSITION) {
                    // Update the view showing the date
                    updatingListener.showSimpleDatePicker(position, holder.tvDetailExparationDate.getText().toString(), new DataUpdatingListener() {
                        @Override
                        public void dataUpdated(String newData) {
                            holder.tvDetailExparationDate.setText(newData);
                        }
                    });
                }
            }
        });

        return rowView;
    }

    public interface UpdatingListListener {
        void showSimpleDatePicker(final int position, String s, DataUpdatingListener listener);
    }

    public interface DataUpdatingListener {
        void dataUpdated(String newData);
    }

    static class ViewHolder {
        public TextView tvDetailAtribure;
        public TextView tvDetailExparationDate;
        public ImageView ivDetailEdit;
    }

}

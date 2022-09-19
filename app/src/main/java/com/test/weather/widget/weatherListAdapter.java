package com.test.weather.widget;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.weather.R;


//list view adapter
public class weatherListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String cityName;
    private final String[] desc;
    private final float[] mMax;
    private final float[] mMin;
    private final String[] dt;
    private final String[] imgid;

    public weatherListAdapter(Activity context, String cityName, String[] desc,
                              float[] mMax, float[] mMin, String[] dt,
                               String[] imgid) {
        super(context, R.layout.itemweather, desc);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.cityName = cityName;
        this.desc = desc;
        this.mMax = mMax;
        this.mMin = mMin;
        this.dt = dt;
        this.imgid=imgid;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.itemweather, null,true);

        TextView l_cityName = (TextView) rowView.findViewById(R.id.l_cityName);
        TextView l_textDesc = (TextView) rowView.findViewById(R.id.l_textDesc);
        TextView l_textMax = (TextView) rowView.findViewById(R.id.l_textMax);
        TextView l_textMin = (TextView) rowView.findViewById(R.id.l_textMin);
        TextView l_textdt = (TextView) rowView.findViewById(R.id.l_textdt);
        ImageView l_imageState = (ImageView) rowView.findViewById(R.id.l_imageState);

        l_cityName.setText(cityName);
        l_textDesc.setText(desc[position]);
        l_textMax.setText(String.valueOf(mMax[position]));
        l_textMin.setText(String.valueOf(mMin[position]));
        l_textdt.setText(dt[position]);

        Context c = context;
        int id = c.getResources().getIdentifier("drawable/"+imgid[position], null, c.getPackageName());
        l_imageState.setImageResource(id);

        return rowView;

    };
}
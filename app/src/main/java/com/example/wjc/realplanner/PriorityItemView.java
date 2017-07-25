package com.example.wjc.realplanner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by WJC on 2017-07-24.
 */

public class PriorityItemView extends LinearLayout {

    ImageView image;
    TextView textview1;
    TextView textview2;

    public PriorityItemView(Context context) {
        super(context);
        init(context);
    }

    public PriorityItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.priority_item_view,this,true);
        image = (ImageView)findViewById(R.id.image);
        textview1 = (TextView)findViewById(R.id.textview1);
        textview2 = (TextView)findViewById(R.id.textview2);
    }

    public void setImage(int resId){
        image.setImageResource(resId);
    }

    public void setDate(String date){
        textview1.setText(date);
    }
    public void setMemo(String memo) {
        textview2.setText(memo);
    }
}

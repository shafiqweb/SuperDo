package com.example.shafiq.designproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shafiq.designproject2.model.Image;
import com.example.shafiq.designproject2.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Image> imageModelArrayList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Image> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

        }
        TextView imageNameListTextView;
        ImageView categoryImageView;
        imageNameListTextView = convertView.findViewById(R.id.imageNameListTextViewId);
        categoryImageView = convertView.findViewById(R.id.imageViewId);
        imageNameListTextView.setText(imageModelArrayList.get(position).getName());
        categoryImageView.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        return convertView;
    }
}

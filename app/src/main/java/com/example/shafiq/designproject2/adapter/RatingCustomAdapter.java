package com.example.shafiq.designproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shafiq.designproject2.model.ModelRating;
import com.example.shafiq.designproject2.R;

import java.util.ArrayList;

public class RatingCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelRating> modelRatingArrayList;

    public RatingCustomAdapter(Context context, ArrayList<ModelRating> modelRatingArrayList) {
        this.context = context;
        this.modelRatingArrayList = modelRatingArrayList;
    }

    @Override
    public int getCount() {
        return modelRatingArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelRatingArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.listview_rating_item,parent,false);
        }
        ImageView imageViewOwner,imageViewOwnerAvailableIcon;
        TextView textViewOwnerName,textViewOwnerAddress,textViewOwnerAvailableNow;

        imageViewOwner = convertView.findViewById(R.id.imageViewOwnerID);
        textViewOwnerName = convertView.findViewById(R.id.textViewOwnerFullNameID);
        textViewOwnerAddress = convertView.findViewById(R.id.textViewOwnerAddressID);
        imageViewOwnerAvailableIcon = convertView.findViewById(R.id.imageViewAvailableNowID);
        textViewOwnerAvailableNow = convertView.findViewById(R.id.textViewAvailableNowID);
        imageViewOwner.setImageResource(modelRatingArrayList.get(position).getOwnerImageList());
        textViewOwnerName.setText(modelRatingArrayList.get(position).getOwnerName());
        textViewOwnerAddress.setText(modelRatingArrayList.get(position).getOwnerAddress());
        imageViewOwnerAvailableIcon.setImageResource(modelRatingArrayList.get(position).getOwnerAvailableImage());
        textViewOwnerAvailableNow.setText(modelRatingArrayList.get(position).getOwnerAvailableText());

        return convertView;
    }
}

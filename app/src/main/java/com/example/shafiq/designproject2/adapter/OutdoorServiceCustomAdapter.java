package com.example.shafiq.designproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shafiq.designproject2.model.ModelOutdoorService;
import com.example.shafiq.designproject2.R;

import java.util.ArrayList;

public class OutdoorServiceCustomAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ModelOutdoorService> modelOutdoorServiceArrayList;

    public OutdoorServiceCustomAdapter(Context context, ArrayList<ModelOutdoorService> modelOutdoorServiceArrayList) {
        this.context = context;
        this.modelOutdoorServiceArrayList = modelOutdoorServiceArrayList;
    }
    @Override
    public int getCount() {
        return modelOutdoorServiceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelOutdoorServiceArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.listview_outdoorservice_item,parent,false);
        }
        ImageView imageViewOwner,imageViewOwnerAvailableIcon;
        TextView textViewOwnerName,textViewOwnerZone,textViewOwnerAvailableNow;

        imageViewOwner = convertView.findViewById(R.id.imageViewOwnerID);
        textViewOwnerName = convertView.findViewById(R.id.textViewOwnerFullNameID);
        textViewOwnerZone = convertView.findViewById(R.id.textViewOwnerZoneID);
        imageViewOwnerAvailableIcon = convertView.findViewById(R.id.imageViewAvailableNowID);
        textViewOwnerAvailableNow = convertView.findViewById(R.id.textViewAvailableNowID);
        imageViewOwner.setImageResource(modelOutdoorServiceArrayList.get(position).getOwnerImageList());
        textViewOwnerName.setText(modelOutdoorServiceArrayList.get(position).getOwnerName());
        textViewOwnerZone.setText(modelOutdoorServiceArrayList.get(position).getOwnerZone());
        imageViewOwnerAvailableIcon.setImageResource(modelOutdoorServiceArrayList.get(position).getOwnerAvailableImage());
        textViewOwnerAvailableNow.setText(modelOutdoorServiceArrayList.get(position).getOwnerAvailableText());

        return convertView;
    }
}

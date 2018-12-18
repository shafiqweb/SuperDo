package com.example.shafiq.designproject2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shafiq.designproject2.model.BookingModel;
import com.example.shafiq.designproject2.R;

import java.util.ArrayList;
import java.util.TreeSet;

public class BookingListCustomAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private ArrayList<BookingModel> list = new ArrayList<BookingModel>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    private LayoutInflater layoutInflater;

    public BookingListCustomAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final BookingModel item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final BookingModel item) {
        list.add(item);
        sectionHeader.add(list.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BookingModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = layoutInflater.inflate(R.layout.snippet_item1, parent, false);
                    holder.imageView = convertView.findViewById(R.id.imageViewOwnerProfileID);
                    holder.textViewOwnerName = convertView.findViewById(R.id.textOwnerName);
                    holder.textViewBeautyCategory = convertView.findViewById(R.id.textBeautyCategory);
                    holder.button = convertView.findViewById(R.id.cancelBookingButtonID);
                    break;
                case TYPE_SEPARATOR:
                    convertView = layoutInflater.inflate(R.layout.snippet_item2, parent, false);
                    holder.textView = convertView.findViewById(R.id.textSeparator);
                    break;
            }
            assert convertView != null;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (rowType == TYPE_ITEM) {
            holder.imageView.setImageResource(list.get(position).getOwnerImage());
            holder.textViewOwnerName.setText(list.get(position).getOwnerName());
            holder.textViewBeautyCategory.setText(list.get(position).getBeautyCategory());
//            holder.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
            final View finalConvertView = convertView;
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(finalConvertView.getContext());
                    AlertDialog alertDialog;
                    builder.setTitle("Do you want to delete?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            list.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        } else if (rowType == TYPE_SEPARATOR) {
            holder.textView.setText(list.get(position).getSectionHeaderName());
        }


        return convertView;
    }

    public static class ViewHolder {
        TextView textView,textViewOwnerName,textViewBeautyCategory;
        public ImageView imageView;
        public Button button;
    }


}

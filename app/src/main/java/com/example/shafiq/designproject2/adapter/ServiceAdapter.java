package com.example.shafiq.designproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.model.ProviderServiceInfo;
import java.util.HashMap;
import java.util.List;

public class ServiceAdapter extends BaseExpandableListAdapter {
    private AddButtonClickListener addButtonClickListener;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<ProviderServiceInfo>> listDataChild;

    public ServiceAdapter(Context context, List<String> listDataHeader, HashMap<String, List<ProviderServiceInfo>> listDataChild, AddButtonClickListener addButtonClickListener) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
        this.addButtonClickListener = addButtonClickListener;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        final String headerText = (String) getGroup(groupPosition);
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.group_layout, parent, false);
        }
        TextView headerTextView = convertView.findViewById(R.id.headerTextViewID);
        headerTextView.setText(headerText);
        Button addServiceButton = convertView.findViewById(R.id.addServiceButtonID);
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClickListener.onAddButtonClick(groupPosition, headerText);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.child_layout, parent, false);
        }
        TextView childTextView1 = convertView.findViewById(R.id.childTextViewID1);
        TextView childTextView2 = convertView.findViewById(R.id.childTextViewID2);
        ProviderServiceInfo providerServiceInfo = (ProviderServiceInfo) getChild(groupPosition, childPosition);
        childTextView1.setText(providerServiceInfo.getDescription());
        childTextView2.setText(providerServiceInfo.getDuration());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface AddButtonClickListener {
        void onAddButtonClick(int groupPosition, String headerText);
    }
}

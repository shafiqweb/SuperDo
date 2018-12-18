package com.example.shafiq.designproject2.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.adapter.ServiceAdapter;
import com.example.shafiq.designproject2.model.ProviderServiceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
    String groupName;
    int lastExpandedPosition = -1;
    String[] headerList = new String[]{"1.Hair", "2.Nails", "3.Massage", "4.Spray-Tan", "5.Make-Up", "6.Facial",
            "7.Hair-Removal", "8.Nutrition", "9.Injection", "10.Coaching", "11.Styling",
            "12.Influencer"};
    ExpandableListView expandableListView;
    ServiceAdapter serviceAdapter;
    List<String> listDataHeader = new ArrayList<String>();
    HashMap<String, List<ProviderServiceInfo>> listDataChild = new HashMap<String, List<ProviderServiceInfo>>();

    public static final String KEY = "InformationKey";
    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<ProviderServiceInfo> items1 = new ArrayList<ProviderServiceInfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Toolbar toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prepareListData();

        expandableListView = findViewById(R.id.expandableListViewID);
        expandableListView.setGroupIndicator(null);
        serviceAdapter = new ServiceAdapter(this, listDataHeader, listDataChild, new ButtonClickListener());
        expandableListView.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged();
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                groupName = listDataHeader.get(groupPosition);
                Toast.makeText(getApplicationContext(), "Group Name = " + groupName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ProviderServiceInfo childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                if (childPosition == 0) {
                    Toast.makeText(getApplicationContext(), "Child value of child position " + childPosition + " = " + childString.getDescription(), Toast.LENGTH_SHORT).show();
                    childPosition = childPosition + 1;
                    Toast.makeText(getApplicationContext(), "Child value of child position " + childPosition + " = " + childString.getDuration(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && lastExpandedPosition != groupPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

    }

    public void prepareListData() {
        listDataHeader = Arrays.asList(headerList);
        for (String groupTitle : listDataHeader) {
            /* Retriving or loading items from SharedPreferences dynamically and keeping it to items1   */
            gson = new Gson();
            sharedPreferences = getPreferences(0);
            String jsonList1 = sharedPreferences.getString(groupTitle, "");
            Type type = new TypeToken<ArrayList<ProviderServiceInfo>>() {
            }.getType();
            items1 = gson.fromJson(jsonList1, type);
            Log.d(KEY, "onCreate '" + items1 + "' was changed");

            if (items1 == null)
                listDataChild.put(groupTitle, new ArrayList<ProviderServiceInfo>());
            else
                listDataChild.put(groupTitle, items1);
        }
    }

    class ButtonClickListener implements ServiceAdapter.AddButtonClickListener {

        @Override
        public void onAddButtonClick(int groupPosition, final String headerText) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ServicesActivity.this);
            @SuppressLint("InflateParams") final View dialogueView = ServicesActivity.this.getLayoutInflater().inflate(R.layout.dialogue_add_service, null, false);
            builder.setView(dialogueView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            TextView addServiceHeaderTextView = dialogueView.findViewById(R.id.addServiceHeaderTextID);
            addServiceHeaderTextView.setText(headerText);
            final EditText descriptionEditText = dialogueView.findViewById(R.id.descriptionEditTextID);
            final EditText durationEditText = dialogueView.findViewById(R.id.durationEditTextID);
            Button addButton = dialogueView.findViewById(R.id.addButtonID);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String providerDescription = descriptionEditText.getText().toString();
                    String providerDuration = durationEditText.getText().toString();
                    ProviderServiceInfo providerServiceInfo = new ProviderServiceInfo(providerDescription, providerDuration);
                    addItem(headerText, providerServiceInfo);
                    alertDialog.dismiss();
                }
            });
        }

        private void addItem(String headerText, ProviderServiceInfo providerServiceInfo) {
            List<ProviderServiceInfo> items = listDataChild.get(headerText);/*Creating a childList under headerText which already
                                                                             selected by user,Here items is a list of child under
                                                                             selected headerText
                                                                             */
            if (items == null) {
                items = new ArrayList<ProviderServiceInfo>();
            }
            items.add(0, providerServiceInfo);/*Keeping a object i.e. reference of model class ProviderServiceInfo
                                                     in a child list items
                                                     */

            /* Saving items dynamically in SharedPreferences using Gson
           and using dynamical key headerText to save child item dynamically
            */
            gson = new Gson();
            sharedPreferences = getPreferences(0);
            editor = sharedPreferences.edit();
            String jsonList1 = gson.toJson(items);
            editor.putString(headerText, jsonList1);
            editor.apply();
            Log.d(KEY, "The key '" + headerText + jsonList1 + "' was changed");

            serviceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booking_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        if (id == R.id.action_settings_booking_manager_navigationID) {
            Toast.makeText(this, "Services   " + groupName, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

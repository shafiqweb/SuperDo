package com.example.shafiq.designproject2.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.shafiq.designproject2.model.ModelOutdoorService;
import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.OwnerDetailsActivity;
import com.example.shafiq.designproject2.adapter.OutdoorServiceCustomAdapter;

import java.util.ArrayList;

public class OutdoorService extends Fragment implements View.OnClickListener {
    Button buttonPopupOutdoorService;
    PopupMenu popupMenuOutdoor;
    ListView listViewOutdoorService;
    OutdoorServiceCustomAdapter outdoorServiceCustomAdapter;
    ArrayList<ModelOutdoorService> modelOutdoorServiceArrayList;
    private int[] drawableOwnerImageList = new int[]{R.drawable.owner1, R.drawable.owner12, R.drawable.owner13, R.drawable.owner14,
            R.drawable.owner16, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1,
            R.drawable.owner1, R.drawable.owner1, R.drawable.owner1};
    private String[] ownerNameList = new String[]{"Francois Vaccarello", "Josen Vaccarello", "Helen Vaccarello", "Shannu Vaccarello",
            "Vennella Vaccarello", "Xentia Vaccarello", "Mekhshu Vaccarello", "Beron Vaccarello", "Ponting Vaccarello", "Kueish Vaccarello",
            "Famella Vaccarello", "Bushra Vaccarello", "13venella Vaccarello"};
    private String[] ownerZoneList = new String[]{"Uttara", "Khilkhet", "Dhanmondi", "Gulsion", "Banani", "Baridhara",
            "Rampura", "Badda", "Motizil", "Tongi", "Mirpur14", "Gazipura", "Gazipur"};
    private int[] drawableAvailableImageIconList = new int[]{R.drawable.available_icon, R.drawable.available_icon, R.drawable.available_icon
            , R.drawable.available_icon, R.drawable.available_icon, R.drawable.available_icon, R.drawable.available_icon
            , R.drawable.available_icon, R.drawable.available_icon, R.drawable.available_icon, R.drawable.available_icon
            , R.drawable.available_icon, R.drawable.available_icon};
    private String[] availableTextList = new String[]{"Available now", "Available now", "Available now", "Available now"
            , "Available now", "Available now", "Available now", "Available now", "Available now", "Available now"
            , "Available now", "Available now", "Available now"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewOutdoor = inflater.inflate(R.layout.outdoor_service, container, false);
        buttonPopupOutdoorService = viewOutdoor.findViewById(R.id.buttonPopupOutdoorID);
        listViewOutdoorService = viewOutdoor.findViewById(R.id.listViewOutdoorServiceID);
        modelOutdoorServiceArrayList = populateList();
        outdoorServiceCustomAdapter = new OutdoorServiceCustomAdapter(getActivity(), modelOutdoorServiceArrayList);
        listViewOutdoorService.setAdapter(outdoorServiceCustomAdapter);
        buttonPopupOutdoorService.setOnClickListener(this);
        listViewOutdoorService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ownerName = modelOutdoorServiceArrayList.get(position).getOwnerName();
                int profilePic = modelOutdoorServiceArrayList.get(position).getOwnerImageList();
                String[] separatedName = ownerName.split(" ");
                String firstName = separatedName[0];
                Intent intent = new Intent(getActivity(),OwnerDetailsActivity.class);
                intent.putExtra("FirstName",firstName);
                intent.putExtra("OwnerFullName",ownerName);
                intent.putExtra("OwnerProfileImage",profilePic);
                startActivity(intent);
            }
        });
        return viewOutdoor;
    }

    private ArrayList<ModelOutdoorService> populateList() {
        ArrayList<ModelOutdoorService> list = new ArrayList<ModelOutdoorService>();
        for (int i = 0; i < ownerNameList.length; i++) {
            ModelOutdoorService modelOutdoorService = new ModelOutdoorService();
            modelOutdoorService.setOwnerImageList(drawableOwnerImageList[i]);
            modelOutdoorService.setOwnerName(ownerNameList[i]);
            modelOutdoorService.setOwnerZone(ownerZoneList[i]);
            modelOutdoorService.setOwnerAvailableImage(drawableAvailableImageIconList[i]);
            modelOutdoorService.setOwnerAvailableText(availableTextList[i]);
            list.add(modelOutdoorService);
        }

        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPopupOutdoorID) {
            popupMenuOutdoor = new PopupMenu(getActivity(), buttonPopupOutdoorService);
            //popupMenuOutdoor.setGravity(Gravity.END);
            popupMenuOutdoor.getMenuInflater().inflate(R.menu.menu_popup, popupMenuOutdoor.getMenu());
            popupMenuOutdoor.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    String location = item.getTitle().toString();
                    buttonPopupOutdoorService.setText(location);
                    return true;
                }
            });
            popupMenuOutdoor.show();
        }
    }

}

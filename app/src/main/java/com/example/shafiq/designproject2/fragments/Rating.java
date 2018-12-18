package com.example.shafiq.designproject2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shafiq.designproject2.model.ModelRating;
import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.adapter.RatingCustomAdapter;

import java.util.ArrayList;

public class Rating extends Fragment {
    ListView listViewRating;
    RatingCustomAdapter ratingCustomAdapter;
    ArrayList<ModelRating> modelRatingArrayList;
    private int[] drawableOwnerImageList = new int[]{R.drawable.owner1, R.drawable.owner12, R.drawable.owner13, R.drawable.owner14,
            R.drawable.owner16, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1,
            R.drawable.owner1, R.drawable.owner1, R.drawable.owner1};
    private String[] ownerNameList = new String[]{"Francois Vaccarello", "Josen Vaccarello", "Helen Vaccarello", "Shannu Vaccarello",
            "Vennella Vaccarello", "Xentia Vaccarello", "Mekhshu Vaccarello", "Beron Vaccarello", "Ponting Vaccarello", "Kueish Vaccarello",
            "Famella Vaccarello", "Bushra Vaccarello", "13venella Vaccarello"};
    private String[] ownerAddress = new String[]{"House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230", "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230", "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230", "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230", "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230", "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230",
            "House-2,Floor-3,Road-7,Sector-7,Uttara,Dhaka-1230"};
    private int[] drawableAvailableImageIconList = new int[]{R.drawable.available_icon,R.drawable.available_icon,R.drawable.available_icon
            ,R.drawable.available_icon,R.drawable.available_icon,R.drawable.available_icon,R.drawable.available_icon
            ,R.drawable.available_icon,R.drawable.available_icon,R.drawable.available_icon,R.drawable.available_icon
            ,R.drawable.available_icon,R.drawable.available_icon};
    private String[] availableTextList = new String[]{"Available now","Available now","Available now","Available now"
            ,"Available now","Available now","Available now","Available now","Available now","Available now"
            ,"Available now","Available now","Available now"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRating = inflater.inflate(R.layout.rating, container, false);
        listViewRating = viewRating.findViewById(R.id.listViewRatingID);
        modelRatingArrayList = populateList();
        ratingCustomAdapter = new RatingCustomAdapter(getActivity(), modelRatingArrayList);
        listViewRating.setAdapter(ratingCustomAdapter);
        return viewRating;
    }

    private ArrayList<ModelRating> populateList() {
        ArrayList<ModelRating> list = new ArrayList<ModelRating>();
        for (int i = 0; i < ownerNameList.length; i++) {
            ModelRating modelRating = new ModelRating();
            modelRating.setOwnerImageList(drawableOwnerImageList[i]);
            modelRating.setOwnerName(ownerNameList[i]);
            modelRating.setOwnerAddress(ownerAddress[i]);
            modelRating.setOwnerAvailableImage(drawableAvailableImageIconList[i]);
            modelRating.setOwnerAvailableText(availableTextList[i]);
            list.add(modelRating);
        }

        return list;
    }

}

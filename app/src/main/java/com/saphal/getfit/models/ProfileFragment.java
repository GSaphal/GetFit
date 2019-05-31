package com.saphal.getfit.models;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.saphal.getfit.R;
import com.saphal.getfit.utils.FirebaseHelper;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragments";

    private FirebaseHelper mFirebaseHelper;
    private CircleImageView display_profile;
    private TextView display_name, display_age, display_height, display_weight, display_goal_weight, display_gender, display_snipper;
    private Context mContext;
    private String userID;
    private TextView display_active;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
     //   display_profile = view.findViewById(R.id.display_profile);
        display_name = view.findViewById(R.id.display_name);
        display_age = view.findViewById(R.id.display_age);
        display_height = view.findViewById(R.id.display_height);
        display_weight = view.findViewById(R.id.display_weight);
        display_goal_weight = view.findViewById(R.id.display_goal_weight);
        display_gender = view.findViewById(R.id.display_gender);
        display_snipper = view.findViewById(R.id.display_spinner);
        display_active=view.findViewById(R.id.display_active);
        mContext = getActivity();
        mFirebaseHelper=new FirebaseHelper(getActivity());
        if (mFirebaseHelper.getmAuth().getCurrentUser() != null) {
            userID = mFirebaseHelper.getmAuth().getCurrentUser().getUid();
        }
        mFirebaseHelper.getmRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setupWidgets(getData(dataSnapshot));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void setupWidgets(UserFire userdata) {
        Toast.makeText(mContext, ""+userdata.getTv_name(), Toast.LENGTH_SHORT).show();
        display_name.setText(userdata.getTv_name());
        display_age.setText(userdata.getTv_age());
        display_gender.setText(userdata.getValue());
        display_weight.setText(userdata.getTv_weight());
        display_height.setText(userdata.getTv_height());
        display_goal_weight.setText(userdata.getTv_goal_weight());
        display_active.setText(userdata.getActive());
    }


    private UserFire getData(DataSnapshot dataSnapshot) {
        UserFire userdata = new UserFire();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            if (ds.getKey().equals("users")) {
                userdata.setTv_name(ds.child(userID).getValue(UserFire.class).getTv_name());
                userdata.setTv_age(ds.child(userID).getValue(UserFire.class).getTv_age());
                userdata.setValue(ds.child(userID).getValue(UserFire.class).getValue());
                userdata.setTv_weight(ds.child(userID).getValue(UserFire.class).getTv_weight());
                userdata.setTv_height(ds.child(userID).getValue(UserFire.class).getTv_height());
                userdata.setTv_goal_weight(ds.child(userID).getValue(UserFire.class).getTv_goal_weight());
                userdata.setActive(ds.child(userID).getValue(UserFire.class).getActive());
                    userdata.setTv_age(ds.child(userID).getValue(UserFire.class).getTv_age());
            }
        }
        return userdata;

    }
}
package com.example.android.colgpartal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import model.Profile;

public class fragment_user_profile extends Fragment {
    Button button;
    private ParseImageView mealPreview;
    public fragment_user_profile(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, parent, false);
        button= (Button) v.findViewById(R.id.profile_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(mealName.getWindowToken(), 0);
                startCamera();

            }
        });
        mealPreview = (ParseImageView) v.findViewById(R.id.profile_pic);
        mealPreview.setVisibility(View.INVISIBLE);
        return v;

    }
    /*
	 * All data entry about a Meal object is managed from the NewMealActivity.
	 * When the user wants to add a photo, we'll start up a custom
	 * CameraFragment that will let them take the photo and save it to the Meal
	 * object owned by the NewMealActivity. Create a new CameraFragment, swap
	 * the contents of the fragmentContainer (see activity_new_meal.xml), then
	 * add the NewMealFragment to the back stack so we can return to it when the
	 * camera is finished.
	 */
    public void startCamera() {
        Fragment cameraFragment = new CameraFragment();
        FragmentTransaction transaction = getActivity().getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.container, cameraFragment);
        transaction.addToBackStack("fragment_user_profile");
        transaction.commit();
    }
    @Override
    public void onResume() {
        super.onResume();

        final ParseFile[] photoFile = new ParseFile[1];
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);

        query.whereEqualTo("UserId",ParseUser.getCurrentUser().getObjectId());

        query.findInBackground(new FindCallback<Profile>() {
            @Override
            public void done(List<Profile> messages, ParseException e) {
                if (e == null) {
                    photoFile[0] = messages.get(0).getPhotoFile();
                } else {
                    Log.v("Error:", "Error:" + e.getMessage());
                }
            }
        });
        if (photoFile[0] != null) {
            mealPreview.setParseFile(photoFile[0]);
            mealPreview.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    mealPreview.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}

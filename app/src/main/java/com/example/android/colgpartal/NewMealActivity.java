package com.example.android.colgpartal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.colgportal.R;

/*
 * NewMealActivity contains two fragments that handle
 * data entry and capturing a photo of a given meal.
 * The Activity manages the overall meal data.
 */
public class NewMealActivity extends Activity {

	//private Meal meal;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//meal = new Meal();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);

		// Begin with main data entry view,
		// NewMealFragment
		setContentView(R.layout.activity_user_profile);
		FragmentManager manager = getFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.container);

		if (fragment == null) {
			fragment = new fragment_user_profile();
			manager.beginTransaction().add(R.id.container, fragment)
					.commit();
		}
	}

	/*public Meal getCurrentMeal() {
		return meal;
	}*/

}

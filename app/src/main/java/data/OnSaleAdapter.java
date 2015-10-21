package data;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.android.colgpartal.R;
import com.example.android.colgpartal.user_profile;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import model.BooksModel;
import model.CarModel;

public class OnSaleAdapter extends ArrayAdapter<String> {


    private String mUserId;
    List<String> data;
    Activity activity;

    public OnSaleAdapter(Activity act, List<String> messages) {
        super(act, 0, messages);
        activity = act;
        data = messages;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.on_sale_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.bookName);
            holder.box = (CheckBox) convertView.findViewById(R.id.check);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();


        final String message =  getItem(position);

        holder.name.setText(message);
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    ParseQuery<BooksModel> query = ParseQuery.getQuery(BooksModel.class);
                    query.whereEqualTo("name", holder.name.getText().toString());

                    query.findInBackground(new FindCallback<BooksModel>() {
                        @Override
                        public void done(List<BooksModel> list, ParseException e) {

                            if (e == null) {
                                if (list != null) {
                                    BooksModel book = (BooksModel) list.get(0);
                                    book.setHasSold("YES");
                                    book.saveInBackground(new SaveCallback() {
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                // Saved successfully.
                                                Log.d("error", "saved successfully");
                                            } else {
                                                // The save failed.
                                                Log.d("error", "problem occured");
                                            }
                                        }
                                    });
                                }
                                else{
                                    ParseQuery<CarModel> query = ParseQuery.getQuery(CarModel.class);
                                    query.whereEqualTo("name", holder.name.getText().toString());

                                    query.findInBackground(new FindCallback<CarModel>() {
                                        @Override
                                        public void done(List<CarModel> list, ParseException e) {

                                            if (e == null) {
                                                if (list != null) {
                                                    CarModel car = (CarModel) list.get(0);
                                                    car.setHasSold("YES");
                                                    car.saveInBackground(new SaveCallback() {
                                                        public void done(ParseException e) {
                                                            if (e == null) {
                                                                // Saved successfully.
                                                                Log.d("error", "saved successfully");
                                                            } else {
                                                                // The save failed.
                                                                Log.d("error", "problem occured");
                                                            }
                                                        }
                                                    });
                                                }
                                            } else {
                                                Log.d("error", "adapter error");
                                            }
                                        }
                                    });
                                    activity.startActivity(new Intent(activity, user_profile.class));
                                }

                                //startActivity(activity,user_profile.class);
                            } else {
                                Log.d("error", "adapter error");
                            }
                        }
                    });
                    activity.startActivity(new Intent(activity, user_profile.class));
                }
            }
        });
        return convertView;
    }


    class ViewHolder {
        public TextView name;
        public CheckBox box;
    }
}
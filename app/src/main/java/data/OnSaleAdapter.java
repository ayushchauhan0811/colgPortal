package data;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.android.colgpartal.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import model.BooksModel;

public class OnSaleAdapter extends ArrayAdapter<BooksModel> {


    private String mUserId;
    List<BooksModel> data;
    Activity activity;

    public OnSaleAdapter(Activity act, List<BooksModel> messages) {
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


        final BooksModel message = (BooksModel) getItem(position);

        holder.name.setText(message.getName());
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
                                BooksModel book = (BooksModel) list.get(0);
                                book.setHasSold("YES");
                                book.saveInBackground(new SaveCallback() {
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            // Saved successfully.
                                            Log.d("error","saved successfully");
                                        } else {
                                            // The save failed.
                                            Log.d("error","problem occured");
                                        }
                                    }
                                });
                            } else {
                                Log.d("error", "adapter error");
                            }
                        }
                    });
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
package data;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.colgpartal.MyApplication;
import com.example.android.colgpartal.R;
import com.example.android.colgpartal.user_account;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import model.BooksModel;
import model.Colg;

/**
 * Created by Charu gupta on 01-Oct-15.
 */
public class BooksAdapter extends ArrayAdapter<BooksModel> {

    private String mUserId;
    List<BooksModel> data;
    Activity activity;

    public BooksAdapter(Activity act, List<BooksModel> messages) {
        super(act, 0, messages);
        activity=act;
        data=messages;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.books_buy_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.desc= (TextView) convertView.findViewById(R.id.desc);
            holder.stream= (TextView) convertView.findViewById(R.id.stream);
            holder.quantity= (TextView) convertView.findViewById(R.id.quantity);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        //final Colg message =  data.get(position);

        final BooksModel message = (BooksModel)getItem(position);
        /*if(holder.name.getText().toString()==null)
        {
            Toast.makeText(activity, "view empty", Toast.LENGTH_LONG).show();
        }*/
        holder.name.setText(message.getName());
        holder.desc.setText(message.getDesc());
        holder.stream.setText(message.getStream());
        holder.quantity.setText(message.getQuantity().toString());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Colg");
                query.whereEqualTo("name", holder.name.getText().toString());
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {

                        if(e==null){
                            Colg colg= (Colg) list.get(0);
                            MyApplication.global.setSomeVariable(colg.getColgId());
                        }
                        else{
                            Log.d("error", "adapter error");
                        }
                    }
                });

                activity.startActivity(new Intent(activity,user_account.class));
            }
        });
        return convertView;
    }



    class ViewHolder {
        public TextView name;
        public TextView desc;
        public TextView stream;
        public TextView quantity;
    }
}

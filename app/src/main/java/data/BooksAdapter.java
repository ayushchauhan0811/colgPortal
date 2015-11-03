package data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.colgportal.R;
import com.example.android.colgportal.UserProfile;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.Serializable;
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
            //holder.desc= (TextView) convertView.findViewById(R.id.desc);
            holder.stream= (TextView) convertView.findViewById(R.id.stream);
            holder.quantity= (TextView) convertView.findViewById(R.id.quantity);
            holder.callBuyer= (ImageView) convertView.findViewById(R.id.caller);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        //final Colg message =  data.get(position);

        final BooksModel message = getItem(position);
        /*if(holder.name.getText().toString()==null)
        {
            Toast.makeText(activity, "view empty", Toast.LENGTH_LONG).show();
        }*/
        holder.name.setText(message.getName());
        //holder.desc.setText(message.getDesc());
        holder.stream.setText(message.getStream());
        holder.quantity.setText(message.getQuantity().toString());

        final StringBuilder builder = new StringBuilder();
        builder.append("Books Name: " + message.getName());
        builder.append("\nStream " + message.getStream());
        builder.append("\nDesc: " + message.getDesc());
        builder.append("\nSeller Id: " + message.getSellerId());

        final ParseUser[] user = new ParseUser[1];

        ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
        query.whereEqualTo("objectId", message.getSellerId());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if(e==null) {
                     user[0] = list.get(0);
                }
            }
        });

        holder.callBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.setData(Uri.parse(user[0].getEmail()));
                intent.putExtra(Intent.EXTRA_EMAIL, ParseUser.getCurrentUser().getEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "College Portal");
                intent.putExtra(Intent.EXTRA_TEXT, (Serializable) builder);
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivity(intent);
                }
                //activity.startActivity(new Intent(activity,user_account.class));
            }
        });
        return convertView;
    }



    class ViewHolder {
        public TextView name;
        //public TextView desc;
        public TextView stream;
        public TextView quantity;
        public ImageView callBuyer;
    }
}
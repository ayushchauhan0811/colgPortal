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

import com.example.android.colgpartal.R;

import java.io.Serializable;
import java.util.List;

import model.BooksModel;

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
            holder.stream= (TextView) convertView.findViewById(R.id.stream);
            holder.quantity= (TextView) convertView.findViewById(R.id.quantity);
            holder.callBuyer= (ImageView) convertView.findViewById(R.id.caller);
            holder.report=(ImageView) convertView.findViewById(R.id.report);
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
        holder.desc.setText(message.getDesc());
        holder.stream.setText(message.getStream());
        holder.quantity.setText(message.getQuantity().toString());

        final StringBuilder builder = new StringBuilder();
        builder.append("Books Name: " + message.getName());
        builder.append("Stream " + message.getStream());
        builder.append("Desc: " + message.getDesc());
        builder.append("Seller Id: " + message.getSellerId());


        holder.callBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "aac9095@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "College Portal");
                intent.putExtra(Intent.EXTRA_TEXT, (Serializable) builder);
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivity(intent);
                }
                //activity.startActivity(new Intent(activity,user_account.class));
            }
        });
        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return convertView;
    }



    class ViewHolder {
        public TextView name;
        public TextView desc;
        public TextView stream;
        public TextView quantity;
        public ImageView callBuyer;
        public ImageView report;
    }
}

package data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.colgportal.R;

import java.io.Serializable;
import java.util.List;

import model.CarModel;

public class CarsAdapter extends ArrayAdapter<CarModel> {

    private String mUserId;
    List<CarModel> data;
    Activity activity;

    public CarsAdapter(Activity act, List<CarModel> messages) {
        super(act, 0, messages);
        activity=act;
        data=messages;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cars_buy_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.model= (TextView) convertView.findViewById(R.id.model);
            holder.price= (TextView) convertView.findViewById(R.id.price);
            holder.callBuyer= (Button) convertView.findViewById(R.id.caller);
            //holder.report=(ImageView) convertView.findViewById(R.id.report);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        //final Colg message =  data.get(position);

        final CarModel message = getItem(position);
        /*if(holder.name.getText().toString()==null)
        {
            Toast.makeText(activity, "view empty", Toast.LENGTH_LONG).show();
        }*/
        holder.name.setText(message.getName());
        holder.desc.setText(message.getDesc());
        holder.model.setText(message.getModel());
        holder.price.setText(message.getPrice().toString());

        final StringBuilder builder = new StringBuilder();
        builder.append("Books Name: " + message.getName());
        builder.append("Model " + message.getModel());
        builder.append("Desc: " + message.getDesc());
        builder.append("Seller Id: " + message.getSellerId());


        holder.callBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "darshgupta50@gmail.com");
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
        public TextView model;
        public TextView price;
        public Button callBuyer;
        public ImageView report;
    }
}

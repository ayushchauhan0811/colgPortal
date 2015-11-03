package data;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.colgportal.PlacesDetailShow;
import com.example.android.colgportal.R;

import java.util.List;

import model.Places;

public class Placesadapter extends ArrayAdapter<Places> {


    private String mUserId;
    List<Places> data;
    Activity activity;

    public Placesadapter(Activity act, List<Places> messages) {
        super(act, 0, messages);
        activity=act;
        data=messages;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.placename);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        //final Places message =  data.get(position);

        final Places message = (Places)getItem(position);
        if(holder.name.getText().toString()==null)
        {
            Toast.makeText(activity, "view empty", Toast.LENGTH_LONG).show();
        }
        holder.name.setText(message.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.startActivity(new Intent(activity,PlacesDetailShow.class));
            }
        });
        return convertView;
    }



    class ViewHolder {
        public TextView name;
    }
}


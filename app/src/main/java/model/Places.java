package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Charu gupta on 27-Sep-15.
 */
@ParseClassName("Places")
public class Places extends ParseObject {


    public String getColgId() {
        return getObjectId();
    }

    public String getName() {
        return getString("name");
    }

    public String getLocation() {
        return getString("location");
    }

    public String getDistance() {
        return getString("distance");
    }

    public String getReview() {
        return getString("review");
    }


    public void setColgId(String colgId) {
        put("colgId", colgId);
    }

    public void setName(String name) {
        put("name", name);
    }

    public void setLocation(String location) {
        put("location", location);
    }

    public void setDistance(String distance) {
        put("distance", distance);
    }

    public void setReview(String review) {
        put("review", review);
    }
}



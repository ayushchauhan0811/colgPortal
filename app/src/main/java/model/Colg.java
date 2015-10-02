package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Charu gupta on 27-Sep-15.
 */
@ParseClassName("Colg")
public class Colg extends ParseObject {



        public String getColgId(){
            return  getObjectId();
        }
        public String getName() {
            return getString("name");
        }
        public String getLocation(){
            return getString("location");
        }
        public String getSpeciality(){
            return getString("speciality");
        }


        public void setColgId(String colgId) {
            put("colgId", colgId);
        }
        public void setName(String name) {
            put("name", name);
        }
        public void setLocation(String location){put("location",location);}
        public void setSpeciality(String speciality){put("speciality",speciality);}
    }



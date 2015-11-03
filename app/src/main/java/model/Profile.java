package model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Profile")
public class Profile extends ParseObject {
    public Profile(){

    }
    public String getUserId() {
        return ParseUser.getCurrentUser().getObjectId();
    }

    public void setUserId(String user) {
        put("UserId", user);
    }
    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }
}

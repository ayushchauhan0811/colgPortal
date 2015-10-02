package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Charu gupta on 30-Sep-15.
 */

    @ParseClassName("BooksModel")
    public class BooksModel extends ParseObject {

    public String getBookId(){
        return  getString("bookId");
    }
    public String getColgId() { return getString("colgId"); }
    public String getHasSold(){return getString("hasSold");}
    public String getSellerId(){return getString("sellerId");}
    public String getName() {
        return getString("name");
    }
    public String getStream(){
        return getString("stream");
    }
    public String getDesc(){
        return getString("desc");
    }
    public  Integer getQuantity(){return getInt("quantity");}


    public void setBookId(String bookId) {
        put("bookId", bookId);
    }
    public void setColgId(String colgId){put("colgId",colgId);}
    public void setHasSold(String has){put("hasSold",has);}
    public void setSellerId(String sellerId){put("sellerId",sellerId);}
    public void setName(String name) {
        put("name", name);
    }
    public void setStream(String stream){put("stream",stream);}
    public void setDesc(String desc){put("desc",desc);}
    public void setQuantity(int quantity){put("quantity",quantity);}
}





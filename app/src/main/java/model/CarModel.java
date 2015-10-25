package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("CarModel")
public class CarModel extends ParseObject {

    public String getCarId(){
        return  getString("CarId");
    }
    public String getColgId() { return getString("colgId"); }
    public String getHasSold(){return getString("hasSold");}
    public String getSellerId(){return getString("sellerId");}
    public String getName() {
        return getString("name");
    }
    public String getModel(){
        return getString("model");
    }
    public String getDesc(){
        return getString("desc");
    }
    public  Integer getPrice(){return getInt("price");}


    public void setBCarId(String carId) {
        put("CarId", carId);
    }
    public void setColgId(String colgId){put("colgId",colgId);}
    public void setHasSold(String has){put("hasSold",has);}
    public void setSellerId(String sellerId){put("sellerId",sellerId);}
    public void setName(String name) {
        put("name", name);
    }
    public void setModel(String model){put("model",model);}
    public void setDesc(String desc){put("desc",desc);}
    public void setPrice(int price){put("price",price);}
    public void setReport(String rep){put("report",rep);}
}





package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter

public class Country implements Serializable {
    private int id;
    private String name;
    private String phoneCode;
    private Supplier supplier;
    private Manufacturer manufacturer;
    private long importRate;
    private long population;
    private long carRate;
    private int tariff;
    private ArrayList<String> neighbors;


//    public void addNeighbors(String neighbors) {
//        neighbors.add(neighbors);
//    }
    public static long demand(long importRate,long productionRate,long carRate){
        return importRate +  productionRate - (carRate / 2);
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

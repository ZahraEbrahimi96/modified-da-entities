package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter

public class Country implements Serializable {
    private int id;
    private int tariff;
    private String name;
    private String phoneCode;
    private long importRate;
    private long population;
    private long carRate;
    private String neighbors;
    private Supplier supplier;
    private Manufacturer manufacturer;


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

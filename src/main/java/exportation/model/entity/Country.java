package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter

public class Country {
    private int id;
    private String name;
    private int tariff;
    private String phoneCode;
    private long importRate;
    private long population;
    private long carRate;
    private String neighbors;

//    public static long demand(long importRate, long productionRate, long carRate) {
//        return importRate + productionRate - (carRate / 2);
//    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

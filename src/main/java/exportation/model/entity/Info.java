package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Info {
    private int id;
    private long population;
    private long carRate;
    private String climate;
    private AccessPath accessPath;
    private Supplier supplier;
    private Manufacturer manufacturer;
    private Imports importing;
    private Exports exporting;
    private String demand;
    private String tariff;
//    private ArrayList<String> neighbors;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

//demand ro hesab kone

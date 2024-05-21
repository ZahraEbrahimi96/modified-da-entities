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

    private long population;
    private long carRate;
    private ArrayList<String> neighbors;
    private String climate;
    //private String manufacturingStatus;
    //private String importationStatus;
    //private String exportationStatus;
    private String accessPath;
    private String lifeExpectancy;
    private String demand;
    private String tariff;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

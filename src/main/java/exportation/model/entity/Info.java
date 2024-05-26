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
    private String accessPath;
    private String lifeExpectancy;
    private String demand;
    private String tariff;
    private ArrayList<String> neighbors;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

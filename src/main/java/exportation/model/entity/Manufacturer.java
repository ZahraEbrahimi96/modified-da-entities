package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)


public class Manufacturer extends Company {
    private int id;
    private String name;
    private int productionRate;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
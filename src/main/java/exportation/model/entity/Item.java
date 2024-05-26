package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.Brand;
import exportation.model.entity.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Item {
    private int id;
    private String name;
    private String model;
    private String dimensionOfUnite;
    private String dimensionOfPallet;
    private int palletCapacity;
    private float cost;
    private float weightOfUnit;
    private float weightOfPallet;
    private Brand brand;
    private Type type;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

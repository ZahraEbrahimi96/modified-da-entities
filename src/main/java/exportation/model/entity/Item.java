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

    private int itemId;
    private String name;
    private String model;
    private Type type;
    private Brand brand;
    private String dimensionOfUnite;
    private String dimensionOfPallet;
    private float weightOfUnit;
    private float weightOfPallet;
    private int palletCapacity;
    private float cost;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

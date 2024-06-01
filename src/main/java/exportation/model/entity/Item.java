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
    private String dou;         //dimensionOfUnite
    private String dop;        //dimensionOfPallet
    private int palletCapacity;
    private float cost;
    private float wou;              //weightOfUnit
    private float wop;             //weightOfPallet


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

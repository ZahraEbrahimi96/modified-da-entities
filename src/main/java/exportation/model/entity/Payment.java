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

public class Payment {
    private int id;
    private float tax;
    private float insurance;
    private Item item;
    private Transportation transportation;
    private Company company;


    public static long cif(float cost, int amper, int palletCapacity, float insurance, float freight) {
        return (long) ((cost * amper * palletCapacity) + insurance + freight);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

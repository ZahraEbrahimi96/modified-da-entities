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
    private Country country;

    public static long totalCost(int tariff, float cost, float tax, float insurance, float freight) {
        float cif = cif(cost,  insurance, freight);
        tariff = (int) ((cif * tariff) / 100);
        tax = (cif * tax) / 100;
        return (long) (tariff + cif + tax);
    }

    public static float cif(float cost,  float insurance, float freight) {
        return (long) (cost  + insurance + freight);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

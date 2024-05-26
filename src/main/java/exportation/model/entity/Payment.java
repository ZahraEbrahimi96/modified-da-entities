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
    private long totalCost;
    private float tax;
    private float insurance;
    private Item cost;
    private Transportation freight;
    private Info tariff;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

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
    private Item item;
    private Transportation transportation;
    private Info info;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
//...
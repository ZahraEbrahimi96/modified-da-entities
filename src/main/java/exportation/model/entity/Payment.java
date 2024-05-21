package exportation.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Payment {

    private float tax;
    private Transportation freight;
    private Item cost;
    private float insurance;
    private long totalCost;
    private Info tariff;

    // return: cif, pish pardakht, mablaghe kol, mande hesab

}

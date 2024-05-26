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

    private long totalCost;
    private float tax;
    private float insurance;
    private Item cost;
    private Transportation freight;
    private Info tariff;

    // return: cif, pish pardakht, mablaghe kol, mande hesab

}

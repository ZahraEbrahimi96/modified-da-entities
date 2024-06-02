package exportation.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Imports {
    private int id;
    private long hsCode;
    private Country country;
    private long quantity;
    private long usdValue;
}
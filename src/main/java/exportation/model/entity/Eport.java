package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.RoleAccess;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)


public abstract class Eport {

    private int id;
    private long hsCode;
    private Country country;
    private long quantity;
    private long usdValue;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

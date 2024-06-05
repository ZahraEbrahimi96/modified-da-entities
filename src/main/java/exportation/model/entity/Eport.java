package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.regex.Pattern;
@Setter
@Getter
@NoArgsConstructor
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

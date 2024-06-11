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


public class Manufacturer {
    private int id;
    private String name;
    private String product;
    private String address;
    private String email;
    private String phoneNumber;
    private long productionRate;
    private Country country;
    private Person person;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

package exportation.model.entity;

import com.google.gson.Gson;
import jdk.nashorn.internal.runtime.Debug;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public abstract class Company {

    private int id;
    private String name;
    private String product;
    private String address;
    private String email;
    private String phone;
    private Person manager;
    private Country country;




    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
//....



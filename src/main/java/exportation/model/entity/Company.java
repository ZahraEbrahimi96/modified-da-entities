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

public class Company {

    private int id;
    private String name;
    private String product;
    private String address;
    private String email;
    private String phoneNumber;
//    private Person person;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}



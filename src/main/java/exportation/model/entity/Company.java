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
    private int companyId;
    private String companyName;
    private Person companyManager;
    private String companyProduct;
    private String companyAddress;
    private String companyEmail;
    private RoleAccess companyType;
    private Country countryName;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}



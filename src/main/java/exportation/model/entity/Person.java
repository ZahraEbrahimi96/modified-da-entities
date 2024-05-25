package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Person {
    private int personId;
    private String name;
    private String family;
    private Gender gender;
    private String email;
    private String phone;
    private String nationalId;
    private String position;
    private String address;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}

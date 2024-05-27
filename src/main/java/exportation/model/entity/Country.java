package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)


public class Country {
    private int id;
    private String name;
    private String phoneCode;
    private ArrayList<Info> info;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
//...
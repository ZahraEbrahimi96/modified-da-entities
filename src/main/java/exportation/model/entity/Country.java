package exportation.model.entity;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.regex.Pattern;


@NoArgsConstructor
@SuperBuilder(toBuilder = true)


public class Country implements Serializable {
    private int id;
    private String name;
    private String phoneCode;
    private String relatedMarket;
    private Supplier supplier;
    private Manufacturer manufacturer;
    private Info info;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

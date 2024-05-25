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


public class Country{
    private int countryId;
    private String countryName;
    private String countryPhoneCode;
    private String relatedMarket;  //BE IN FEKR MIKONAM KE HAZFESH KONIM YA TYPESHO BE COMPANY TAGHIR BEDIM
    private ArrayList<Company> company;
    private ArrayList<Info> info;



    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

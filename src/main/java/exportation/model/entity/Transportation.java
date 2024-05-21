package exportation.model.entity;

import com.google.gson.Gson;
import exportation.model.entity.enums.TransportType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Transportation {

    private TransportType transportType;
    private String direction; //shayad class beshe
    private Country country;
    private String origin;
    private float freight;
    private Item item;

    // nazdik tarin rah
    // arzun tarin rahe ersal ra pishnahad dahad
    // aya be yek khoruji be onvane barname tahvil dahad?

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

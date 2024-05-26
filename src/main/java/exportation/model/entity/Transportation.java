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
    private String direction;
    private String origin;
    private float freight;
    private Item item;
    private TransportType transportType;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

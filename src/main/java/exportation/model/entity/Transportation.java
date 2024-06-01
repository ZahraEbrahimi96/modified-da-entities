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

public class Transportation {
    private int id;
    private String direction;
    private String origin;
    private float freight;
    private Item item;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

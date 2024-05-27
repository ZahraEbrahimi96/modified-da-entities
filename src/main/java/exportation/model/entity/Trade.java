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

public class Trade {
    private int id;
    private String status;
    private String correspondences;
    private String contract;
    private String agreement;
    private String invoice;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

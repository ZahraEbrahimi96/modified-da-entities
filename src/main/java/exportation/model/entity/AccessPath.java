package exportation.model.entity;
import com.google.gson.Gson;
import exportation.model.entity.enums.Navigation;
import exportation.model.entity.enums.PathType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class AccessPath {
    private int id;
    private String city;
    private PathType pathType;
    private float distance;
    private Navigation navigation;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
//...
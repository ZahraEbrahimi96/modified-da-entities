package exportation.model.entity;
import com.google.gson.Gson;
import exportation.model.entity.enums.Navigation;
import exportation.model.entity.enums.PathType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;
import java.util.regex.Pattern;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class AccessPath implements Serializable {
    private int id;
    private String city;
    private PathType pathType;
    private float distance;
    private Navigation navigation;


}

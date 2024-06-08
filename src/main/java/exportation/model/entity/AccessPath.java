package exportation.model.entity;
import exportation.model.entity.enums.Navigation;
import exportation.model.entity.enums.PathType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

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

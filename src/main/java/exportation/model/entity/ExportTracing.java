package exportation.model.entity;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class ExportTracing implements Serializable {
    private int id;
    private boolean loadingStatus;
    private boolean prePayment;
    private boolean checkout;
    private Transportation transportation;
    private Trade trade;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

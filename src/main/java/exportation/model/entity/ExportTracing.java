package exportation.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ExportTracing {
    private int id;
    private boolean loadingStatus;
    private boolean prePayment;
    private boolean checkout;
    private String waybill;
    private String invoice;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

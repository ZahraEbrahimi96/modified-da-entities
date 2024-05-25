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
    private int exportId;
    private LocalDateTime transferTime;
    private LocalDateTime receiveTime;
    private boolean loadingStatus;
    private String invoice;
    private String waybill;
    private boolean prePayment;
    private boolean checkout;
    private Person transferee;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

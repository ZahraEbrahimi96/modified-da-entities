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

public class Transportation {
    private int id;
    private String direction;
    private float freight;
    private Item item;
    private Company company;
    private ExportTracing exportTracing;
    private Country country;
    private LocalDateTime dateTime;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
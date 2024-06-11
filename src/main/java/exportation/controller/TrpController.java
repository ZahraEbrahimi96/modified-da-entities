package exportation.controller;

import exportation.model.bl.TransportationBl;
import exportation.model.entity.Country;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Item;
import exportation.model.entity.Transportation;

public class TrpController {

    //save
    public static void save(String direction, float freight, Item item, Country country, ExportTracing exportTracing) {
        try {
            Transportation transportation = Transportation
                    .builder()
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .country(country)
                    .exportTracing(exportTracing)
                    .build();
            TransportationBl.getTransportationBl().save(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String direction, float freight, Item item, Country country, ExportTracing exportTracing) {
        try {

            Transportation transportation = Transportation
                    .builder()
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .country(country)
                    .exportTracing(exportTracing)
                    .build();

            TransportationBl.getTransportationBl().edit(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            Transportation transportation = new Transportation();
            transportation.setId(id);
            TransportationBl.getTransportationBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

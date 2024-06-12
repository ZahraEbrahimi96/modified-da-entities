package exportation.controller;

import exportation.model.bl.TransportationBl;
import exportation.model.entity.*;

public class TransportationController {

    //save
    public static void save(String direction, float freight, Item item, Company company, ExportTracing exportTracing) {
        try {
            Transportation transportation = Transportation
                    .builder()
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .company(company)
                    .exportTracing(exportTracing)
                    .build();
            TransportationBl.getTransportationBl().save(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String direction, float freight, Item item, Company company, ExportTracing exportTracing) {
        try {

            Transportation transportation = Transportation
                    .builder()
                    .id(id)
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .company(company)
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

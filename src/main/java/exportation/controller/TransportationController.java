package exportation.controller;

import exportation.model.bl.TransportationBl;
import exportation.model.entity.*;

import java.time.LocalDateTime;

public class TransportationController {

    //save
    public static void save(String direction, float freight, Item item, Company company, Country country, LocalDateTime dateTime) {
        try {
            Transportation transportation = Transportation
                    .builder()
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .company(company)

                    .country(country)
                    .dateTime(dateTime)
                    .build();
            TransportationBl.getTransportationBl().save(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String direction, float freight, Item item, Company company, Country country,LocalDateTime dateTime) {
        try {

            Transportation transportation = Transportation
                    .builder()
                    .id(id)
                    .direction(direction)
                    .freight(freight)
                    .item(item)
                    .company(company)
                    .country(country)
                    .dateTime(dateTime)
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

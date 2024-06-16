package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.entity.Country;
import exportation.model.entity.enums.Gender;

import java.util.regex.Pattern;

public class CountryController {

    //save
    public static String save(String name, int tariff, String phoneCode, long importRate, long population, long carRate, String neighbors) {
        try {
            {
                Country country = Country
                        .builder()
                        .name(name)
                        .tariff(tariff)
                        .phoneCode(phoneCode)
                        .importRate(importRate)
                        .population(population)
                        .carRate(carRate)
                        .neighbors(neighbors)
                        .build();
                CountryBl.getCountryBl().save(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    //edit
    public static void edit(int id, String name, int tariff, String phoneCode, long importRate, long population, long carRate, String neighbors) {
        try {
            {
                Country country = Country
                        .builder()
                        .id(id)
                        .name(name)
                        .tariff(tariff)
                        .phoneCode(phoneCode)
                        .importRate(importRate)
                        .population(population)
                        .carRate(carRate)
                        .neighbors(neighbors)
                        .build();

                CountryBl.getCountryBl().edit(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            Country country = new Country();
            country.setId(id);
            CountryBl.getCountryBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

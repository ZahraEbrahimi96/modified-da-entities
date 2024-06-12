package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.entity.Country;

import java.util.regex.Pattern;

public class CountryController {

    //save
    public static void save(String name, String phoneCode, long population, long carRate, int tariff, String neighbors) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{2,30}$", name) && Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
                Country country = Country
                        .builder()
                        .name(name)
                        .phoneCode(phoneCode)
                        .population(population)
                        .carRate(carRate)
                        .tariff(tariff)
                        .neighbors(neighbors)
                        .build();
                CountryBl.getCountryBl().save(country);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String name, String phoneCode, long population, long carRate, int tariff, String neighbors) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{2,30}$", name) && Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
                Country country = Country
                        .builder()
                        .name(name)
                        .phoneCode(phoneCode)
                        .population(population)
                        .carRate(carRate)
                        .tariff(tariff)
                        .neighbors(neighbors)
                        .build();
                CountryBl.getCountryBl().edit(country);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            Country country = new Country();
            CountryBl.getCountryBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
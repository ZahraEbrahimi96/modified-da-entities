package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.entity.Country;
import exportation.model.entity.Manufacturer;
import exportation.model.entity.Supplier;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CountryController {

public static void save(String name, String phoneCode, Supplier supplier, Manufacturer manufacturer, long population, long carRate, int tariff, ArrayList<String> neighbors) {
    try {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
            Country country = Country
                    .builder()
                    .name(name)
                    .phoneCode(phoneCode)
                    .supplier(supplier)
                    .manufacturer(manufacturer)
                    .population(population)
                    .carRate(carRate)
                    .tariff(tariff)
                    .neighbors(neighbors)
                    .build();
            CountryBl.getCountryBl().save(country);
        }else {
            System.out.println("Invalid Data");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


public static void edit(int id, String name, String phoneCode, Supplier supplier, Manufacturer manufacturer, long population, long carRate, int tariff, ArrayList<String> neighbors) {
    try {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
            Country country = Country
                    .builder()
                    .name(name)
                    .phoneCode(phoneCode)
                    .supplier(supplier)
                    .manufacturer(manufacturer)
                    .population(population)
                    .carRate(carRate)
                    .tariff(tariff)
                    .neighbors(neighbors)
                    .build();
            CountryBl.getCountryBl().edit(country);
        }else {
            System.out.println("Invalid Data");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

public static void remove(int id) {
    try {
        Country country = new Country();
        CountryBl.getCountryBl().remove(id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

//    public static String findAll() {
//        try {
//             if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
//            List<Country> countryList = new ArrayList<>();
//            Country country = Country
//                     .builder()
//                    .name(name)
//                    .phoneCode(phoneCode)
//                    .supplier(supplier)
//                    .manufacturer(manufacturer)
//                    .population(population)
//                    .carRate(carRate)
//                    .tariff(tariff)
//                    .neighbors(neighbors)
//                    .build();
//           countryList.add(country);
//           CountryBl.getCountryBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(countryList);
//              }else {
//             System.out.println("Invalid Data");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return countryList;
//    }
//
//
//    public String findById (int id) {
//        try {
//              if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
//             Country country = Country
////                    .builder()
//                    .name(name)
//                    .phoneCode(phoneCode)
//                    .supplier(supplier)
//                    .manufacturer(manufacturer)
//                    .population(population)
//                    .carRate(carRate)
//                    .tariff(tariff)
//                    .neighbors(neighbors)
//                    .build();
//            country.setId(id);
//
//            }else {
////             System.out.println("Invalid Data");
////            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
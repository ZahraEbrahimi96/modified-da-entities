package exportation.controller;

import exportation.model.bl.CountryBl;
import exportation.model.entity.*;

import java.util.regex.Pattern;

public class CountryController {
    public static void save(String name, String phoneCode, String relatedMarket, Supplier supplier, Manufacturer manufacturer, Info info) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode) && Pattern.matches("^[a-zA-Z\\s]{2,30}$",relatedMarket)) {
                Country country = Country
                        .builder()
                        .name(name)
                        .phoneCode(phoneCode)
                        .relatedMarket(relatedMarket)
                        .supplier(supplier)
                        .manufacturer(manufacturer)
                        .info(info)
                        .build();
                CountryBl.getCountryBl().save(country);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String name, String phoneCode, String relatedMarket, Supplier supplier, Manufacturer manufacturer, Info info) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode) && Pattern.matches("^[a-zA-Z\\s]{2,30}$",relatedMarket)) {
            Country country = Country
                    .builder()
                    .id(id)
                    .name(name)
                    .phoneCode(phoneCode)
                    .relatedMarket(relatedMarket)
                    .supplier(supplier)
                    .manufacturer(manufacturer)
                    .info(info)
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
//             if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode) && Pattern.matches("^[a-zA-Z\\s]{2,30}$",relatedMarket)) {
//            List<Country> countryList = new ArrayList<>();
//            Country country = Country
//                    .builder()
//                    .id(id)
//                    .name(name)
//                    .phoneCode(phoneCode)
//                    .relatedMarket(relatedMarket)
//                    .supplier(supplier)
//                    .manufacturer(manufacturer)
//                    .info(info)
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
//              if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode) && Pattern.matches("^[a-zA-Z\\s]{2,30}$",relatedMarket)) {
//             Country country = Country
////                    .builder()
////                    .id(id)
////                    .name(name)
////                    .phoneCode(phoneCode)
////                    .relatedMarket(relatedMarket)
////                    .supplier(supplier)
////                    .manufacturer(manufacturer)
////                    .info(info)
////                    .build();
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

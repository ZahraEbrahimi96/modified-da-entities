package exportation.controller;
import exportation.model.bl.ManufacturerBl;
import exportation.model.entity.Country;
import exportation.model.entity.Manufacturer;
import exportation.model.entity.Person;

import java.util.regex.Pattern;

public class MnController {
    public static void save (String name, String product, String address, String email, Country counrty, String phoneNumber, int productionRate, Person person) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Manufacturer manufacturer = Manufacturer
                        .builder()
                        .name(name)
                        .product(product)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .country(counrty)
                        .productionRate(productionRate)
                        .person(person)
                        .build();
                ManufacturerBl.getManufacturerBl().save(manufacturer);
            }else{
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String name, String product, String address, String email, Country counrty, String phoneNumber, int productionRate, Person person) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Manufacturer manufacturer = Manufacturer
                        .builder()
                        .name(name)
                        .product(product)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .country(counrty)
                        .productionRate(productionRate)
                        .person(person)
                        .build();
                ManufacturerBl.getManufacturerBl().edit(manufacturer);
            }else{
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            Manufacturer manufacturer = new Manufacturer();
//            Manufacturer.setId(id);
//            ManufacturerBl.getManufacturerBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<Manufacturer> manufacturerList = new ArrayList<>();
//            Manufacturer manufacturer = Manufacturer
//                        .builder()
//                        .name(name)
//                        .product(product)
//                        .address(address)
//                        .phoneNumber(phoneNumber)
//                        .email(email)
//                        .country(counrty)
//                        .productionRate(productionRate)
//                        .person(person)
//                        .build();
//            manufacturerList.add(manufacturer);
//             ManufacturerBl.getManufacturerBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return manufacturerList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            Manufacturer manufacturer = Manufacturer
//                        .builder()
//                        .name(name)
//                        .product(product)
//                        .address(address)
//                        .phoneNumber(phoneNumber)
//                        .email(email)
//                        .country(counrty)
//                        .productionRate(productionRate)
//                        .person(person)
//                        .build();
//            manufacturer.setId(id);
//
//           ManufacturerBl.getManufacturerBl().findById();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//        public static String findByName(String name) {
//        try {
//
//            List<Manufacturer> manufacturerList = new ArrayList<>();
//            Manufacturer manufacturer = Manufacturer
//                        .builder()
//                        .name(name)
//                        .product(product)
//                        .address(address)
//                        .phoneNumber(phoneNumber)
//                        .email(email)
//                        .country(counrty)
//                        .productionRate(productionRate)
//                        .person(person)
//                        .build();
//            manufacturerList.add(manufacturer);
//             ManufacturerBl.getManufacturerBl().findByName();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return manufacturerList;
//    }
}

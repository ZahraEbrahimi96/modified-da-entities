package exportation.controller;

import exportation.model.bl.SupplierBl;
import exportation.model.bl.SupplierBl;
import exportation.model.entity.Country;
import exportation.model.entity.Supplier;
import exportation.model.entity.Person;
import exportation.model.entity.Supplier;

import java.util.regex.Pattern;

public class SuController {

    //save
    public static void save(String name, String product, String address, String phoneNumber, String email, Country counrty, boolean onlineSale, Person person) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{2,30}$", name) && Pattern.matches("^[a-zA-Z\\s\\@]{200}$", email) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
                Supplier supplier = Supplier
                        .builder()
                        .name(name)
                        .product(product)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .country(counrty)
                        .onlineSale(onlineSale)
                        .person(person)
                        .build();
                SupplierBl.getSupplierBl().save(supplier);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String name, String product, String address, String phoneNumber, String email, Country counrty, boolean onlineSale, Person person) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{2,30}$", name) && Pattern.matches("^[a-zA-Z\\s\\@]{200}$", email) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
                Supplier supplier = Supplier
                        .builder()
                        .name(name)
                        .product(product)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .country(counrty)
                        .onlineSale(onlineSale)
                        .person(person)
                        .build();
                SupplierBl.getSupplierBl().edit(supplier);
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
            Supplier supplier = new Supplier();
            supplier.setId(id);
            SupplierBl.getSupplierBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

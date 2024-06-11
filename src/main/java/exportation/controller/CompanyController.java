package exportation.controller;

import exportation.model.bl.CompanyBl;
import exportation.model.entity.Company;

import java.util.regex.Pattern;

public class CompanyController {

    //save
    public static String save(String name, String product, String address, String email, String phoneNumber) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[a-zA-Z\\s]{2,30}$", product) && Pattern.matches("^[a-zA-Z\\s\\@]{200}$", email) && Pattern.matches("\\d{3}-\\d{6}-\\d{1}|\\d[10]", phoneNumber) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
                Company company = Company
                        .builder()
                        .name(name)
                        .product(product)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .build();
                CompanyBl.getCompanyBl().save(company);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    //edit
    public static void edit(int id, String name, String product, String address, String email, String phoneNumber) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[a-zA-Z\\s]{2,30}$", product) && Pattern.matches("^[a-zA-Z\\s\\@]{200}$", email) && Pattern.matches("\\d{3}-\\d{6}-\\d{1}|\\d[10]", phoneNumber) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
                Company company = Company
                        .builder()
                        .id(id)
                        .name(name)
                        .product(product)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .build();
                CompanyBl.getCompanyBl().edit(company);
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
            Company company = new Company();
            company.setId(id);
            CompanyBl.getCompanyBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

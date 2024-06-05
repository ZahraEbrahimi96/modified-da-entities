package exportation.controller;
import exportation.model.bl.SupplierBl;
import exportation.model.entity.Supplier;

import java.util.regex.Pattern;

public class SuController {
    public static void save (String name, String product, String address, String email, String phoneNumber, boolean onlineSale) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Supplier supplier = Supplier
                        .builder()
                        .name(name)
                        .address(address)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .onlineSale(onlineSale)
                        .build();
                SupplierBl.getSupplierBl().save(supplier);
            }else{
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String name, String product, String address, String email, String phoneNumber, boolean onlineSale) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Supplier supplier = Supplier
                        .builder()
                        .id(id)
                        .name(name)
                        .address(address)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .onlineSale(onlineSale)
                        .build();
                SupplierBl.getSupplierBl().edit(supplier);
            }else{
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            Supplier supplier = new Supplier();
//            Supplier.setId(id);
//            SupplierBl.getSupplierBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<Supplier> supplierList = new ArrayList<>();
//            Supplier supplier = Supplier
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .address(address)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .onlineSale(onlineSale)
//                        .build();
//            supplierList.add(supplier);
//             SupplierBl.getSupplierBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return supplierList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            Supplier supplier = Supplier
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .address(address)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .onlineSale(onlineSale)
//                        .build();
//            supplier.setId(id);
//
//           SupplierBl.getSupplierBl().findById();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//        public static String findByName(String name) {
//        try {
//
//            List<Supplier> supplierList = new ArrayList<>();
//            Supplier supplier = Supplier
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .address(address)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .onlineSale(onlineSale)
//                        .build();
//            supplierList.add(supplier);
//             SupplierBl.getSupplierBl().findByName();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return supplierList;
//    }
}

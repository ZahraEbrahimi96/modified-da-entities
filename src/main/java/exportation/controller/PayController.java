package exportation.controller;

import exportation.model.bl.PaymentBl;
import exportation.model.entity.*;

public class PayController {

    public static void save(float tax, float insurance, Item item, Transportation transportation, Country country) {
        try {

            Payment payment = Payment
                    .builder()
                    .tax(tax)
                    .insurance(insurance)
                    .item(item)
                    .transportation(transportation)
                    .country(country)
                    .build();
            PaymentBl.getPaymentBl().save(payment);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void edit(int id, float tax, float insurance, Item item, Transportation transportation, Country country) {
        try {

            Payment payment = Payment
                    .builder()
                    .tax(tax)
                    .insurance(insurance)
                    .item(item)
                    .transportation(transportation)
                    .country(country)
                    .build();
            PaymentBl.getPaymentBl().edit(payment);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            Payment payment = new Payment();
            payment.setId(id);
            PaymentBl.getPaymentBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<Payment> paymentList = new ArrayList<>();
//            Payment payment = Payment
//                         .builder()
//                    .tax(tax)
//                    .insurance(insurance)
//                    .item(item)
//                    .transportation(transportation)
//                    .country(country)
//                    .build();
//            paymentList.add(payment);
//            PaymentBl.getPaymentBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return paymentList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<Payment> paymentList = new ArrayList<>();
//            Payment payment = Payment
//                        .builder()
//                    .tax(tax)
//                    .insurance(insurance)
//                    .item(item)
//                    .transportation(transportation)
//                    .country(country)
//                    .build();
//            payment.setId(id);
//            PaymentBl.getPaymentBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return paymentList.get(id);
//    }
}

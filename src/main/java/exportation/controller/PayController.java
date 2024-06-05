package exportation.controller;
import exportation.model.bl.PaymentBl;
import exportation.model.entity.Info;
import exportation.model.entity.Item;
import exportation.model.entity.Payment;
import exportation.model.entity.Transportation;

public class PayController {
    public static void save(long totalCost, float tax, float insurance, Item item, Transportation transportation, Info info) {
        try {

            Payment payment = Payment
                    .builder()
                    .totalCost(totalCost)
                    .tax(tax)
                    .insurance(insurance)
                    .item(item)
                    .transportation(transportation)
                    .info(info)
                    .build();
            PaymentBl.getPaymentBl().save(payment);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, long totalCost, float tax, float insurance, Item item, Transportation transportation, Info info) {
        try {

            Payment payment = Payment
                    .builder()
                    .id(id)
                    .totalCost(totalCost)
                    .tax(tax)
                    .insurance(insurance)
                    .item(item)
                    .transportation(transportation)
                    .info(info)
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
//                        .builder()
//                        .id(id)
//                        .totalCost(totalCost)
//                        .tax(tax)
//                        .insurance(insurance)
//                        .item(item)
//                        .transportation(transportation)
//                        .info(info)
//                        .build();
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
//                        .id(id)
//                        .totalCost(totalCost)
//                        .tax(tax)
//                        .insurance(insurance)
//                        .item(item)
//                        .transportation(transportation)
//                        .info(info)
//                        .build();
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
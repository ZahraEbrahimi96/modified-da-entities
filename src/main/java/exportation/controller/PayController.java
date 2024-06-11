package exportation.controller;

import exportation.model.bl.PaymentBl;
import exportation.model.entity.*;

public class PayController {
    //save
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

    //edit
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

    //remove
    public static void remove(int id) {
        try {
            Payment payment = new Payment();
            payment.setId(id);
            PaymentBl.getPaymentBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

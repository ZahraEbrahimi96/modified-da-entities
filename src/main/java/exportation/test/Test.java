package exportation.test;

import exportation.model.bl.*;
import exportation.model.entity.*;

public class Test {
    public static void main(String[] args) throws Exception {

        //check Country
        Country country1 =
                Country
                        .builder()
                        .name("Iran")
                        .build();

        Country country2 =
                Country
                        .builder()
                        .name("Iraq")
                        .build();

        Country country3 =
                Country
                        .builder()
                        .name("UAE")
                        .build();

        CountryBl.getCountryBl().save(country1);
        CountryBl.getCountryBl().save(country2);
        CountryBl.getCountryBl().save(country3);


        //check Company
        Company company =
                Company
                        .builder()
                        .name("Iran Co.")
                        .country(country1)
                        .build();

        Company company2 =
                Company
                        .builder()
                        .name("Iraq Co.")
                        .country(country2)
                        .build();

        Company company3 =
                Company
                        .builder()
                        .name("UAE Co.")
                        .country(country3)
                        .build();
        CompanyBl.getCompanyBl().save(company);
        CompanyBl.getCompanyBl().save(company2);
        CompanyBl.getCompanyBl().save(company3);


        //check user
        User user =
                User
                        .builder()
                        .username("mamad")
                        .build();
        UserBl.getUserBl().save(user);

        //check Person
        Person person =
                Person
                        .builder()
                        .user(user)
                        .build();
        PersonBl.getPersonBl().save(person);


        //check Trade
        Trade trade =
                Trade
                        .builder()
                        .person(person)
                        .build();
        TradeBl.getTradeBl().save(trade);


        //check Item
        Item item =
                Item
                        .builder()
                        .name("Mobile")
                        .build();
        ItemBl.getItemBl().save(item);


//        check Transportation
        Transportation transportation =
                Transportation
                        .builder()
                        .item(item)
                        .country(country1)
                        .build();
        TransportationBl.getTransportationBl().save(transportation);


//check Payment
        Payment payment =
                Payment
                        .builder()
                        .item(item)
                        .transportation(transportation)
                        .build();
        PaymentBl.getPaymentBl().save(payment);
        System.out.println(Payment.totalCost(5, 35, 72, 72, 3450));


//check ExportTracing
        ExportTracing exportTracing =
                ExportTracing
                        .builder()
                        .trade(trade)
                        .build();
        ExportTracingBl.getExportTracingBl().save(exportTracing);


//        exportation cost ???
    }
}

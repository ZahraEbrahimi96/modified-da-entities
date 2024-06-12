package exportation.test;

import exportation.model.bl.*;
import exportation.model.entity.*;

public class Test {
    public static void main(String[] args) throws Exception {

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



//
//        Company company1 =
//                Company
//                        .builder()
//                        .name("Iran Co.")
//                        .country(country1)
//                        .build();
//
//        Company company2 =
//                Company
//                        .builder()
//                        .name("Iraq Co.")
//                        .country(country2)
//                        .build();
//
//        Company company3 =
//                Company
//                        .builder()
//                        .name("UAE Co.")
//                        .country(country3)
//                        .build();
//
//        CompanyBl.getCompanyBl().save(company1);
//        CompanyBl.getCompanyBl().save(company2);
//        CompanyBl.getCompanyBl().save(company3);
//


//
//
//
//        User user =
//                User
//                .builder()
//                .username("mamad")
//                .build();
//
//        UserBl.getUserBl().save(user);
//
//
//
//
//
//
//        Person person =
//                Person
//                        .builder()
//                        .user(user)
//                        .build();
//
//        PersonBl.getPersonBl().save(person);



//
//        Trade trade =
//                Trade
//                        .builder()
//                        .person(person)
//                        .build();
//
//        TradeBl.getTradeBl().save(trade);
//
//
//        Item item =
//                Item
//                        .builder()
//                        .name("Mobile")
//                        .build();
//
//        ItemBl.getItemBl().save(item);
//
//        Transportation transportation =
//                Transportation
//                        .builder()
//                        .item(item)
//                        .country(country1)
//                        .build();
//
//        TransportationBl.getTransportationBl().save(transportation);
//
//        Payment payment =
//                Payment
//                        .builder()
////                        .item(item)
//                        .transportation(transportation)
//                        .build();
//
//        ExportTracing exportTracing =
//                ExportTracing
//                        .builder()
//                        .trade(trade)
//                        .build();
//
//        ExportTracingBl.getExportTracingBl().save(exportTracing);

//        exportation cost ???
    }
}

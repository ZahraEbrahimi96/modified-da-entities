package exportation.test;

import exportation.controller.PnController;
import exportation.model.bl.PersonBl;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Gender;

//package exportation.test;
//
//import exportation.model.bl.PersonBl;
//import exportation.model.entity.Person;
//import exportation.model.entity.enums.City;
//import exportation.model.entity.enums.Gender;
//
//import java.time.LocalDate;
//
public class PersonTest {
    public static void main(String[] args) throws Exception {

        PnController.save("ali","alipur","safsgdjt@jbyu.","2211336655","1234567899","modir","hivuheoismecsol",Gender.male);

//        System.out.println(PersonBl.getPersonBl().save(
//                Person.builder().name("ali").family("alipour").build()));


//        Person->Company>Item>Country>Company>Tax


////        System.out.println(PersonBl.getPersonBl().findAll());
////        System.out.println(PersonBl.getPersonBl().findById(3));
////        System.out.println(PersonBl.getPersonBl().findByFamily("alipour"));
////        System.out.println(PersonBl.getPersonBl().remove(3));
//        System.out.println(PersonBl.getPersonBl().edit(
//                Person.builder()
//                        .id(4)
//                        .name("aaa")
//                        .family("bbbb")
//                        .gender(Gender.Female)
//                        .city(City.Tabriz)
//                        .birthDate(LocalDate.now())
//                        .build()
//        ));
//
////                System.out.println(PersonBl.getPersonBl().save(
////                Person.builder()
////                        .name("reza")
////                        .family("rezaii")
////                        .gender(Gender.Male)
////                        .city(City.Tehran)
////                        .birthDate(LocalDate.now())
////                        .build()
////        ));
//
//
    }
}

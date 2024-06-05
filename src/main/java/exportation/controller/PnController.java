package exportation.controller;
import exportation.model.bl.PersonBl;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Gender;

import java.util.regex.Pattern;

public class PnController {
    public static void save(String name, String family, String email, String phoneNumber, String nationalId, String position, String address, Gender gender) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",name)&& Pattern.matches("^[a-zA-Z\\s]{2,30}$",family)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&&Pattern.matches("\\d{3}-\\d{6}-\\d{1}|\\d[10]", nationalId)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Person person = Person
                        .builder()
                        .name(name)
                        .family(family)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .nationalId(nationalId)
                        .position(position)
                        .address(address)
                        .gender(gender)
                        .build();
                PersonBl.getPersonBl().save(person);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String name, String family, String email, String phoneNumber, String nationalId, String position, String address, Gender gender) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",name)&& Pattern.matches("^[a-zA-Z\\s]{2,30}$",family)&& Pattern.matches("^[a-zA-Z\\s\\@]{200}$",email)&&Pattern.matches("\\d{3}-\\d{6}-\\d{1}|\\d[10]", nationalId)&& Pattern.matches("^[a-zA-Z\\s]{300}$",address)) {
                Person person = Person
                        .builder()
                        .id(id)
                        .name(name)
                        .family(family)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .nationalId(nationalId)
                        .position(position)
                        .address(address)
                        .gender(gender)
                        .build();

                PersonBl.getPersonBl().edit(person);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            Person person = new Person();
            person.setId(id);
            PersonBl.getPersonBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<Person> personList = new ArrayList<>();
//            Person person = Person
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .family(family)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .nationalId(nationalId)
//                        .position(position)
//                        .address(address)
//                        .gender(gender)
//                        .build();
//            personList.add(person);
//            PersonBl.getPersonBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return personList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<Person> personList = new ArrayList<>();
//            Person person = Person
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .family(family)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .nationalId(nationalId)
//                        .position(position)
//                        .address(address)
//                        .gender(gender)
//                        .build();
//            person.setId(id);
//            PersonBl.getPersonBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return personList.get(id);
//    }

//   public String findByFamily (String family) {
//        try {
//            List<Person> personList = new ArrayList<>();
//            Person person = Person
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .family(family)
//                        .email(email)
//                        .phoneNumber(phoneNumber)
//                        .nationalId(nationalId)
//                        .position(position)
//                        .address(address)
//                        .gender(gender)
//                        .build();
//            person.setId(id);
//            PersonBl.getPersonBl().findByFamily();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return personList.get(id);
//    }
}
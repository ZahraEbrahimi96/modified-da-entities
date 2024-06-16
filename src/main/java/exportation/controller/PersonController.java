package exportation.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField idTxt, nameTxt, familyTxt, nationalidTxt, emailTxt, phoneTxt, positionTxt;
    @FXML
    private RadioButton maleRbtn, femaleRbtn;
    @FXML
    private Button saveBtn,editBtn,removeBtn;
    @FXML
    private ToggleGroup genderToggle;

//
//    //save
//    public static String save(String name, String family, Gender gender, String nationalId, String phoneNumber, String email, String address, String position) {
//        try {
//            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[a-zA-Z\\s]{2,30}$", family) && Pattern.matches("^[a-zA-Z\\s\\@]{200}$", email) && Pattern.matches("\\d{3}-\\d{6}-\\d{1}|\\d[10]", nationalId) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
//                Person person = Person
//                        .builder()
//                        .name(name)
//                        .family(family)
//                        .gender(gender)
//                        .nationalId(nationalId)
//                        .phoneNumber(phoneNumber)
//                        .email(email)
//                        .address(address)
//                        .position(position)
//                        .build();
//                PersonBl.getPersonBl().save(person);
//            } else {
//                System.out.println("Invalid Data");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return name;
//    }
//
//    //edit
//    public static void edit(int id, String name, String family, Gender gender, String nationalId, String phoneNumber, String email, String address, String position) {
//        try {
//            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[a-zA-Z\\s]{2,30}$", family) && Pattern.matches("^[a-zA-Z\\s@]{200}$", email) && Pattern.matches("\\d{3}-\\d{6}-\\d|\\d[10]", nationalId) && Pattern.matches("^[a-zA-Z\\s]{300}$", address)) {
//                Person person = Person
//                        .builder()
//                        .id(id)
//                        .name(name)
//                        .family(family)
//                        .gender(gender)
//                        .nationalId(nationalId)
//                        .phoneNumber(phoneNumber)
//                        .email(email)
//                        .address(address)
//                        .position(position)
//                        .build();
//
//                PersonBl.getPersonBl().edit(person);
//            } else {
//                System.out.println("Invalid Data");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    //remove
//    public static void remove(int id) {
//        try {
//            Person person = new Person();
//            person.setId(id);
//            PersonBl.getPersonBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}

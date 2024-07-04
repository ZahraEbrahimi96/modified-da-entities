package exportation.controller;
import exportation.model.bl.PersonBl;
import exportation.model.entity.Person;
import exportation.model.entity.enums.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Log4j
public class PersonController implements Initializable {

    @FXML
    private TextField idTxt, nameTxt, familyTxt, nationalidTxt, emailTxt, phoneTxt, positionTxt, findbyidTxt, findbyfamilyTxt;
    @FXML
    private RadioButton maleRbtn, femaleRbtn;
    @FXML
    private Button saveBtn, editBtn, removeBtn, findallBtn;
    @FXML
    private ToggleGroup genderToggle;
    @FXML
    private TextArea addressTxtarea;
    @FXML
    private TableView <Person> personTbl;
    @FXML
    private TableColumn <Person, Integer > idCln;
    @FXML
    private TableColumn <Person, String >nameCln,familyCln,nationalCln,emailCln,phoneCln,position,addressCln;
    @FXML
    private TableColumn <Person, Gender>genderCln;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        saveBtn.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();

                Person person = Person
                        .builder()
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .gender(Gender.valueOf(gender.getText()))
                        .nationalId(nationalidTxt.getText())
                        .phoneNumber(phoneTxt.getText())
                        .email(emailTxt.getText())
                        .address(addressTxtarea.getText())
                        .position(positionTxt.getText())
                        .build();
                PersonBl.getPersonBl().save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Saved\n" + person);
                alert.show();
                resetForm();
                log.info("Person Saved " + person);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();

                Person person = Person
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .gender(Gender.valueOf(gender.getText()))
                        .nationalId(nationalidTxt.getText())
                        .phoneNumber(phoneTxt.getText())
                        .email(emailTxt.getText())
                        .address(addressTxtarea.getText())
                        .position(positionTxt.getText())
                        .build();
                PersonBl.getPersonBl().edit(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Edited\n" + person);
                alert.show();
                resetForm();
                log.info("Person Edited " + person);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                PersonBl.getPersonBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Removed\n" + idTxt.getText());
                alert.show();
                log.info("Person Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        findbyidTxt.setOnKeyReleased((event) -> {
            try {

                showDataOnTable((List<Person>) PersonBl.getPersonBl().findById(Integer.parseInt(findbyidTxt.getText())));
                log.info("Person Searched By Id : " + findbyidTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error : " + e.getMessage());
            }
        });

        findbyfamilyTxt.setOnKeyReleased((event) -> {
            try {

                showDataOnTable((List<Person>) PersonBl.getPersonBl().findByFamily(findbyfamilyTxt.getText()));
                log.info("Person Searched By Id : " + findbyfamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error : " + e.getMessage());
            }
        });

        findallBtn.setOnAction((event) -> {
            try {
                showDataOnTable(PersonBl.getPersonBl().findAll());
                log.info("ALL Person Searched : " + findallBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Person Error : " + e.getMessage());
            }
        });

        personTbl.setOnMouseClicked((event) -> {
            Person person = personTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(person.getId()));
            nameTxt.setText(person.getName());
            familyTxt.setText(person.getName());
            if (person.getGender().equals(Gender.male)) {
                maleRbtn.setSelected(true);
            } else {
                femaleRbtn.setSelected(true);
            }
            nationalidTxt.setText((person.getNationalId()));
            phoneTxt.setText(person.getPhoneNumber());
            emailTxt.setText(person.getEmail());
            addressTxtarea.setText(person.getAddress());
            positionTxt.setText(person.getPosition());
        });
    }

    private void showDataOnTable(List<Person> personList) {
        ObservableList<Person> observableList = FXCollections.observableList(personList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyCln.setCellValueFactory(new PropertyValueFactory<>("family"));
        nationalCln.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        emailCln.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCln.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addressCln.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderCln.setCellValueFactory(new PropertyValueFactory<>("gender"));
        personTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();
        emailTxt.clear();
        maleRbtn.setSelected(true);
        phoneTxt.clear();
        addressTxtarea.clear();
        nationalidTxt.clear();
        positionTxt.clear();
        showDataOnTable(PersonBl.getPersonBl().findAll());

    }
}

package exportation.controller;

import exportation.model.bl.ItemBl;
import exportation.model.bl.TradeBl;
import exportation.model.bl.TransportationBl;
import exportation.model.entity.*;
import exportation.model.entity.enums.Brand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class TransportationController implements Initializable {

    @FXML
    private TextField idTxt,coIdTxt,productTxt,countryTx,directionTxt,freightTxt,expoIdTxt,tradeIdTxt,fByIdTxt;
    @FXML
    private Button saveBtn,editBtn,removeBtn,countryBtn,tradeBtn,exportationBtn,findAllBtn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Transportation>transportTbl;
    @FXML
    private TableColumn<Transportation,Integer>idCln;
    @FXML
    private TableColumn<Transportation,String>directionCln;
    @FXML
    private TableColumn<Transportation,Float>freightCln;
    @FXML
    private TableColumn<Transportation,Trade>tradeCln;
    @FXML
    private TableColumn<Transportation,Item>productCln;
    @FXML
    private TableColumn<Transportation,Company>companyCln;
    @FXML
    private TableColumn<Transportation,Country>countryCln;
    @FXML
    private TableColumn<Transportation,LocalDate>dateCln;
    @FXML
    private TableColumn<Transportation,ExportTracing>expoCln;

//    //save
//    public static void save(String direction, float freight, Item item, Company company, Country country, LocalDateTime dateTime) {
//        try {
//            Transportation transportation = Transportation
//                    .builder()
//                    .direction(direction)
//                    .freight(freight)
//                    .item(item)
//                    .company(company)
//                    .country(country)
//                    .date(LocalDate.ofYearDay(2024, dateTime.getYear()))
//                    .build();
//            TransportationBl.getTransportationBl().save(transportation);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    //edit
//    public static void edit(int id, String direction, float freight, Item item, Company company, Country country, LocalDateTime dateTime) {
//        try {
//
//            Transportation transportation = Transportation
//                    .builder()
//                    .id(id)
//                    .direction(direction)
//                    .freight(freight)
//                    .item(item)
//                    .company(company)
//                    .country(country)
//                    .date(LocalDate.ofYearDay(2024, dateTime.getYear()))
//                    .build();
//
//            TransportationBl.getTransportationBl().edit(transportation);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    //remove
//    public static void remove(int id) {
//        try {
//            Transportation transportation = new Transportation();
//            transportation.setId(id);
//            TransportationBl.getTransportationBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        countryBtn.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/country.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        tradeBtn.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/trade.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        exportationBtn.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/export.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        saveBtn.setOnAction(event -> {
            try {
            Transportation transportation = Transportation
                    .builder()
                    .direction(directionTxt.getText())
                    .freight(Float.parseFloat(freightTxt.getText()))
                    .item(Item.builder().id(Integer.parseInt(productTxt.getText())).build())
                    .company(Company.builder().id(Integer.parseInt(coIdTxt.getText())).build())
                    .country(Country.builder().id(Integer.parseInt(countryTx.getText())).build())
                    .date(datePicker.getValue())
                    .build();
            TransportationBl.getTransportationBl().save(transportation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Transportation Saved\n" + transportation);
                alert.show();
                resetForm();
                log.info("Transportation Saved " + transportation);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }

        });

        editBtn.setOnAction (event -> {
            try {

            Transportation transportation = Transportation
                    .builder()
                    .id(Integer.parseInt(idTxt.getText()))
                    .direction(directionTxt.getText())
                    .freight(Float.parseFloat(freightTxt.getText()))
                    .item(Item.builder().id(Integer.parseInt(productTxt.getText())).build())
                    .company(Company.builder().id(Integer.parseInt(coIdTxt.getText())).build())
                    .country(Country.builder().id(Integer.parseInt(countryTx.getText())).build())
                    .date(datePicker.getValue())
                    .build();
            TransportationBl.getTransportationBl().edit(transportation);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "  Transportation Edited\n" + transportation);
                alert.show();
                resetForm();
                log.info(" Transportation Edited" + transportation);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "  Transportation Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }

        });

        removeBtn.setOnAction(event -> {
            try {
                TransportationBl.getTransportationBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Transportation Removed\n" + idTxt.getText());
                alert.show();
                log.info("Transportation Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        findAllBtn.setOnAction((event) -> {
            try {
                showDataOnTable(  TransportationBl.getTransportationBl().findAll());
                log.info("All Transportation Searched : " + findAllBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation\n" + e.getMessage());
                alert.show();
                log.error("Find All Error : " + e.getMessage());
            }
        });

        fByIdTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable((List<Transportation>) TransportationBl.getTransportationBl().findById(Integer.parseInt(fByIdTxt.getText())));
                log.info("Transportation Searched By Id : " + fByIdTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Transportation\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error : " + e.getMessage());
            }
        });

        transportTbl.setOnMouseClicked((event) -> {
            Transportation transportation = transportTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(transportation.getId()));
            directionTxt.setText(transportation.getDirection());
            freightTxt.setText(String.valueOf(transportation.getFreight()));
            productTxt.setText(String.valueOf(transportation.getItem().getId()));
            coIdTxt.setText(String.valueOf(transportation.getCompany().getId()));
            countryTx.setText(String.valueOf(transportation.getCountry().getId()));
            datePicker.setValue(LocalDate.now());
        });
    }
    private void showDataOnTable (List <Transportation> transportationList) {

        ObservableList<Transportation> observableList = FXCollections.observableList(transportationList);
        idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
        directionCln.setCellValueFactory(new PropertyValueFactory<>("direction"));
        freightCln.setCellValueFactory(new PropertyValueFactory<>("freight"));
        tradeCln.setCellValueFactory(new PropertyValueFactory<>("trade"));
        productCln.setCellValueFactory(new PropertyValueFactory<>("item"));
        companyCln.setCellValueFactory(new PropertyValueFactory<>("company"));
        countryCln.setCellValueFactory(new PropertyValueFactory<>("country"));
        dateCln.setCellValueFactory(new PropertyValueFactory<>("LocalDate"));
        expoCln.setCellValueFactory(new PropertyValueFactory<>("exportTracing"));
        transportTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        directionTxt.clear();
        freightTxt.clear();
        productTxt.clear();
        coIdTxt.clear();
        countryTx.clear();
        datePicker.setValue(null);
        showDataOnTable(TransportationBl.getTransportationBl().findAll());
    }
}

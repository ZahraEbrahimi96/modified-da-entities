package exportation.controller;
import exportation.model.bl.CountryBl;
import exportation.model.entity.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class mainController implements Initializable {

    @FXML
    private MenuItem closeMenuItem,tradeMenuItem,TargetMenuItem,exportMenuItem,PriceMenuItem,transMenuItem,midMenuItem,CompanyMenuItem,batteryMenuItem,Acount;

    @FXML
    private Tab aboutTab,countryTab,priceTab;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, Integer> idClmn,tariffClmn;

    @FXML
    private TableColumn<Country, Long> ImportClmn, popClmn, carClmn ;

    @FXML
    private TableColumn <Country, String> nameClmn, phcodeClmn, neighberClmn;

    @FXML
    private TextField fByIdTxt,fByNameTxt,taxTxt,insureTxt,costTxt,freightTxt,tariffTxt,palletTxt;

    @FXML
    private TreeView cifTree,totalTree;

    @FXML
    private  Button findBtn,totalBtn,cifBtn,newBtn1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        closeMenuItem.setOnAction((event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("App Closed");
        }));

        tradeMenuItem.setOnAction(event -> {
            try {
                        FXMLLoader.load(getClass().getResource("trade.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        TargetMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("country.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        exportMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("export.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });
        PriceMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("payment.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        transMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("transportation.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        midMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("person.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        CompanyMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("company.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        batteryMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("Item.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

// aboutTab..setOnAction(event -> {}
// countryTab
// priceTab

        fByIdTxt.setOnKeyReleased((event) -> {
            try {

                showDataOnTable(CountryBl.getCountryBl().findById(Integer.parseInt(fByIdTxt.getText())));
                log.info("Country Searched By Id : " + fByIdTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                alert.show();
                log.error("Find By Id Error : " + e.getMessage());
            }
        });


        fByNameTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable((Country) CountryBl.getCountryBl().findByName(fByNameTxt.getText()));
                log.info("Country Searched By Name : " + fByNameTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Persons\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error : " + e.getMessage());
            }
        });

        findBtn.setOnAction((event) -> {
            try {
                showDataOnTable((Country) CountryBl.getCountryBl().findAll());
                log.info("ALL Country Searched : " + findBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Persons\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Country Error : " + e.getMessage());
            }
        });

//        totalBtn.setOnAction((event) ->{
//            try {
//                Payment payment= Payment
//                        .builder()
//                        .tax(Float.parseFloat(taxTxt.getText()))
//                        .insurance(Float.parseFloat(insureTxt.getText()))
//                        .item(Item.builder().cost(Float.parseFloat(costTxt.getText())).build())
//                        .transportation(Transportation.builder().freight(Float.parseFloat(freightTxt.getText())).build())
//                        .company(Company.builder().country(Country.builder().tariff(Integer.parseInt(tariffTxt.getText())).build()).build())
//                        .item(Item.builder().palletCapacity(Integer.parseInt(palletTxt.getText())).build())
//                        .build();
//
//            }
//    } );

//
//        cifBtn.setOnAction((event) ->{
////            try {
////                Payment payment= Payment
////                        .builder()
////                        .tax(Float.parseFloat(taxTxt.getText()))
////                        .insurance(Float.parseFloat(insureTxt.getText()))
////                        .item(Item.builder().cost(Float.parseFloat(costTxt.getText())).build())
////                        .transportation(Transportation.builder().freight(Float.parseFloat(freightTxt.getText())).build())
////                        .company(Company.builder().country(Country.builder().tariff(Integer.parseInt(tariffTxt.getText())).build()).build())
////                        .item(Item.builder().palletCapacity(Integer.parseInt(palletTxt.getText())).build())
////                        .build();
////
////            }
////    } );

        newBtn1.setOnAction((event) ->{
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });





    private void showDataOnTable(List <Country> countryList) {
        ObservableList<Country> observableList = FXCollections.observableList(countryList);

        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tariffClmn.setCellValueFactory(new PropertyValueFactory<>("tariff"));
        phcodeClmn.setCellValueFactory(new PropertyValueFactory<>("phone code"));
        ImportClmn.setCellValueFactory(new PropertyValueFactory<>("import rate"));
        popClmn.setCellValueFactory(new PropertyValueFactory<>("population"));
        carClmn.setCellValueFactory(new PropertyValueFactory<>("car rate"));
        neighberClmn.setCellValueFactory(new PropertyValueFactory<>("neighbor"));
        countryTable.setItems(observableList);
    }

        private void resetForm()throw Exception{
        taxTxt.clear();
        insureTxt.clear();
        costTxt.clear();
        freightTxt.clear();
        tariffTxt.clear();
        palletTxt.clear();
    }

    }

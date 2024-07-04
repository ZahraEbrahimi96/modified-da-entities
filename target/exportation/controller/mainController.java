package exportation.controller;
import exportation.model.bl.CountryBl;
import exportation.model.bl.PaymentBl;
import exportation.model.entity.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;
import static java.lang.Float.valueOf;


@Log4j
public class mainController implements Initializable {

    @FXML
    private MenuItem closeMenuItem, tradeMenuItem, TargetMenuItem, exportMenuItem, PriceMenuItem, transMenuItem, midMenuItem, CompanyMenuItem, batteryMenuItem, Acount;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, Integer> idClmn, tariffClmn;

    @FXML
    private TableColumn<Country, Long> ImportClmn, popClmn, carClmn;

    @FXML
    private TableColumn<Country, String> nameClmn, phcodeClmn, neighberClmn;

    @FXML
    private TextField fByIdTxt, fByNameTxt, taxTxt, insureTxt, costTxt, freightTxt, tariffTxt, palletTxt, totalTxt, cifTxt;


    @FXML
    private Button findBtn, totalBtn, cifBtn, newBtn1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("App Started");

        try {
            resetForm1();
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
                FXMLLoader.load(getClass().getResource("view/trade.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        TargetMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/country.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        exportMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/export.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        PriceMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/payment.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        transMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/transportation.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        midMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/person.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        CompanyMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/company.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        batteryMenuItem.setOnAction(event -> {
            try {
                FXMLLoader.load(getClass().getResource("view/Item.fxml"));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });



        fByIdTxt.setOnKeyReleased((event) -> {
                    try {

                        showDataOnTable((List<Country>) CountryBl.getCountryBl().findById(Integer.parseInt(fByIdTxt.getText())));
                        log.info("Country Searched By Id : " + fByIdTxt.getText());
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                        alert.show();
                        log.error("Find By Id Error : " + e.getMessage());
                    }
        });


        fByNameTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(CountryBl.getCountryBl().findByName(fByNameTxt.getText()));
                log.info("Country Searched By Name : " + fByNameTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                alert.show();
                log.error("Find By Name Error : " + e.getMessage());
            }
        });

        findBtn.setOnAction((event) -> {
            try {
                showDataOnTable(CountryBl.getCountryBl().findAll());
                log.info("ALL Country Searched : " + findBtn);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Country\n" + e.getMessage());
                alert.show();
                log.error("Find ALL Country Error : " + e.getMessage());
            }
        });


        totalBtn.setOnAction((event) ->{
            try {

                float tax = Float.parseFloat(taxTxt.getText());
                float insurance = Float.parseFloat(insureTxt.getText());
                float cost = Float.parseFloat(costTxt.getText());
                float freight = Float.parseFloat(freightTxt.getText());
                int tariff = Integer.parseInt(tariffTxt.getText());
                int pallet = Integer.parseInt(palletTxt.getText());

                long total = Payment.totalCost(tariff,cost,tax,pallet,insurance,freight);

                totalTxt.setText(String.valueOf(total));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Calculation Done\n" + total);
                alert.show();
                resetForm1();
                log.info("Calculation Done" + total);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Calculation Error\n" + e.getMessage());
                alert.show();
                log.error("Calculation Error : " + e.getMessage());
            }
    } );


        cifBtn.setOnAction((event) ->{
            try {
                float tax = Float.parseFloat(taxTxt.getText());
                float insurance = Float.parseFloat(insureTxt.getText());
                float cost = Float.parseFloat(costTxt.getText());
                float freight = Float.parseFloat(freightTxt.getText());
                int tariff = Integer.parseInt(tariffTxt.getText());
                int pallet = Integer.parseInt(palletTxt.getText());

                long cif = Payment.cif(cost,pallet,insurance,freight);

                cifTxt.setText(String.valueOf(cif));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Calculation Done\n" + cif);
                alert.show();
                resetForm1();
                log.info("Calculation Done" + cif);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Calculation Error\n" + e.getMessage());
                alert.show();
                log.error("Calculation Error : " + e.getMessage());
            }
    } );


        newBtn1.setOnAction((event) -> {
            try {
                resetForm1();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

    }
        private void showDataOnTable (List < Country > countryList) {
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

        private void resetForm1 () throws Exception {
            taxTxt.clear();
            insureTxt.clear();
            costTxt.clear();
            freightTxt.clear();
            tariffTxt.clear();
            palletTxt.clear();
        }


}

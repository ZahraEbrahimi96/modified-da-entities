//package exportation.controller;
//import exportation.model.bl.TradeBl;
//import exportation.model.entity.Person;
//import exportation.model.entity.Trade;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import java.net.URL;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.regex.Pattern;
//
//public class TradeController implements Initializable {
//
//    @FXML
//    private TextField idTxt,cITxt,statusTxt, agreeTxt,nCTxt,fByIdTxt;
//
//    @FXML
//    private DatePicker tradeDate;
//
//    @FXML
//    private Button saveBtn,editBtn,removeBtn,exportBtn,transportBtn,cInfoBtn,findAllBtn;
//
//    @FXML
//    private TableView<Trade> tradeTbl;
//
//    @FXML
//    private TableColumn <Trade, String> dateCln,customerCln,statusCln,agreeCln,nContractCln;
//
//    @FXML
//    private TableColumn <Trade, Integer> idCln;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        log.info("App Started");
//
//        try {
//            resetForm();
//        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
//            alert.show();
//        }
//
//        saveBtn.setOnAction(event -> {
//            try {
//
//                Trade trade = Trade
//                        .builder()
//                        .status(statusTxt.getText())
//                        .contract(nCTxt.getText())
//                        .agreement(agreeTxt.getText())
//                        .person(Person.builder().id(Integer.parseInt(cITxt.getText())).build())
//                        .date(tradeDate.getValue())
//                        .build();
//                TradeBl.getTradeBl().save(trade);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Trade Saved\n" + trade);
//                alert.show();
//                resetForm();
//                log.info("Trade Saved" + trade);
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Trade Save Error\n" + e.getMessage());
//                alert.show();
//                log.error("Save Error : " + e.getMessage());
//            }
//
//        });
//
//        editBtn.setOnAction (event -> {
//            try {
//
//                Trade trade = Trade
//                        .builder()
//                        .id(Integer.parseInt(idTxt.getText()))
//                        .status(statusTxt.getText())
//                        .contract(nCTxt.getText())
//                        .agreement(agreeTxt.getText())
//                        .person(Person.builder().id(Integer.parseInt(cITxt.getText())).build())
//                        .date(tradeDate.getValue())
//                        .build();
//                TradeBl.getTradeBl().edit(trade);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Trade Edited\n" + trade);
//                alert.show();
//                resetForm();
//                log.info("Trade Edited" + trade);
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Trade Edit Error\n" + e.getMessage());
//                alert.show();
//                log.error("Edit Error : " + e.getMessage());
//            }
//
//        });
//
//        removeBtn.setOnAction(event -> {
//            try {
//                TradeBl.getTradeBl().remove(Integer.parseInt(idTxt.getText()));
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Trade Removed\n" + idTxt.getText());
//                alert.show();
//                log.info("Trade Removed " + idTxt.getText());
//                resetForm();
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Trade Remove Error\n" + e.getMessage());
//                alert.show();
//                log.error("Remove Error : " + e.getMessage());
//            }
//        });
//
//        exportBtn.setOnAction(event -> {
//            try {
//                FXMLLoader.load(getClass().getResource("export.fxml"));
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
//                alert.show();
//            }
//        });
//
//        transportBtn.setOnAction(event -> {
//            try {
//                FXMLLoader.load(getClass().getResource("transportation.fxml"));
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
//                alert.show();
//            }
//        });
//
//        cInfoBtn.setOnAction(event -> {
//            try {
//                FXMLLoader.load(getClass().getResource("person.fxml"));
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
//                alert.show();
//            }
//        });
//
//        fByIdTxt.setOnKeyReleased((event) -> {
//            try {
//                showDataOnTable(TradeBl.getTradeBl().findById(Integer.parseInt(fByIdTxt.getText())));
//                log.info("Trade Searched By Id : " + fByIdTxt.getText());
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Trade\n" + e.getMessage());
//                alert.show();
//                log.error("Find By Id Error : " + e.getMessage());
//            }
//        });
//
//        findAllBtn.setOnAction((event) -> {
//            try {
//                showDataOnTable(TradeBl.getTradeBl().findAll());
//                log.info("All Trade Searched : " + findAllBtn);
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, " Trade\n" + e.getMessage());
//                alert.show();
//                log.error("Find All Error : " + e.getMessage());
//            }
//            });
//
//        tradeTbl.setOnMouseClicked((event) -> {
//            Trade trade =tradeTbl.getSelectionModel().getSelectedItem();
//            idTxt.setText(String.valueOf(trade.getId()));
//            cITxt.setText(String.valueOf(trade.getPerson().getId()));
//            statusTxt.setText(trade.getStatus());
//            agreeTxt.setText(trade.getAgreement());
//            nCTxt.setText(trade.getContract());
//        });
//
//        private void showDataOnTable (List <Trade> tradeList) {
//
//            ObservableList<Trade> observableList = FXCollections.observableList(tradeList);
//            idCln.setCellValueFactory(new PropertyValueFactory<>("id"));
//            dateCln.setCellValueFactory(new PropertyValueFactory<>("date"));
//            customerCln.setCellValueFactory(new PropertyValueFactory<>("person"));
//            statusCln.setCellValueFactory(new PropertyValueFactory<>("status"));
//            agreeCln.setCellValueFactory(new PropertyValueFactory<>("agreement"));
//            nContractCln.setCellValueFactory(new PropertyValueFactory<>("contract"));
//            tradeTbl.setItems(observableList);
//        }
//
//        private void resetForm() throws Exception {
//            idTxt.clear();
//            statusTxt.clear();
//            nCTxt.clear();
//            agreeTxt.clear();
//            cITxt.clear();
//            tradeDate.setValue(null);
//            showDataOnTable(TradeBl.getTradeBl().findAll());
//        }
//
//    }
//}

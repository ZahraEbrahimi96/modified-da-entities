package exportation.controller;

import exportation.model.bl.ExportTracingBl;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ExportTracingController implements Initializable {

    @FXML
    private TextField idTxt,transIdText,tradeTxt,fById;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton loadN,loadY,payN,payY,checkN,checkY;

    @FXML
    private Button saveBtn,editBtn,removeBtn,findAllBtn,TradeBtn,transBtn,payBtn;

    @FXML
    private TableView < ExportTracing> exportTbl;

    @FXML
    private TableColumn < ExportTracing, Integer> idCln,tradeCln,transportCln;

    @FXML
    private TableColumn < ExportTracing, Boolean> loadingCln,payCln,checkCln;

    @FXML
    private TableColumn < ExportTracing, LocalDate> dateCln;

    //save
    public static void save(boolean loadingStatus, boolean prePayment, boolean checkout, Trade trade, LocalDateTime dateTime) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .trade(trade)
                    .dateTime(dateTime)
                    .build();
            ExportTracingBl.getExportTracingBl().save(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, boolean loadingStatus, boolean prePayment, boolean checkout, Trade trade, LocalDateTime dateTime) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .trade(trade)
                    .dateTime(dateTime)
                    .build();
            ExportTracingBl.getExportTracingBl().edit(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            ExportTracing exportTracing = new ExportTracing();
            exportTracing.setId(id);
            ExportTracingBl.getExportTracingBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

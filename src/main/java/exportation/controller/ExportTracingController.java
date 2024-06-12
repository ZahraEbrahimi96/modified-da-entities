package exportation.controller;

import exportation.model.bl.ExportTracingBl;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;

public class ExportTracingController {

    //save
    public static void save(boolean loadingStatus, boolean prePayment, boolean checkout, Trade trade) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .trade(trade)
                    .build();
            ExportTracingBl.getExportTracingBl().save(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, boolean loadingStatus, boolean prePayment, boolean checkout, Trade trade) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .trade(trade)
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
}

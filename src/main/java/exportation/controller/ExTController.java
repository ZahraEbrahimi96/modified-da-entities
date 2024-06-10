package exportation.controller;

import exportation.model.bl.ExportTracingBl;
import exportation.model.entity.ExportTracing;
import exportation.model.entity.Trade;
import exportation.model.entity.Transportation;

public class ExTController {

    public static void save(boolean loadingStatus, boolean prePayment, boolean checkout, Transportation transportation, Trade trade) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .transportation(transportation)
                    .trade(trade)
                    .build();
            ExportTracingBl.getExportTracingBl().save(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, boolean loadingStatus, boolean prePayment, boolean checkout, Transportation transportation, Trade trade) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .transportation(transportation)
                    .trade(trade)
                    .build();
            ExportTracingBl.getExportTracingBl().edit(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            ExportTracing exportTracing = new ExportTracing();
            exportTracing.setId(id);
            ExportTracingBl.getExportTracingBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<ExportTracing> exportTracingList = new ArrayList<>();
//            ExportTracing exportTracing = ExportTracing
//                    .builder()
//                    .loadingStatus(loadingStatus)
//                    .prePayment(prePayment)
//                    .checkout(checkout)
//                    .transportation(transportation)
//                    .trade(trade)
//                    .build();
//            exportTracingList.add(exportTracing);
//            ExportTracingBl.getExportTracingBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return exportTracingList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<ExportTracing> exportTracingList = new ArrayList<>();
//            ExportTracing exportTracing = ExportTracing
//                   .builder()
//                    .loadingStatus(loadingStatus)
//                    .prePayment(prePayment)
//                    .checkout(checkout)
//                    .transportation(transportation)
//                    .trade(trade)
//                    .build();
//            exportTracing.setId(id);
//            ExportTracingBl.getExportTracingBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return exportTracingList.get(id);
//    }

}

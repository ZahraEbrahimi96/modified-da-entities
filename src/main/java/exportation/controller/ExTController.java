package exportation.controller;

import exportation.model.bl.ExportTracingBl;
import exportation.model.entity.ExportTracing;

public class ExTController {

    public static void save(boolean loadingStatus, boolean prePayment, boolean checkout, String waybill, String invoice) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .waybill(waybill)
                    .invoice(invoice)
                    .build();
            ExportTracingBl.getExportTracingBl().save(exportTracing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, boolean loadingStatus, boolean prePayment, boolean checkout, String waybill,String invoice) {
        try {
            ExportTracing exportTracing = ExportTracing
                    .builder()
                    .id(id)
                    .loadingStatus(loadingStatus)
                    .prePayment(prePayment)
                    .checkout(checkout)
                    .waybill(waybill)
                    .invoice(invoice)
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
//                    .id(id)
//                    .loadingStatus(loadingStatus)
//                    .prePayment(prePayment)
//                    .checkout(checkout)
//                    .waybill(waybill)
//                    .invoice(invoice)
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
//                    .builder()
//                    .id(id)
//                    .loadingStatus(loadingStatus)
//                    .prePayment(prePayment)
//                    .checkout(checkout)
//                    .waybill(waybill)
//                    .invoice(invoice)
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

//88
}



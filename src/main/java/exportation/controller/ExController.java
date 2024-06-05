package exportation.controller;
import exportation.model.bl.ExportsBl;
import exportation.model.entity.Country;
import exportation.model.entity.Exports;


public class ExController {
    public static void save (long hsCode, Country country, long quantity, long usdValue) {
        try {
                Exports exports = Exports
                        .builder()
                        .hsCode (hsCode)
                        .country(country)
                        .quantity(quantity)
                        .usdValue(usdValue)
                        .build();
                ExportsBl.getExportBl().save(exports);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, long hsCode, Country country, long quantity, long usdValue) {
        try {
            Exports exports = Exports
                    .builder()
                    .id(id)
                    .hsCode(hsCode)
                    .country(country)
                    .quantity(quantity)
                    .usdValue(usdValue)
                    .build();
            ExportsBl.getExportBl().edit(exports);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            Exports exports = new Exports();
//            Exports.setId(id);
//            ExportsBl.getExportBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<Exports> exportsList = new ArrayList<>();
//            Exports exports = Exports
//                    .builder()
//                    .id(id)
//                    .hsCode(hsCode)
//                    .country(country)
//                    .quantity(quantity)
//                    .usdValue(usdValue)
//                    .build();
//            exportsList.add(exports);
//             ExportsBl.getExportBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(exportsList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return exportsList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            Exports exports = Exports
//                     .builder()
//                    .id(id)
//                    .hsCode(hsCode)
//                    .country(country)
//                    .quantity(quantity)
//                    .usdValue(usdValue)
//                    .build();
//            exports.setId(id);
//
//           ExportsBl.getExportBl().findById();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
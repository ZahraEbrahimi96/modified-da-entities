package exportation.controller;


import exportation.model.bl.ImportsBl;
import exportation.model.entity.Country;
import exportation.model.entity.Imports;

public class ImController {
    public static void save (long hsCode, Country country, long quantity, long usdValue) {
        try {
            Imports imports = Imports
                    .builder()
                    .hsCode (hsCode)
                    .country(country)
                    .quantity(quantity)
                    .usdValue(usdValue)
                    .build();
            ImportsBl.getImportsBl().save(imports);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, long hsCode, Country country, long quantity, long usdValue) {
        try {
            Imports imports = Imports
                    .builder()
                    .id(id)
                    .hsCode(hsCode)
                    .country(country)
                    .quantity(quantity)
                    .usdValue(usdValue)
                    .build();
            ImportsBl.getImportsBl().edit(imports);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            Imports imports = new Imports();
//            Imports.setId(id);
//            ImportsBl.getImportsBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<Imports> importsList = new ArrayList<>();
//            Imports imports = Imports
//                    .builder()
//                    .id(id)
//                    .hsCode(hsCode)
//                    .country(country)
//                    .quantity(quantity)
//                    .usdValue(usdValue)
//                    .build();
//            importsList.add(imports);
//             ImportsBl.getImportsBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return importsList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            Imports importss = Imports
//                     .builder()
//                    .id(id)
//                    .hsCode(hsCode)
//                    .country(country)
//                    .quantity(quantity)
//                    .usdValue(usdValue)
//                    .build();
//            imports.setId(id);
//
//           ImportsBl.getImportsBl().findById();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}


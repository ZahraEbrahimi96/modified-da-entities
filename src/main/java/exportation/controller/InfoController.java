package exportation.controller;
import exportation.model.bl.InfoBl;
import exportation.model.entity.AccessPath;
import exportation.model.entity.Info;

public class InfoController {
    public static void save (long population, long carRate, String climate, AccessPath accessPath, String lifeExpectancy, String demand, String tariff) {
        try {
            Info info = Info
                    .builder()
                    .population (population)
                    .carRate(carRate)
                    .climate(climate)
                    .lifeExpectancy(lifeExpectancy)
                    .demand(demand)
                    //.accessPath(accessPath)
                    .tariff(tariff)
                    .build();
            InfoBl.getInfoBl().save(info);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id,long population, long carRate, String climate, String accessPath,String lifeExpectancy,String demand,String tariff) {
        try {
            Info info = Info
                    .builder()
                    .id(id)
                    .population (population)
                    .carRate(carRate)
                    .climate(climate)
                    //.accessPath(accessPath)
                    .lifeExpectancy(lifeExpectancy)
                    .demand(demand)
                    .tariff(tariff)
                    .build();
            InfoBl.getInfoBl().edit(info);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            Info info = new Info();
//
//            InfoBl.getInfoBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<Info> infoList = new ArrayList<>();
//            Info info = Info
//                    .builder()
//                    .id(id)
//                    .population (population)
//                    .carRate(carRate)
//                    .climate(climate)
//                    //.accessPath(accessPath)
//                    .lifeExpectancy(lifeExpectancy)
//                    .demand(demand)
//                    .tariff(tariff)
//                    .build();
//            infoList.add(info);
//             InfoBl.getInfoBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(infoList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return infoList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            Info info = Info
//                    .builder()
//                    .id(id)
//                    .population (population)
//                    .carRate(carRate)
//                    .climate(climate)
//                    //.accessPath(accessPath)
//                    .lifeExpectancy(lifeExpectancy)
//                    .demand(demand)
//                    .tariff(tariff)
//                    .build();
//
//           InfoBl.getInfoBl().findById();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}

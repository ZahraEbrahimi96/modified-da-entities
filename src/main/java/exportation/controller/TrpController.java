package exportation.controller;

import exportation.model.bl.TransportationBl;
import exportation.model.entity.Transportation;

public class TrpController {
    public static void save(String direction, String origin, float freight) {
        try {
                Transportation transportation = Transportation
                        .builder()
                        .direction(direction)
                        .origin(origin)
                        .freight(freight)
                        .build();
                TransportationBl.getTransportationBl().save(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String direction, String origin, float freight) {
        try {

                Transportation transportation = Transportation
                        .builder()
                        .id(id)
                        .direction(direction)
                        .origin(origin)
                        .freight(freight)
                        .build();

                TransportationBl.getTransportationBl().edit(transportation);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            Transportation transportation = new Transportation();
            transportation.setId(id);
            TransportationBl.getTransportationBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<Transportation> transportationList = new ArrayList<>();
//            Transportation transportation = Transportation
//                         ..builder()
//                        .id(id)
//                        .direction(direction)
//                        .origin(origin)
//                        .freight(freight)
//                        .build();
//            transportationList.add(transportation);
//            TransportationBl.getTransportationBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return transportationList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<Transportation> transportationList = new ArrayList<>();
//            Transportation transportation = Transportation
//                       .builder()
//                        .id(id)
//                        .direction(direction)
//                        .origin(origin)
//                        .freight(freight)
//                        .build();
//            transportation.setId(id);
//            TransportationBl.getTransportationBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return transportationList.get(id);
//    }

}

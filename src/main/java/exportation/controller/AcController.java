package exportation.controller;

import exportation.model.bl.AccessPathBl;
import exportation.model.entity.AccessPath;
import exportation.model.entity.enums.Navigation;
import exportation.model.entity.enums.PathType;

import java.util.regex.Pattern;


public class AcController {
    public static void save(String city, PathType pathType, float distance, Navigation navigation) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",city)) {
                AccessPath accessPath = AccessPath
                        .builder()
                        .city(city)
                        .pathType(pathType)
                        .distance(distance)
                        .navigation(navigation)
                        .build();
                AccessPathBl.getAccessPathBl().save(accessPath);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String city, PathType pathType, float distance, Navigation navigation) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",city)) {
            AccessPath accessPath = AccessPath
                    .builder()
                    .id(id)
                    .city(city)
                    .pathType(pathType)
                    .distance(distance)
                    .navigation(navigation)
                    .build();
            AccessPathBl.getAccessPathBl().edit(accessPath);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static void remove(int id) {
//        try {
//            AccessPath accessPath = new AccessPath();
//            AccessPathBl.getAccessPathBl().remove(id);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public static String findAll() {
//        try {
//
//            List<AccessPath> accessPathList = new ArrayList<>();
//            AccessPath accessPath = AccessPath
//                    .builder()
//                    .id(Integer.parseInt("id"))
//                    .city("city")
//                    .pathType(PathType.valueOf("pathType "))
//                    .distance(Float.parseFloat("distance"))
//                    .navigation(Navigation.valueOf("navigation"))
//                    .build();
//            accessPathList.add(accessPath);
//            AccessPathBl.getAccessPathBl().findAll();
//
//            Gson gson = new Gson();
//            return gson.toJson(accessPathList);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return accessPathList;
//    }
//
//
//    public String findById (int id) {
//        try {
//
//            AccessPath accessPath = AccessPath
//                    .builder()
//                    .id(Integer.parseInt("id"))
//                    .city("city")
//                    .pathType(PathType.valueOf("pathType "))
//                    .distance(Float.parseFloat("distance"))
//                    .navigation(Navigation.valueOf("navigation"))
//                    .build();
//            accessPath.setId(id);
//            AccessPathBl.getAccessPathBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}

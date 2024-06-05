package exportation.controller;

import exportation.model.bl.ItemBl;
import exportation.model.entity.Item;
import exportation.model.entity.enums.Brand;

import java.util.regex.Pattern;

public class ItemController {
    public static void save(int palletCapacity, String name, Brand brand, String model, String dimensionOfUnite, String dimensionOfPallet, long Hs_Code, float cost, float weightOfUnit, float weightOfPallet) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",name)&& Pattern.matches("^[H + \\d + L +\\d + w +\\d]$",dimensionOfUnite)&&Pattern.matches("^[H + \\d + L +\\d + w +\\d]$",dimensionOfPallet)) {
                Item item = Item
                        .builder()
                        .palletCapacity(palletCapacity)
                        .name(name)
                        .brand(brand)
                        .model(model)
                        .dimensionOfUnite(dimensionOfUnite)
                        .dimensionOfPallet(dimensionOfPallet)
                        .Hs_Code(Hs_Code)
                        .cost(cost)
                        .weightOfUnit(weightOfUnit)
                        .weightOfPallet(weightOfPallet)
                        .build();
                ItemBl.getItemBl().save(item);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, int palletCapacity, String name, Brand brand, String model, String dimensionOfUnite, String dimensionOfPallet, long Hs_Code, float cost, float weightOfUnit, float weightOfPallet) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",name)&& Pattern.matches("^[H + \\d + L +\\d + w +\\d]$",dimensionOfUnite)&&Pattern.matches("^[H + \\d + L +\\d + w +\\d]$",dimensionOfPallet)) {
                Item item = Item
                        .builder()
                        .id(id)
                        .palletCapacity(palletCapacity)
                        .name(name)
                        .brand(brand)
                        .model(model)
                        .dimensionOfUnite(dimensionOfUnite)
                        .dimensionOfPallet(dimensionOfPallet)
                        .Hs_Code(Hs_Code)
                        .cost(cost)
                        .weightOfUnit(weightOfUnit)
                        .weightOfPallet(weightOfPallet)
                        .build();
            ItemBl.getItemBl().edit(item);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            Item item = new Item();
            item.setId(id);
            ItemBl.getItemBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<Item> itemList = new ArrayList<>();
//            Item item = Item
//                   .builder()
//                        .palletCapacity(palletCapacity)
//                        .name(name)
//                        .brand(brand)
//                        .model(model)
//                        .dimensionOfUnite(dimensionOfUnite)
//                        .dimensionOfPallet(dimensionOfPallet)
//                        .Hs_Code(Hs_Code)
//                        .cost(cost)
//                        .weightOfUnit(weightOfUnit)
//                        .weightOfPallet(weightOfPallet)
//                        .build();
//            itemList.add(item);
//            ItemBl.getItemBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return itemList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<Item> itemList = new ArrayList<>();
//            Item item = Item
//                    .builder()
//                        .palletCapacity(palletCapacity)
//                        .name(name)
//                        .brand(brand)
//                        .model(model)
//                        .dimensionOfUnite(dimensionOfUnite)
//                        .dimensionOfPallet(dimensionOfPallet)
//                        .Hs_Code(Hs_Code)
//                        .cost(cost)
//                        .weightOfUnit(weightOfUnit)
//                        .weightOfPallet(weightOfPallet)
//                        .build();
//            item.setId(id);
//            ItemBl.getItemBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return itemList.get(id);
//    }

//   public String findByName (String name) {
//        try {
//            List<Item> itemList = new ArrayList<>();
//            Item item = Item
//                    .builder()
//                        .palletCapacity(palletCapacity)
//                        .name(name)
//                        .brand(brand)
//                        .model(model)
//                        .dimensionOfUnite(dimensionOfUnite)
//                        .dimensionOfPallet(dimensionOfPallet)
//                        .Hs_Code(Hs_Code)
//                        .cost(cost)
//                        .weightOfUnit(weightOfUnit)
//                        .weightOfPallet(weightOfPallet)
//                        .build();
//            item.setId(id);
//            ItemBl.getItemBl().findByName();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return itemList.get(id);
//    }
}

package exportation.controller;

import exportation.model.bl.ItemBl;
import exportation.model.entity.Item;
import exportation.model.entity.enums.Brand;

import java.util.regex.Pattern;

public class ItemController {

    //save
    public static void save(String name, Brand brand, String model, String dimensionOfUnite, String dimensionOfPallet, int palletCapacity, float cost, long Hs_Code, float weightOfUnit, float weightOfPallet) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[H + \\d + L +\\d + w +\\d]$", dimensionOfUnite) && Pattern.matches("^[H + \\d + L +\\d + w +\\d]$", dimensionOfPallet)) {
                Item item = Item
                        .builder()
                        .name(name)
                        .brand(brand)
                        .model(model)
                        .dimensionOfUnite(dimensionOfUnite)
                        .dimensionOfPallet(dimensionOfPallet)
                        .palletCapacity(palletCapacity)
                        .cost(cost)
                        .Hs_Code(Hs_Code)
                        .weightOfUnit(weightOfUnit)
                        .weightOfPallet(weightOfPallet)
                        .build();
                ItemBl.getItemBl().save(item);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String name, Brand brand, String model, String dimensionOfUnite, String dimensionOfPallet, int palletCapacity, float cost, long Hs_Code, float weightOfUnit, float weightOfPallet) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{5,40}$", name) && Pattern.matches("^[H + \\d + L +\\d + w +\\d]$", dimensionOfUnite) && Pattern.matches("^[H + \\d + L +\\d + w +\\d]$", dimensionOfPallet)) {
                Item item = Item
                        .builder()
                        .name(name)
                        .brand(brand)
                        .model(model)
                        .dimensionOfUnite(dimensionOfUnite)
                        .dimensionOfPallet(dimensionOfPallet)
                        .palletCapacity(palletCapacity)
                        .cost(cost)
                        .Hs_Code(Hs_Code)
                        .weightOfUnit(weightOfUnit)
                        .weightOfPallet(weightOfPallet)
                        .build();
                ItemBl.getItemBl().edit(item);
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            Item item = new Item();
            item.setId(id);
            ItemBl.getItemBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

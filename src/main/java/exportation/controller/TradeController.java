package exportation.controller;


import exportation.model.bl.TradeBl;
import exportation.model.entity.Person;
import exportation.model.entity.Trade;

import java.util.regex.Pattern;

public class TradeController {

    //save
    public static void save(String status, String correspondences, String contract, String agreement, Person person) {
        try {
            Trade trade = Trade
                    .builder()
                    .status(status)
                    .correspondences(correspondences)
                    .contract(contract)
                    .agreement(agreement)
                    .person(person)
                    .build();
            TradeBl.getTradeBl().save(trade);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, String status, String correspondences, String contract, String agreement, Person person) {
        try {
            Trade trade = Trade
                    .builder()
                    .status(status)
                    .correspondences(correspondences)
                    .contract(contract)
                    .agreement(agreement)
                    .person(person)
                    .build();

            TradeBl.getTradeBl().edit(trade);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //remove
    public static void remove(int id) {
        try {
            Trade trade = new Trade();
            trade.setId(id);
            TradeBl.getTradeBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

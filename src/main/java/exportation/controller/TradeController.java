package exportation.controller;


import exportation.model.bl.TradeBl;
import exportation.model.entity.Person;
import exportation.model.entity.Trade;

import java.util.regex.Pattern;

public class TradeController {

    //save
    public static void save(Person person, String status, String correspondences, String contract, String agreement) {
        try {
            Trade trade = Trade
                    .builder()
                    .person(person)
                    .status(status)
                    .correspondences(correspondences)
                    .contract(contract)
                    .agreement(agreement)
                    .build();
            TradeBl.getTradeBl().save(trade);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //edit
    public static void edit(int id, Person person, String status, String correspondences, String contract, String agreement, String invoice) {
        try {
            Trade trade = Trade
                    .builder()
                    .person(person)
                    .status(status)
                    .correspondences(correspondences)
                    .contract(contract)
                    .agreement(agreement)
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

package exportation.controller;

import exportation.model.bl.TradeBl;
import exportation.model.entity.Trade;

import java.util.regex.Pattern;

public class TradeController {
    public static void save(String client, String status, String correspondences, String contract, String agreement, String invoice) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",client)) {
                Trade trade = Trade
                        .builder()
                        .client(client)
                        .status(status)
                        .correspondences(correspondences)
                        .contract(contract)
                        .agreement(agreement)
                        .invoice(invoice)
                        .build();
                TradeBl.getTradeBl().save(trade);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void edit(int id, String client, String status, String correspondences, String contract, String agreement, String invoice) {
        try {
            if(Pattern.matches("^[a-zA-Z\\s]{5,40}$",client)) {
                Trade trade = Trade
                        .builder()
                        .id(id)
                        .client(client)
                        .status(status)
                        .correspondences(correspondences)
                        .contract(contract)
                        .agreement(agreement)
                        .invoice(invoice)
                        .build();

                TradeBl.getTradeBl().edit(trade);
            }else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void remove(int id) {
        try {
            Trade trade = new Trade();
            trade.setId(id);
            TradeBl.getTradeBl().remove(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public static String findAll() {
//        try {
//
//            List<Trade> tradeList = new ArrayList<>();
//            Trade trade = Trade
//                         .builder()
//                        .id(id)
//                        .client(client)
//                        .status(status)
//                        .correspondences(correspondences)
//                        .contract(contract)
//                        .agreement(agreement)
//                        .invoice(invoice)
//                        .build();
//            tradeList.add(trade);
//            TradeBl.getTradeBl().findAll();
//            Gson gson = new Gson();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return tradeList;
//    }
//
//
//    public String findById (int id) {
//        try {
//            List<Trade> tradeList = new ArrayList<>();
//            Trade trade = Trade
//                        .builder()
//                        .id(id)
//                        .client(client)
//                        .status(status)
//                        .correspondences(correspondences)
//                        .contract(contract)
//                        .agreement(agreement)
//                        .invoice(invoice)
//                        .build();
//            trade.setId(id);
//            TradeBl.getTradeBl().findById();
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//          return tradeList.get(id);
//    }

}

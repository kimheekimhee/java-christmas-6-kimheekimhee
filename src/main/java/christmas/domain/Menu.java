package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static final Map<String, Integer> menuPrices = new HashMap<>();

    static {
        menuPrices.put("양송이수프", 6000);
        menuPrices.put("타파스", 5500);
        menuPrices.put("시저샐러드", 8000);
        menuPrices.put("티본스테이크", 55000);
        menuPrices.put("바비큐립", 54000);
        menuPrices.put("해산물파스타", 35000);
        menuPrices.put("크리스마스파스타", 25000);
        menuPrices.put("초코케이크", 15000);
        menuPrices.put("아이스크림", 5000);
        menuPrices.put("제로콜라", 3000);
        menuPrices.put("레드와인", 60000);
        menuPrices.put("샴페인", 25000);
    }

    private Menu() {
    }

    public static int getPrice(String menuItem) {
        return menuPrices.getOrDefault(menuItem, 0);
    }

    public static boolean isValidMenu(String menuItem) {
        return menuPrices.containsKey(menuItem);
    }

    public static Map<String, Integer> getMenuPrices() {
        return Collections.unmodifiableMap(menuPrices);
    }
}

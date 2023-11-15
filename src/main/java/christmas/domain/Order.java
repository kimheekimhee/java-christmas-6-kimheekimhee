package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private final Map<String, Integer> items;
    private final Map<String, Integer> discountDetails;
    private int totalDiscountAmount;
    private String giftItem = "";

    public Order() {
        this.items = new LinkedHashMap<>();
        this.discountDetails = new LinkedHashMap<>();
        this.totalDiscountAmount = 0;
    }

    public void addItem(String item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void addGift(String gift) {
        this.giftItem = gift;
    }

    public String getGiftItem() {
        return giftItem;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addDiscountDetail(String discountType, int amount) {
        discountDetails.put(discountType, discountDetails.getOrDefault(discountType, 0) + amount);
        totalDiscountAmount += amount;
    }

    public Map<String, Integer> getDiscountDetails() {
        return discountDetails;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int calculateTotalCost() {
        int totalCost = items.entrySet().stream()
                .mapToInt(entry -> Menu.getPrice(entry.getKey()) * entry.getValue())
                .sum();

        if (!giftItem.isEmpty()) {
            totalCost -= Menu.getPrice(giftItem);
        }

        int discountAmountToApply = totalDiscountAmount;

        if (!giftItem.isEmpty()) {
            discountAmountToApply -= Menu.getPrice(giftItem);
        }

        return totalCost - discountAmountToApply + Menu.getPrice(giftItem);
    }

    @Override
    public String toString() {
        return items.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(", "));
    }
}

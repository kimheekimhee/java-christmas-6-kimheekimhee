package christmas.view;

import christmas.domain.*;

import java.text.DecimalFormat;

public class OutputView {

    public void displayOrderSummary(Order order) {
        System.out.println("\n12월 이벤트 혜택 미리 보기!");

        System.out.println("\n<주문 메뉴>");
        System.out.println(order);

        int totalCostBeforeDiscount = order.calculateTotalCost() + order.getTotalDiscountAmount();
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formatPrice(totalCostBeforeDiscount));

        System.out.println("\n<증정 메뉴>");
        String giftItem = order.getGiftItem();
        if (!giftItem.isEmpty()) {
            System.out.println(giftItem + " 1개");
        } else {
            System.out.println("없음");
        }

        System.out.println("\n<혜택 내역>");
        if (order.getDiscountDetails().isEmpty()) {
            System.out.println("없음");
        } else {
            order.getDiscountDetails().forEach((detail, amount) ->
                    System.out.println(detail + ": " + formatPrice(-amount)));
        }

        System.out.println("\n<총혜택 금액>");
        System.out.println(formatPrice(-order.getTotalDiscountAmount()));

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formatPrice(order.calculateTotalCost()));

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(BadgeCalculator.calculateBadge(order.getTotalDiscountAmount()));
    }

    private String formatPrice(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price) + "원";
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}

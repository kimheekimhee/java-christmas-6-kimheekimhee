package christmas.domain;

public class BadgeCalculator {
    // 배지 계산 로직
    public static String calculateBadge(int totalDiscount) {
        if (totalDiscount >= 20000) {
            return "산타";
        } else if (totalDiscount >= 10000) {
            return "트리";
        } else if (totalDiscount >= 5000) {
            return "별";
        } else {
            return "없음";
        }
    }
}

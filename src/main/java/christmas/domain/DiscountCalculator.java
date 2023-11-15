package christmas.domain;

import java.time.LocalDate;
import java.util.Set;
import java.time.temporal.ChronoUnit;

public class DiscountCalculator {

    private static final int DAILY_INCREASE = 100;
    private static final int CHRISTMAS_DAY = 25;
    private static final int WEEKEND_DISCOUNT = 2023;
    private static final int WEEKDAY_DISCOUNT = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;

    private DiscountCalculator() {
    }

    public static int calculateDiscount(Order order, LocalDate date) {
        return calculateChristmasDdayDiscount(date) + calculateWeekendDiscount(order, date) +
                calculateWeekdayDiscount(order, date) + calculateSpecialDiscount(date);
    }

    public static int calculateChristmasDdayDiscount(LocalDate date) {
        if (date.getMonthValue() != 12 || date.getDayOfMonth() > CHRISTMAS_DAY) {
            return 0;
        }
        return DAILY_INCREASE * (date.getDayOfMonth() - 1) + 1000;
    }

    public static int calculateWeekendDiscount(Order order, LocalDate date) {
        if (isWeekend(date)) {
            Set<String> mainDishes = Set.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타");
            return order.getItems().entrySet().stream()
                    .filter(entry -> mainDishes.contains(entry.getKey()))
                    .mapToInt(entry -> WEEKEND_DISCOUNT * entry.getValue())
                    .sum();
        }
        return 0;
    }

    public static int calculateWeekdayDiscount(Order order, LocalDate date) {
        if (!isWeekend(date)) {
            Set<String> desserts = Set.of("초코케이크", "아이스크림");
            return order.getItems().entrySet().stream()
                    .filter(entry -> desserts.contains(entry.getKey()))
                    .mapToInt(entry -> WEEKDAY_DISCOUNT * entry.getValue())
                    .sum();
        }
        return 0;
    }

    public static int calculateSpecialDiscount(LocalDate date) {
        Set<Integer> specialDiscountDays = Set.of(3, 10, 17, 24, 25, 31);
        return specialDiscountDays.contains(date.getDayOfMonth()) ? SPECIAL_DISCOUNT : 0;
    }

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() == 5 || date.getDayOfWeek().getValue() == 6;
    }
}
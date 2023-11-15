package christmas.service;

import christmas.domain.DiscountCalculator;
import christmas.domain.Order;
import christmas.domain.Menu;

import java.time.LocalDate;

public class EventPlannerService {

    public void applyDiscountsAndGifts(Order order, LocalDate date) {
        // 크리스마스 디데이 할인 적용
        int christmasDiscount = DiscountCalculator.calculateChristmasDdayDiscount(date);
        if (christmasDiscount > 0) {
            order.addDiscountDetail("크리스마스 디데이 할인", christmasDiscount);
        }

        // 평일 할인 적용
        int weekdayDiscount = DiscountCalculator.calculateWeekdayDiscount(order, date);
        if (weekdayDiscount > 0) {
            order.addDiscountDetail("평일 할인", weekdayDiscount);
        }

        // 주말 할인 적용
        int weekendDiscount = DiscountCalculator.calculateWeekendDiscount(order, date);
        if (weekendDiscount > 0) {
            order.addDiscountDetail("주말 할인", weekendDiscount);
        }

        // 특별 할인 적용
        int specialDiscount = DiscountCalculator.calculateSpecialDiscount(date);
        if (specialDiscount > 0) {
            order.addDiscountDetail("특별 할인", specialDiscount);
        }

        // 증정 이벤트 적용 (예시: 샴페인 증정)
        if (order.calculateTotalCost() >= 120000) {
            order.addGift("샴페인");
            order.addDiscountDetail("증정 이벤트", Menu.getPrice("샴페인"));
        }
    }
}

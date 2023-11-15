package christmas.controller;

import christmas.domain.Order;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventPlannerController {

    private final EventPlannerService service;
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController() {
        this.service = new EventPlannerService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        try {
            int date = inputView.readDate();
            LocalDate visitDate = LocalDate.of(2023, 12, date);
            Order order = inputView.readOrder();

            service.applyDiscountsAndGifts(order, visitDate);

            outputView.displayOrderSummary(order);
        } catch (IllegalArgumentException e) {
            outputView.displayError(e.getMessage());
        }
    }
}

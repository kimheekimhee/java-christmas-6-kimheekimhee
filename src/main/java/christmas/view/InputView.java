package christmas.view;

import christmas.domain.*;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readDate() {
        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            int date = Integer.parseInt(Console.readLine());
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Order readOrder() {
        System.out.println("주문하실 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1)");
        String[] orderEntries = Console.readLine().split(",");
        Order order = new Order();

        for (String entry : orderEntries) {
            String[] itemDetails = entry.trim().split("-");
            if (itemDetails.length != 2 || !Menu.isValidMenu(itemDetails[0])) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            try {
                int quantity = Integer.parseInt(itemDetails[1].trim());
                if (quantity <= 0) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                }
                order.addItem(itemDetails[0], quantity);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
        return order;
    }
}

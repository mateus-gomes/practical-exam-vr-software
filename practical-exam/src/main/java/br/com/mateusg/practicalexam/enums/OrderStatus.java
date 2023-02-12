package br.com.mateusg.practicalexam.enums;

public enum OrderStatus {
    ACTIVE,
    EXCLUDED;

    public boolean isActive(OrderStatus status){
        return status == OrderStatus.ACTIVE;
    }

    public static boolean isValidStatus(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(status)) {
                return true;
            }
        }

        return false;
    }

    public static String listOfValidStatus(){
        StringBuilder validStatus = new StringBuilder();

        OrderStatus[] orderStatus = OrderStatus.values();
        for (int i = 0; i < orderStatus.length; i++) {
            if(i == orderStatus.length-1){
                validStatus
                        .append(orderStatus[i].name());
            } else {
                validStatus
                        .append(orderStatus[i].name())
                        .append(", ");
            }
        }

        return validStatus.toString();
    }

}
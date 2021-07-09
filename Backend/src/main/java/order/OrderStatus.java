package order;

public enum OrderStatus {
    Processing,
    InPreparation,
    WaitingForDelivery,
    WaitingManagerConfirmation,
    InTransport,
    Delivered,
    Canceled
}

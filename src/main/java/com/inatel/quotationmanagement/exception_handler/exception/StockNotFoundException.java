package com.inatel.quotationmanagement.exception_handler.exception;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message) {
        super(message);
    }

}

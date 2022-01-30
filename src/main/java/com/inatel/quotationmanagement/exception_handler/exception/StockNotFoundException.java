package com.inatel.quotationmanagement.exception_handler.exception;

import java.util.function.Supplier;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message) {
        super(message);
    }

}

package com.tt.shop.exception;

import java.util.function.Supplier;

public class ProductNotFoundException extends Exception  {
    public ProductNotFoundException(String message) {
        super(message);
    }

}

package com.tinqin.bff.core.exception;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(UUID id) {
        super(String.format("Item with id '%s' doesn't exist in cart.", id));
    }
}

package id.ac.ui.cs.advprog.eshop.utils;

import java.util.UUID;
public class UUIDGenerator {
    public String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

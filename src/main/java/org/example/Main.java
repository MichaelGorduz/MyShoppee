package org.example;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        MyShopUpload msu = new MyShopUpload();
//        msu.storeItemDetails("T-101".trim(), "MacBook", 1200, 100);
//        msu.storeCustomerDetails("C-001", "John Connor", "3158782555", "255 Union Square");
//        msu.purchaseDetails("Tr001", "C-001", "T-101", Date.valueOf("2023-08-24"), 2);

        MyShopDisplay display = new MyShopDisplay();
        display.displayItems();
        display.customerDetails("C-001");
        display.purchaseDetails("C-001");
    }
}
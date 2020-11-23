package com.zel.lua;

public class Test {
    public static void main(String[] a) {
        for(int i = 0; i < 1000000; ++i) {
            String text = java.awt.event.KeyEvent.getKeyText(i);
            if(!text.contains("Unknown keyCode: ")) {
                System.out.println(text + " = " + i + ";");
            }
        }
    }
}
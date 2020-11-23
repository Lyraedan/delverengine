package com.zel.lua.input;

import com.zel.lua.engine.LuaEngine;

public class LuaInputHandler {

    public static LuaInputHandler instance;
    public boolean[] keys = new boolean[1000000]; // 300

    public LuaInputHandler() {
        instance = (LuaInputHandler) LuaEngine.singleton.globals.get("input").touserdata();
    }

    public boolean keyPressed(int key) {
        System.out.println("[Java] Lua Key Pressed: " + key);
        return keys[key] == true;
    }

    public boolean keyReleased(int key) {
        System.out.println("[Java] Lua Key Released: " + key);
        return keys[key] == false;
    }
}

package com.zel.lua.input;

import com.badlogic.gdx.Input;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;

public class LuaInputHandler {

    public static LuaInputHandler instance;
    public boolean[] keys = new boolean[1000000]; // 300
    public final String module = "GameInput";

    public OneArgFunction pressed, released;

    public LuaInputHandler() {
        System.out.println("Setting up lua input handler");
        instance = this; //(LuaInputHandler) LuaEngine.singleton.GetFunction(module, "input").touserdata();

        pressed = new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                int keyCode = arg.toint();
                String key = Input.Keys.toString(keyCode); //KeyEvent.getKeyText(keyCode);
                LuaEngine.singleton.GetFunction(module, "OnKeyPressed").call(LuaValue.valueOf(key));
                return LuaValue.valueOf(keyPressed(keyCode));
            }
        };

        released = new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                int keyCode = arg.toint();
                String key = Input.Keys.toString(keyCode); //KeyEvent.getKeyText(keyCode);
                LuaEngine.singleton.GetFunction(module, "OnKeyReleased").call(LuaValue.valueOf(key));
                return LuaValue.valueOf(keyReleased(keyCode));
            }
        };

        System.out.println("Set up lua input handler");
    }

    public boolean keyPressed(int keyCode) {
        return keys[keyCode] == true;
    }

    public boolean keyReleased(int keyCode) {
        return keys[keyCode] == false;
    }
}
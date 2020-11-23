package com.zel.lua.engine;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class LuaEngine {

    private final String AUTHOR = "Zel";
    public final String scriptDir = "./test_mods/lua_test";

    public static LuaEngine singleton;

    public Globals globals = JsePlatform.standardGlobals();

    public LuaEngine() {
        System.out.println("Setting up the " + AUTHOR + "'s lua scripting engine");
        if(singleton == null) singleton = this;
        else System.err.println("Only one instance of the lua engine can exist at a time!");
        SetupAPI();
        System.out.println("Successfully set up " + AUTHOR + "'s lua engine");
    }

    public void SetupAPI() {
        LuaValue core = loadScript(scriptDir + "/ZelsLuaApi/core.lua");
        LuaValue input = loadScript(scriptDir + "/ZelsLuaApi/input.lua");
    }

    /**
     *  Compile and load a lua script
     * */
    public LuaValue loadScript(String path) {
        System.out.println("Loading script " + path);
        LuaValue compiled = null;
        try {
            compiled = globals.load(new FileReader(path), "script").call();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return compiled;
    }

    /*
    *  Call a lua function with or without parameters
    * */
    public Object invoke(String func, Object... parameters) {
        if(parameters != null && parameters.length > 0) {
            LuaValue[] values = new LuaValue[parameters.length];
            for(int i = 0; i < parameters.length; i++) {
                values[i] = CoerceJavaToLua.coerce(parameters[i]);
            }
            return globals.get(func).call(LuaValue.listOf(values));
        }

        return globals.get(func).call();
    }
}

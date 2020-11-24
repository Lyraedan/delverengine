package com.zel.lua.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.interrupt.dungeoneer.game.Game;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.zel.lua.input.LuaInputHandler;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class LuaEngine {

    private final String AUTHOR = "Zel";
    public final String scriptDir = "./test_mods/lua_test";

    public static LuaEngine singleton;

    public Globals globals = JsePlatform.standardGlobals();

    public LuaInputHandler inputHandler;

    public LuaEngine() {
        System.out.println("Setting up the " + AUTHOR + "'s lua scripting engine");
        if(singleton == null) singleton = this;
        else System.err.println("Only one instance of the lua engine can exist at a time!");
        //inputHandler = new LuaInputHandler();
        SetupAPI();
        System.out.println("Successfully set up " + AUTHOR + "'s lua engine");
    }

    public void SetupAPI() {
        LuaValue core = loadScript("/ZelsLuaApi/core.lua");
        LuaValue input = loadScript("/ZelsLuaApi/input.lua");
    }

    /**
     *  Compile and load a lua script
     * */
    public LuaValue loadScript(String scriptName) {
        LuaValue compiled = null;
        try {
            FileHandle file = Game.findInternalFileInMods("/scripts" + scriptName);
            Gdx.app.log("Lua Engine", "Loading scripts " + file.path());
            if(!file.exists()) return null;

            compiled = globals.load(new FileReader(file.file().getPath()), "script").call();
        } catch(Exception e) {
            System.err.println("[Lua Engine] Failed to load script " + scriptName + " | " + e.getMessage());
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

    /*
     *  Call a lua function with or without parameters
     * */
    public Object invoke(String module, String func, Object... parameters) {
        if(parameters != null && parameters.length > 0) {
            LuaValue[] values = new LuaValue[parameters.length];
            for(int i = 0; i < parameters.length; i++) {
                values[i] = CoerceJavaToLua.coerce(parameters[i]);
            }
            return globals.get(module).get(func).call(LuaValue.listOf(values));
        }

        return globals.get(func).call();
    }

    public LuaValue GetFunction(String function) {
        return globals.get(function);
    }

    public LuaValue GetFunction(String module, String function) {
        return globals.get(module).get(function);
    }

    /**
     * Not yet functional...
     **/
    public void SetVariable(String module, String var, Object val) {
        globals.get(module).set(LuaValue.valueOf(var), LuaValue.userdataOf(val));
    }

    public static boolean readyForUse() {
        return singleton != null;
    }
}

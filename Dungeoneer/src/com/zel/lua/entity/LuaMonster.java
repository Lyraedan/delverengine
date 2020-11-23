package com.zel.lua.entity;

import com.interrupt.dungeoneer.entities.Monster;
import com.interrupt.dungeoneer.game.Level;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaValue;

public class LuaMonster extends Monster {

    // The script the monster will use
    private transient LuaValue script;
    // The lua instance of the monster class
    private Monster luaInstance;

    public LuaMonster() {
        //this.script = LuaEngine.singleton.loadScript("test_mods/lua_test/test.lua");
    }

    @Override
    public void init(Level level, Level.Source source) {
        this.script = LuaEngine.singleton.loadScript(LuaEngine.singleton.scriptDir + "/test.lua");
        luaInstance = (Monster) LuaEngine.singleton.globals.get("monster").touserdata();
        luaInstance.init(level, source);

        SyncLuaWithJava();

        LuaEngine.singleton.invoke("init");
    }

    @Override
    public void tick(Level level, float delta) {
        luaInstance.tick(level, delta);
        LuaEngine.singleton.invoke("tick");
        SyncJavaWithLua();
    }

    /**
     * Used for init
     * */
    void SyncLuaWithJava() {
        luaInstance.x = x;
        luaInstance.y = y;
        luaInstance.z = z;

        luaInstance.xa = xa;
        luaInstance.ya = ya;
        luaInstance.za = za;
    }

    /**
     * Used for keep the instance upto date with the lua instance
     * */
    void SyncJavaWithLua() {
        x = luaInstance.x;
        y = luaInstance.y;
        z = luaInstance.z;

        xa = luaInstance.xa;
        ya = luaInstance.ya;
        za = luaInstance.za;
    }
}

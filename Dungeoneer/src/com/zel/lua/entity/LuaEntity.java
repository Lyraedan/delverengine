package com.zel.lua.entity;

import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Entity;
import com.interrupt.dungeoneer.game.Level;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaValue;

public class LuaEntity extends Entity {

    // The script the monster will use
    protected transient LuaValue script;
    // The lua instance of the monster class
    private Entity luaInstance;

    @EditorProperty(group = "Scripting")
    public String scriptName = "/test.lua";

    @EditorProperty(group = "Scripting")
    public String module = "ScriptableEntity";

    public LuaEntity() { }

    @Override
    public void init(Level level, Level.Source source) {
        if(LuaEngine.readyForUse()) {
            this.script = LuaEngine.singleton.loadScript(scriptName);
            luaInstance = (ScriptableEntity) LuaEngine.singleton.GetFunction(module, "entity").touserdata();
            luaInstance.init(level, source);
            SyncLuaWithJava();
            LuaEngine.singleton.GetFunction(module, "init").call();
        }
    }

    @Override
    public void tick(Level level, float delta) {
        luaInstance.tick(level, delta);
        LuaEngine.singleton.GetFunction(module, "tick").call(LuaValue.userdataOf(level), LuaValue.valueOf(delta));
        SyncJavaWithLua();
    }

    /**
     * Used for init
     * */
    void SyncLuaWithJava() {
        luaInstance.setPosition(x, y, z);

        luaInstance.xa = xa;
        luaInstance.ya = ya;
        luaInstance.za = za;

        luaInstance.tex = tex;
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

        tex = luaInstance.tex;
    }

}

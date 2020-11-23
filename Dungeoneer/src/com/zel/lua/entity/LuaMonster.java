package com.zel.lua.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.interrupt.dungeoneer.annotations.EditorProperty;
import com.interrupt.dungeoneer.entities.Monster;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaValue;

public class LuaMonster extends Monster {

    // The script the monster will use
    private transient LuaValue script;
    // The lua instance of the monster class
    private Monster luaInstance;

    @EditorProperty(group = "Scripting")
    public String scriptName = "/test.lua";

    public LuaMonster() { }

    @Override
    public void init(Level level, Level.Source source) {
        this.script = LuaEngine.singleton.loadScript(scriptName); //LuaEngine.singleton.scriptDir + scriptName
        luaInstance = (Monster) LuaEngine.singleton.globals.get("monster").touserdata();
        luaInstance.init(level, source);

        SyncLuaWithJava();

        LuaEngine.singleton.GetFunction("ScriptableMonster", "init").call();
        LuaEngine.singleton.SetVariable("ScriptableMonster", "test", "Set variable from java");
    }

    @Override
    public void tick(Level level, float delta) {
        luaInstance.tick(level, delta);
        LuaEngine.singleton.GetFunction("ScriptableMonster", "tick").call(LuaValue.valueOf(delta));
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

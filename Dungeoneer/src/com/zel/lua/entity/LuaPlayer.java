package com.zel.lua.entity;

import com.interrupt.dungeoneer.entities.Player;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.game.Level;
import com.zel.lua.controllers.LuaPlayerController;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaValue;

public class LuaPlayer extends Player {

    private transient LuaPlayerController luaInstance;
    private transient final String module = "Player";

    public LuaPlayer() {
        super();
        if(LuaEngine.readyForUse()) {
            this.luaInstance = (LuaPlayerController) LuaEngine.singleton.GetFunction(module, "controller").touserdata();
        }
    }

    public LuaPlayer(Game game) {
        super(game);
        if(LuaEngine.readyForUse()) {
            this.luaInstance = (LuaPlayerController) LuaEngine.singleton.GetFunction(module, "controller").touserdata();
        }
    }

    @Override
    public void init() {
        super.init();
        luaInstance.syncLuaToJava();
        LuaEngine.singleton.GetFunction(module, "init").call();
    }

    @Override
    public void tick(Level level, float delta) {
        super.tick(level, delta);
        LuaEngine.singleton.GetFunction(module, "tick").call(LuaValue.userdataOf(level), LuaValue.valueOf(delta));
        luaInstance.syncJavaToLua();
    }

}

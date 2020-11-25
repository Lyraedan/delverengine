package com.zel.lua.controllers;

import com.interrupt.dungeoneer.game.Game;

public class LuaPlayerController {

    // This is what gets greated in the player lua script and manipulated

    // Absolute position
    public float x, y, z;
    // Velocity
    public float xa, ya, za;

    // Rotation on z-axis
    public float rot;
    // Rotation on y-axis
    public float yrot;

    public LuaPlayerController() {

    }

    public void syncLuaToJava() {
        x = Game.instance.player.x;
        y = Game.instance.player.y;
        z = Game.instance.player.z;

        xa = Game.instance.player.xa;
        ya = Game.instance.player.ya;
        za = Game.instance.player.za;

        rot = Game.instance.player.rot;
        yrot = Game.instance.player.yrot;
    }

    public void syncJavaToLua() {
        Game.instance.player.setPosition(x, y, z);

        Game.instance.player.xa = xa;
        Game.instance.player.ya = ya;
        Game.instance.player.za = za;

        rot = Game.instance.player.rot;
        yrot = Game.instance.player.yrot;

    }

}

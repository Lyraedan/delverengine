package com.zel.lua.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.interrupt.dungeoneer.GameManager;
import com.interrupt.dungeoneer.game.Game;
import com.interrupt.dungeoneer.gfx.GlRenderer;
import com.zel.lua.engine.LuaEngine;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.HashMap;

public class LuaGraphics {

    private String module = "Gfx";

    private HashMap<String, Texture> cache = new HashMap<String, Texture>();

    public LuaGraphics() { }

    public void drawImage(String imgRef, float x, float y, int width, int height) {
        if(cache.containsKey(imgRef)) {
            GameManager.renderer.uiBatch.begin();
            Texture texture = cache.get(imgRef);
            GameManager.renderer.uiBatch.draw(texture, x, y, width, height);
            GameManager.renderer.uiBatch.end();
        } else {
            Gdx.app.log("Lua Graphic", "Failed to draw image, no image exists with the reference " + imgRef);
        }
    }

    public void drawText(String text, float x, float y, float size, int colorHex) {
        GameManager.renderer.uiBatch.begin();
        Color colour = new Color(colorHex);
        Gdx.app.log("Lua Graphics", "Drawing \"" + text + "\" at " + x + ", " + y + " at the size of " + size + " with the set colour of " + colour.toString());
        GameManager.renderer.drawText(text, x, y, size, colour);
        GameManager.renderer.uiBatch.end();
    }

    public void loadImage(String ref, String path) {
        if(cache.containsKey(ref)) {
            Texture loaded = new Texture(Gdx.files.internal(path));
            cache.put(ref, loaded);
        } else {
            Gdx.app.log("Lua Graphic", "Failed to load image, an image with the reference " + ref + " already exists!");
        }
    }


}

-- Module
Gfx = {}

-- Create a reference to the Java api
Gfx.graphics = luajava.newInstance("com.zel.lua.graphics.LuaGraphics");

print("Graphics called!");

function gfxLoadImage(ref, imagePath)
	Gfx.graphics:loadImage(ref, imagePath);
end

function gfxDrawImage(imageRef, x, y, width, height)
	Gfx.graphics:drawImage(imageRef, x, y, width, height);
end

function gfxDrawText(text, x, y, size, colourHex)
	Gfx.graphics:drawText(text, x, y, size, colourHex);
end
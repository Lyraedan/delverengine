-- The module
Player = {};
-- Core lua class (Called on startup)
print("Player called!");

-- Create a reference to the Java player
Player.controller = luajava.newInstance("com.zel.lua.controllers.LuaPlayerController");

-- Absolute position
local x = 1f;
local y = 10f;
local z = 0f;

-- Velocity
local xa = 0f;
local ya = 0f;
local za = 0f;

-- rotation on z-axis
local rot = 0f;
-- rotation on y-axis
local yrot = 0f;

local speed = 0.05f;

function Player.init()
x = Player.controller.x;
y = Player.controller.y;
z = Player.controller.z;

xa = Player.controller.xa;
ya = Player.controller.ya;
za = Player.controller.za;

rot = Player.controller.rot;
yrot = Player.controller.yrot;

gfxLoadImage("Test", "C:/Users/luke/Downloads/minidoku.png");

end

function Player.tick(level, delta) 

rot = Player.controller.rot;
yrot = Player.controller.yrot;

gfxDrawText("Hello world", 0, 0, 0.05f, 0xFFFFFFFF);

local move = speed / delta;

if keyPressed(Key_Space) then
	z = z + move;
end

if keyPressed(Key_V) then
	z = z + -move;
end

-- How its done in the java version
--float xMod = (float)(xm * Math.cos(rot) + zm * Math.sin(rot)) * walkSpeed * delta;
--float yMod = (float)(zm * Math.cos(rot) - xm * Math.sin(rot)) * walkSpeed * delta;

local rotationY = math.deg(rot); --* 360 / 1f; --6.28318531

if keyPressed(Key_W) then
	x = x + (math.sin(rotationY * math.pi / 180) * move);
	y = y + (-math.cos(rotationY * math.pi / 180) * move);
end

if keyPressed(Key_A) then
	x = x + (math.sin((rotationY - 90) * math.pi / 180) * move);
	y = y + (-math.cos((rotationY - 90) * math.pi / 180) * move);
end

if keyPressed(Key_S) then
	x = x - (math.sin(rotationY * math.pi / 180) * move);
	y = y - (-math.cos(rotationY * math.pi / 180) * move);
end

if keyPressed(Key_D) then
	x = x + (math.sin((rotationY + 90) * math.pi / 180) * move);
	y = y + (-math.cos((rotationY + 90) * math.pi / 180) * move);
end

if keyPressed(Key_Shift) then
	print("Shift pressed!");
end

Player.controller.x = x;
Player.controller.y = y;
Player.controller.z = z;
end
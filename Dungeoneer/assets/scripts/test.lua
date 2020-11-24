-- This is the module
ScriptableEntity = {};
-- Create a new instance of the ScriptableEntity class
ScriptableEntity.entity = luajava.newInstance("com.zel.lua.entity.ScriptableEntity");

-- Absolute position
x = 0f;
y = 0f;
z = 0f;

-- Velocity | takes collision into account
xa = 0f;
ya = 0f;
za = 0f;

-- The texture index
tex = 0;

-- Should the Entity be ticked and drawn?
isActive = true;

speed = 0.02f;

local timer = 0;
local maxTimer = 100;
local flip = false;

function ScriptableEntity.init()
	x = ScriptableEntity.entity.x;
	y = ScriptableEntity.entity.y;
	z = ScriptableEntity.entity.z;

	xa = ScriptableEntity.entity.xa;
	ya = ScriptableEntity.entity.ya;
	za = ScriptableEntity.entity.za;
end

function ScriptableEntity.tick(level, delta)

	print(keyPressed("3"))

	timer = timer + 1;

	if timer <= maxTimer then
		xa = (speed / delta);
		if not flip then
			ya = (speed / delta);
		else 
			ya = -(speed / delta);
		end
	elseif timer > maxTimer and timer <= maxTimer * 2 then
		xa = -(speed / delta);
		if not flip then
			ya = -(speed / delta);
		else 
			ya = (speed / delta);
		end
	else
		timer = 0;
		if flip then
			flip = false
		else 
			flip = true
		end
	end
	
	ScriptableEntity.entity.xa = xa;
	ScriptableEntity.entity.ya = ya;
	ScriptableEntity.entity.za = za;
end

return ScriptableEntity;
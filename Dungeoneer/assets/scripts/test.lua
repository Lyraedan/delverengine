ScriptableMonster = {};
-- Create a new instance of the Monster class
monster = luajava.newInstance("com.interrupt.dungeoneer.entities.Monster");

-- Absolute position
x = 0f;
y = 0f;
z = 0f;

-- Velocity | takes collision into account
xa = 0f;
ya = 0f;
za = 0f;

speed = 0.002f;

test = "Hello world";

function ScriptableMonster.init()
x = monster.x;
y = monster.y;
z = monster.z;

xa = monster.xa;
ya = monster.ya;
za = monster.za;

print(test);

end

function ScriptableMonster.tick(delta)
print("Delta");
print(delta);
xa = xa + (speed / delta);
--print("----");
--print(xa);
--print(ya);
--print(za);

print("---");
print(test);

monster.xa = xa;
monster.ya = ya;
monster.za = za;
end
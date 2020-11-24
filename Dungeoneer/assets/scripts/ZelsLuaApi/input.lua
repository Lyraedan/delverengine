GameInput = {}

print("Setting up lua input");
-- Create a reference to the Java api
GameInput.input = luajava.newInstance("com.zel.lua.input.LuaInputHandler");

GameInput.keyCodePressed = -1;
GameInput.keyCodeReleased = -1;
GameInput.keyPressed = false;
GameInput.keyReleased = false;

-- What happens when a key is pressed / released
function GameInput.OnKeyPressed(keyCode)
	GameInput.keyCodePressed = keyCode;
	GameInput.keyPressed = true;
	GameInput.keyReleased = false;
	print("Pressed");
	print(keyCode);
end

function GameInput.OnKeyReleased(keyCode)
	GameInput.keyCodeReleased = keyCode;
	GameInput.keyPressed = false;
	GameInput.keyReleased = true;
	print("Released");
	print(keyCode);
end

function keyPressed(code)
	return GameInput.keyPressed == true and GameInput.keyCodePressed == code;
end

function keyReleased(code)
	return GameInput.keyReleased == true and GameInput.keyCodeReleased == code;
end
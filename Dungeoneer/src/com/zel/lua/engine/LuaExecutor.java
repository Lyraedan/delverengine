package com.zel.lua.engine;

import java.util.HashMap;

public class LuaExecutor {

    public static LuaExecutor singleton;

    public HashMap<String, LuaExecutable> executables = new HashMap<String, LuaExecutable>();

    public LuaExecutor() {
        if(singleton == null) singleton = this;
        else System.err.println("LuaExecutor singleton already instantiated!");
    }

    public void RegisterExecutable(LuaExecutable exe) {
        if(!executables.containsKey((exe.functionName))) {
            executables.put(exe.functionName, exe);
        } else {
            System.err.println("Executable " + exe.functionName + " already exists with " + exe.parameters.length + " parameters");
            return;
        }
    }

    public void ExecuteFunction(String function) {
        if(executables.containsKey(function)) {
            executables.get(function).ExecuteAll();
        } else {
            System.err.println("Unable to find function " + function + " please ensure it is registered!");
            return;
        }
    }

    public void ExecuteFunction(String function, int parameter_index) {
        if(executables.containsKey(function)) {
            executables.get(function).Execute(parameter_index);
        } else {
            System.err.println("Unable to find function " + function + " please ensure it is registered!");
            return;
        }
    }

}

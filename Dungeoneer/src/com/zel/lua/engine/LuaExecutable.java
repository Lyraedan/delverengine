package com.zel.lua.engine;

import java.util.ArrayList;
import java.util.List;

public class LuaExecutable {

    public String functionName;
    public ILuaExecutable[] parameters;

    public LuaExecutable(String ref, ILuaExecutable... param) {
        this.functionName = ref;
        this.parameters = param;
    }

    public void ExecuteAll() {
        if(parameters.length < 1) {
            System.err.println("Lua parameter length can not be less then 1");
            return;
        }
        for(ILuaExecutable parameter : parameters) {
            parameter.Invoke();
        }
    }

    public void Execute(int index) {
        if(parameters.length < 1) {
            System.err.println("Lua parameter length can not be less then 1");
            return;
        }

        if(index < 0) {
            System.err.println("Parameter index can not be < 0");
            return;
        }

        if(index >= parameters.length) {
            System.err.println("Parameter index can not be >= the amount of parameters [" + parameters.length + "]");
            return;
        }

        parameters[index].Invoke();
    }

}

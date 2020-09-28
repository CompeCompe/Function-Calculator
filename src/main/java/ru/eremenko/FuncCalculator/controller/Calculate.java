package ru.eremenko.FuncCalculator.controller;

import lombok.Data;
import ru.eremenko.FuncCalculator.model.CalcDesk;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

@Data
public class Calculate {
    public String firstResult;
    public String secondResult;
    //Добавлять инфу по выполненым методам в список и его выводить сделать static
    public static ArrayList<ArrayList<String>> list;

    private String func1;
    private String func2;
    private int count;
    private String order;
    private int firstParam;
    private int secondParam;


    //Добавляем информацию про вызов функций
    public void adInfo(CalcDesk calcDesk){
        func1 = calcDesk.func1;
        func2 = calcDesk.func2;
        count = calcDesk.count;
        order = calcDesk.order;
    }

    //Определяем параметры у функции
    private int param(String func){
        int start = func.indexOf("(") + 1;
        if (start > func.indexOf("{")){
            return -1;
        }
        int end = func.indexOf(")");
        return  Integer.parseInt(func.substring(start,end));
    }



    //Выполняем JS код
    public void calculation() throws ScriptException, NoSuchMethodException {

        firstParam = param(func1);
        secondParam = param(func2);

        func1 = func1.replaceFirst("("+firstParam + ")","");
        func2 = func2.replaceFirst("("+secondParam + ")","");

        MyThread t1 = new MyThread(func1,count,1,order);
        MyThread t2 = new MyThread(func2,count,2,order);

        if (firstParam >= secondParam){
            t2.start();
            t1.start();
        }else{
            t1.start();
            t2.start();
        }


//        Object o = engine.eval(func1);
//        Object o2 = engine.eval(func2);
//        if (firstParam >= secondParam){
//             firstResult = String.valueOf(((Invocable)engine).invokeFunction(firstFuncName));
//             secondResult = String.valueOf(((Invocable)engine).invokeFunction(secondFuncName));
//        }else{
//             firstResult = String.valueOf(((Invocable)engine).invokeFunction(secondFuncName));
//             secondResult = String.valueOf(((Invocable)engine).invokeFunction(firstFuncName));
//        }


    }
}

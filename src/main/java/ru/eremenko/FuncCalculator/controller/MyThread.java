package ru.eremenko.FuncCalculator.controller;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class MyThread extends Thread{

    private String func;
    private ScriptEngineManager manager;
    private ScriptEngine engine;
    private int count;
    private int number;
    private String order;
    private ArrayList<ArrayList<String>> results = new ArrayList<>();
    private int amountResults = 0;



    public MyThread(String func,int count,int number,String order) {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
        this.func = func;
        this.count = count;
        this.number = number;
        this.order = order;
    }
    //Поиск названия функции
    private String findFuncName(String func){

        int start = func.indexOf("(");
        while (start != 0 && !Character.toString(func.charAt(start - 1)).equals(" ")){
            start--;
        }
        return func.substring(start,func.indexOf("("));
    }
    @Override
    public void run(){
        try {
            String firstFuncName = findFuncName(func);
            engine.eval(func);
            for (int i =1;i<= count;i++){
                long startTime = System.currentTimeMillis();
                String result = (String.valueOf(((Invocable)engine).invokeFunction(firstFuncName)));
                long runTime= System.currentTimeMillis() - startTime;
                int finalI = i;
                if (order.equals("sorted")){

                    results.add(new ArrayList<>(){{ add(String.valueOf(finalI));
                    add(String.valueOf(number));
                    add(result);
                    add(String.valueOf(runTime));
                    }});

                    amountResults++;
                    if (number == 1){
                        if (Calculate.list.size()%2 == 0){
                            amountResults--;
                            results.get(0).add(String.valueOf(amountResults));
                            Calculate.list.add(results.get(0));
                            results.remove(0);
                        }
                    }else{
                        if (Calculate.list.size()%2 != 0){
                            amountResults--;
                            results.get(0).add(String.valueOf(amountResults));
                            Calculate.list.add(results.get(0));
                            results.remove(0);
                        }
                    }
                }else{
                    Calculate.list.add(new ArrayList<>(){{ add(String.valueOf(finalI));
                        add(String.valueOf(number));
                        add(result);
                        add(String.valueOf(runTime));
                        add("0");
                    }});
                }
            }
            if (number == 1){
                while (results.size() > 0){
                    if (Calculate.list.size()%2 == 0){
                        amountResults--;
                        results.get(0).add(String.valueOf(amountResults));
                        Calculate.list.add(results.get(0));
                        results.remove(0);
                    }
                }
            }else{
                while (results.size() > 0){
                    if (Calculate.list.size()%2 != 0){
                        amountResults--;
                        results.get(0).add(String.valueOf(amountResults));
                        Calculate.list.add(results.get(0));
                        results.remove(0);
                    }
                }
            }
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

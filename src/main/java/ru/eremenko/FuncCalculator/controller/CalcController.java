package ru.eremenko.FuncCalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.eremenko.FuncCalculator.model.CalcDesk;

import javax.script.ScriptException;
import java.util.ArrayList;

@Controller
public class CalcController {

    private Calculate calculate = new Calculate();

    @GetMapping("/calc")
    public static String caLc(CalcDesk calcDesk){
        return "calc";
    }
    //Дейтсвия после нажатия кнопки
    @PostMapping("/calc")
    public String calc(CalcDesk calcDesk, Model model) throws ScriptException, NoSuchMethodException, InterruptedException {
        calculate.adInfo(calcDesk);
        //Задержка
        Thread.sleep(calcDesk.delay * 1000);
        Calculate.list = new ArrayList();
        calculate.calculation();
        return "redirect:/results";
    }
    @GetMapping("/results")
    public  String results(Model model){
        model.addAttribute("calc",calculate);
        return "results";
    }

    @PostMapping("/close")
    public void close(){
        System.exit(0);
    }
}

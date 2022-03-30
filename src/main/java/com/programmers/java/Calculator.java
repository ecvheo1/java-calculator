package com.programmers.java;

import com.programmers.java.io.Input;
import com.programmers.java.io.Output;
import com.programmers.java.validator.Validator;
import com.programmers.java.service.CalculateService;
import com.programmers.java.service.HistoryService;


public class Calculator {

    private final Input input;
    private final Output output;
    private final CalculateService calculateService;
    private final HistoryService findService;
    private final Validator validator;

    public Calculator(Input input, Output output, CalculateService calculateService, HistoryService findService, Validator validator) {
        this.input = input;
        this.output = output;
        this.calculateService = calculateService;
        this.findService = findService;
        this.validator = validator;
    }

    public void run() {
        while (true) {

            output.printMenu();
            String inputMenu = input.inputMenu();

            switch (inputMenu) {
                case "1":
                    output.printHistory(findService.findHistory());
                    break;
                case "2":
                    String formula = input.inputFormula();
                    try {
                        validator.validateFormula(formula);
                        int result = calculateService.calculateFormula(formula);
                        findService.save(formula, result);
                        output.printResult(result);
                    } catch (Exception e) {
                        output.printFormulaError(e.getMessage());
                    }
                    break;
                default:
                    output.printMenuError();
            }
        }
    }

}
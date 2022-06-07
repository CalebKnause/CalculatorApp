package calculatorApp;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    // Strings that will be used to hold operation numbers
    private String input1, input2, input3;

    // Creating a Frame
    static JFrame window = new JFrame();

    // Creating a text field
    static JTextField windowTextField = new JTextField();

    // Basic Constructor
    public Calculator() {
        input1 = input2 = input3 = "";
    }

    public static void main(String[]args) {

        window = new JFrame("Calculator"); // Naming window of Frame to Calculator

        // Setting look and feel of the Frame
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

        Calculator newCalculator = new Calculator(); // Creation of new Calculator Object

        // Making the text field so that all buttons will fit into field on calculator
        windowTextField = new JTextField(16);
        windowTextField.setEditable(false);

        // Creating a new panel
        JPanel calcPanel = new JPanel();

        // Creating number buttons 0 -> 9, equals, decimal, and 4 operators
        JButton button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, buttonDecimal, buttonEquals, buttonAdd,
                buttonSubtract, buttonMultiply, buttonDivide, buttonClear;

        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");

        // Creating a decimal, equals, and basic arithmetic operators button
        buttonDecimal = new JButton(".");
        buttonEquals = new JButton("=");
        buttonClear = new JButton("C");

        buttonAdd = new JButton("+");
        buttonSubtract = new JButton("-");
        buttonMultiply = new JButton("*");
        buttonDivide = new JButton("/");

        // Creation of a button list to iterate through to add action listeners and add to frame
        List<JButton> jButtonList = Arrays.asList(buttonAdd, button1, button2, button3, buttonSubtract,
                button4, button5, button6, buttonMultiply, button7, button8, button9,
                buttonDivide, buttonDecimal, button0, buttonClear, buttonEquals);

        // Iterating through button list to add action listeners
        for (JButton jButton : jButtonList) {
            jButton.addActionListener(newCalculator);
        }

        // Iterating through previously created list to add all buttons to frame
        for (JButton jButton : jButtonList) {
            calcPanel.add(jButton);
        }

        // Changing background color of panel
        calcPanel.setBackground(Color.GRAY);
        calcPanel.add(windowTextField);

        // Adding the panel to the frame
        window.add(calcPanel);
        window.setSize(200, 220);
        window.show();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Adding events to buttons
    public void actionPerformed(ActionEvent e) {

        String inputAction = e.getActionCommand();

        // Checking if value entered is a number
        if ((inputAction.charAt(0) >= '0' && inputAction.charAt(0) <= '9' || inputAction.charAt(0) == '.')) {
            if (!input2.equals("")) {
                input3 = inputAction + input3;
            } else
                input1 = input1 + inputAction;

            // Setting textfield to answer of equation
            windowTextField.setText(input1 + input2 + input3);

        } else if (inputAction.charAt(0) == 'C') { // Represents clicking the clear button
            input1 = input2 = input3 = "";
            windowTextField.setText(input1 + input2 + input3);

        } else if (inputAction.charAt(0) == '=') {
            double result;

            // Storing arithmetic operator in input1
            if (input2.equals("+"))
                result = (Double.parseDouble(input1) + Double.parseDouble(input3));
            else if (input2.equals("-"))
                result = (Double.parseDouble(input1) - Double.parseDouble(input3));
            else if (input2.equals("/"))
                result = (Double.parseDouble(input1) / Double.parseDouble(input3));
            else
                result = (Double.parseDouble(input1) * Double.parseDouble(input3));

            windowTextField.setText(input1 + input2 + input3 + "=" + result);
            input1 = Double.toString(result);
            input2 = input3 = ""; // Resetting stored values to null
        } else {
            // Ff there was no operand
            if (input2.equals("") || input3.equals(""))
                input2 = inputAction;
                // else evaluate
            else {
                double result;

                // store the value in 1st
                if (input1.equals("+"))
                    result = (Double.parseDouble(input1) + Double.parseDouble(input3));
                else if (input2.equals("-"))
                    result = (Double.parseDouble(input1) - Double.parseDouble(input3));
                else if (input2.equals("/"))
                    result = (Double.parseDouble(input1) / Double.parseDouble(input3));
                else
                    result = (Double.parseDouble(input1) * Double.parseDouble(input3));

                // convert it to string
                input1 = Double.toString(result);

                // place the operator
                input2 = inputAction;

                // make the operand blank
                input3 = "";
            }

            // set the value of text
            windowTextField.setText(input1 + input2 + input3);
        }
    }
}


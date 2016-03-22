import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Boran1 on 22.3.2016.
 */
public class Calculator extends JFrame {

    //properties
    static final int FRAME_WIDTH = 400;
    static final int FRAME_HEIGHT = 300;
    protected JLabel display;
    protected boolean startNewValue;
    protected double lastValue;
    protected String lastOperator;

    //constructor
    public Calculator(){

        setTitle("Calculator");
        display = new JLabel("0");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        add(display, BorderLayout.NORTH);
        setLocationRelativeTo(null);
        lastValue = 0;
        lastOperator = "=";
        startNewValue = true;
        addButtonsToCalculator();
        pack();
        setVisible(true);
    }

    private void addButtonsToCalculator(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        buttonPanel.add(createDigitButtons("7"));
        buttonPanel.add(createDigitButtons("8"));
        buttonPanel.add(createDigitButtons("9"));
        buttonPanel.add(createOperationButton("/"));
        buttonPanel.add(createDigitButtons("4"));
        buttonPanel.add(createDigitButtons("5"));
        buttonPanel.add(createDigitButtons("6"));
        buttonPanel.add(createOperationButton("x"));
        buttonPanel.add(createDigitButtons("1"));
        buttonPanel.add(createDigitButtons("2"));
        buttonPanel.add(createDigitButtons("3"));
        buttonPanel.add(createOperationButton("-"));
        buttonPanel.add(createDigitButtons("0"));
        buttonPanel.add(createOperationButton("."));
        buttonPanel.add(createOperationButton("="));
        buttonPanel.add(createOperationButton("+"));

        add(buttonPanel, BorderLayout.CENTER);
    }

    public JButton createDigitButtons(String digit){
        JButton button = new JButton(digit);
        ActionListener listener = new DigitListener(digit);
        button.setSize(300, 300);
        button.addActionListener(listener);
        return button;
    }

    public JButton createOperationButton(String operator){
        JButton button = new JButton(operator);
        ActionListener listener = new OperationListener(operator);
        button.setSize(300, 300);
        button.addActionListener(listener);
        return button;
    }

    public double calculate(double no1, double no2, String operator){
        if (operator.equals("+"))
            return no1 + no2;
        else if (operator.equals("-"))
            return no1 - no2;
        else if (operator.equals("x"))
            return no1 * no2;
        else if (operator.equals("/"))
            return no1 / no2;
        else
            return no2;
    }

    private class DigitListener implements ActionListener{

        private String dL;

        public DigitListener(String dL){
            this.dL = dL;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (startNewValue){
                display.setText("");
                startNewValue = false;
            }
            display.setText(display.getText() + dL);

        }
    }

    private class OperationListener implements ActionListener{

        private String oL;

        public OperationListener(String oL){
            this.oL = oL;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!startNewValue){
                double value = Double.parseDouble(display.getText());
                lastValue = calculate(lastValue, value, lastOperator);
                display.setText("" + lastValue);
                startNewValue = true;
            }
            lastOperator = oL;
        }
    }
}

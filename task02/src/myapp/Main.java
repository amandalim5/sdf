package myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.print("Welcome.\n> ");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine().trim();
    List<String> inputs = new ArrayList<>();
    double $last = 0;
    while(!input.toLowerCase().equals("exit")){
      String[] inputArray = input.split(" ");

      // removing multiple whitespace
      for(String s: inputArray){
        if(!s.equals("")){
          inputs.add(s);
          // System.out.println("new" + s);
        }
      }
      // for(String s: inputs){
      //   // System.out.println(s);
      // }



      double a = 0;
      double b = 0;
      if(inputs.get(0).toLowerCase().equals("$last")){
        a = $last;
      } else{
        a = Double.parseDouble(inputs.get(0));
      }

      if(inputs.get(2).toLowerCase().equals("$last")){
        b = $last;
      } else{
        b = Double.parseDouble(inputs.get(2));
      }
      
      // System.out.println("first number:" + a);
      // System.out.println("second number: " + b);


      switch(inputs.get(1)){
        case "+":
          $last = add(a, b);
          System.out.println($last);
          break;
        case "-":
          $last = sub(a, b);
          System.out.println($last);
          break;
        case "*":
          $last = mul(a, b);
          System.out.println($last);
          break;
        case "/":
          $last = div(a, b);
          System.out.println($last);
          break;
      }



      inputs.clear();
      System.out.print("> ");
      input = sc.nextLine();
    }
    System.out.println("Bye bye");
    // System.out.println(sc.nextLine());
    sc.close();
  }

  public static double add(double a, double b){
    return a + b;

  }
  public static double sub(double a, double b){
    return a - b;

  }
  public static double mul(double a, double b){
    return a * b;

  }
  public static double div(double a, double b){
    return a / b;

  }
}

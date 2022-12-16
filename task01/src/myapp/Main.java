package myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws Exception {
    
    String csvFileName = args[0];
    String templateFileName = args[1];
    // System.out.printf("Looking at %s and %s\n", csvFileName, templateFileName);

    FileReader fr = new FileReader(csvFileName);
    BufferedReader br = new BufferedReader(fr);

    // getting the headers into an array
    String headers = br.readLine();
    String[] headerArray = headers.split(",");
    // for(String s: headerArray){
    //   System.out.println(s);
    // }


    List<String[]> allPersonsInfo = new ArrayList<>();

    // getting the user info into an arraylist of String arrays
    while(true){
      String line = br.readLine();
      if(line == null){
        break;
      }
      // System.out.println(line);
      String[] lineArray = line.split(",");
      allPersonsInfo.add(lineArray);
    }

    // close the filereader
    br.close();
    fr.close();

    // for(String[] a: allPersonsInfo){
    //   System.out.println("==========");
    //   for(String s: a){
    //     System.out.println(s);
    //   }
    // }

    // create a map where the variable name is the key, and the value is a String array of values
    Map<String,List<String>> data = new HashMap<>();
    for(int i=0; i<headerArray.length; i++){
      // System.out.println("========");
      // will contain each persons info for a particular variable
      List<String> variableData = new ArrayList<>();
      for(String[] a: allPersonsInfo){
        variableData.add(a[i]);
        // System.out.println("Adding " + a[i]);
      }
      data.put(headerArray[i], variableData);
    }

    // get the txt file
    FileReader frr = new FileReader(templateFileName);
    BufferedReader brr = new BufferedReader(frr);


    List<String[]> templateInfo = new ArrayList<>();
    List<String> template = new ArrayList<>();
    // getting the template info into an arraylist of String arrays
    while(true){
      String line = brr.readLine();
      if(line == null){
        break;
      }
      // System.out.println(line);
      template.add(line);
      String[] lineArray = line.split(" ");
      templateInfo.add(lineArray);
    }
    brr.close();
    frr.close();

    // for(String[] a: templateInfo){
    //   System.out.println("==========");
    //   for(String s: a){
    //     if(s.contains("<<")){

    //       int start = s.indexOf("<<");
    //       System.out.println(start);
    //       int end = s.indexOf(">>");
    //       System.out.println(end);
    //       String variableName = s.substring(start + 2, end);
    //       System.out.println(variableName);
    //       String[] variableArray = s.split(">>");

    //     }
    //     System.out.println(s);
    //   }
    // }

    for(int i=0; i<allPersonsInfo.size(); i++){
      // String personFile = allPersonsInfo.get(i)[0] + ".txt";
      // FileOutputStream fos = new FileOutputStream(personFile);
      // OutputStreamWriter osw = new OutputStreamWriter(fos);


      System.out.println("======== Dealing with " + allPersonsInfo.get(i)[0] + "=========");
      for(String s: template){
        while(s.contains("<<")){
          // System.out.println("checking" + s);
          int start = s.indexOf("<<");
          // System.out.println(start);
          int end = s.indexOf(">>");
          // System.out.println(end);
          String variableName = s.substring(start + 2, end);
          // System.out.println(variableName);
          s = s.replace("<<" + variableName + ">>", data.get(variableName).get(i));
          // System.out.println(s);
          // System.out.println(data.get(variableName).get(i));
        }
        if(s.contains("\\n")){
          s = s.replace("\\n", "<break>");
          String[] splitting = s.split("<break>");
          for(String a: splitting){
            System.out.println(a);
          }
          continue;
        }
        System.out.println(s);
      }

    }

  }
  
}

/*
Welcome to JDoodle!

You can execute code here in 110+ languages. Right now you’re in the Java IDE.

  1. Click the orange Execute button ▶ to execute the sample code below and see how it works.

  2. Want help writing or debugging code? Type a query into JDroid on the right hand side ---------------->

  3.Try the menu buttons on the left. Save your file, share code with friends and open saved projects.

Want to change languages? Try the search bar up the top.
*/

import java.util.Random;


public class MyClass {
  public static void main(String args[]) {
    int numAleatorio=0;
    int suma=0;

    Random rand = new Random();
    
    for(int i=0; i<=500; i++){
        
        numAleatorio= rand.nextInt((1000 - 10) + 1) + 10;
     
        suma=suma+numAleatorio;
        
    }
    System.out.println("Promedio : " + suma / 500);
    
    System.out.println("Suma: " + suma);

  }
}
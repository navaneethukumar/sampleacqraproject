package com.guarented.ecommerce.commonUtils;

import java.util.Random;

public class RandomNumber {

public static int getRndNumber() {
    Random random=new Random();
    int randomNumber=0;
    boolean loop=true;
    while(loop) {
        randomNumber=random.nextInt();
        if(Integer.toString(randomNumber).length()==10 && !Integer.toString(randomNumber).startsWith("-")) {
            loop=false;
        }
        }
    return randomNumber;
}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tam Kah Jun
 */

import java.io.*;

public class histogram {
    public static void main(String[] args) {
        int height=600;
        int width=600;
        try{
            File file = new File("bedroom.raw");
            FileInputStream input = new FileInputStream(file);

            int value;
            int[] array = new int[256];
            int[] imgData = new int[(int)file.length()];
            int count = 0;
            while((value=input.read()) != -1){
                imgData[count] = value;
                array[value]++;
                count++;
            }

            int[] array2 = new int[256];
            int x = 0 , newOutput = 0;

            for(int i = 0 ; i <256 ; i++){
                x = array[i] + x;
                newOutput = (x*255)/(height*width);
                array2[i] = newOutput;
                System.out.println("Array" + i + ": " + array[i] + " | RunSum:" + x + " | Normalized:" + x + "/" + (height*width) + " | Multiply:" + newOutput);
            }

            input.close();

            FileOutputStream output = new FileOutputStream("Histogram_Bedroom.raw");
            for(int i = 0; i<file.length(); i++){
                output.write(array2[imgData[i]]);
            }
            output.close();
        }
        catch (IOException ex){
            System.out.println("Error");
        }
    }
}


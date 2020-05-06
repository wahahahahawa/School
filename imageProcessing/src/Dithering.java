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
import java.util.Scanner;

public class Dithering {
    public static void main (String [] args) {
        Scanner input = new Scanner(System.in);
        int height = 600;
        int width = 600;
        int countHeight = 0 , countWidth = 0;


        int[][] data = new int[height][width];
        try{
            File f = new File("Bedroom.raw");
            byte []array = new byte[(int)f.length()+1];
            InputStream image = new FileInputStream(f);

            int value;
            while((value = image.read()) != -1){
                if(countWidth == width){
                    countWidth = 0;
                    countHeight++;
                }
                data[countHeight][countWidth] = value;
                countWidth++;
            }

            int[][] dithering2x2 ={
                    { 0 , 128 },
                    { 192 , 64}
            };

            int count = 0 , a = 0 , b = 0;
            while (count != height){
                if(count % 2 == 0){
                    for (int j = 0; j < width; j++){
                        if(j % 2 == 0){
                            a = 0;
                        }
                        else{
                            a = 1;
                        }
                        if(data[count][j] > dithering2x2[0][a]){
                            data[count][j] = 255;
                        }
                        else{
                            data[count][j] = 0;
                        }
                    }

                }
                else{
                    for (int j = 0; j < width; j++){
                        if(j % 2 == 0){
                            a = 0;
                        }
                        else{
                            a = 1;
                        }
                        if(data[count][j] > dithering2x2[1][a]){
                            data[count][j] = 255;
                        }
                        else{
                            data[count][j] = 0;
                        }
                    }
                }
                count++;
            }

            FileOutputStream output = new FileOutputStream("Dithering_Bedroom.raw");
            for(int i = 0; i < height; i++ ){
                for(int j = 0; j < width; j++){
                    output.write(data[i][j]);
                }
            }
            output.close();
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }
}

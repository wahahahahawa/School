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

public class LowPass {
    public static void main (String [] args) {
        
        int height=600;
        int width=600;
        int [][] image = new int[height][width];
        try{
            FileInputStream input = new FileInputStream("bedroom.raw");
            int value;
            int countHeight =  0;
            int countWidth = 0;
            while( (value = input.read()) != -1 ){
                if (countWidth==width){
                    countWidth=0;
                    countHeight++;
                }
                image[countHeight][countWidth] = value;
                countWidth++;
            }
            float [][] kernal = {
                {0.111f,0.111f,0.111f},
                {0.111f,0.111f,0.111f},
                {0.111f,0.111f,0.111f}
            };
            int [][]newImage = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    newImage[i][j] = image[i][j];
                    if (i == 0 || j % width == 0 || j == width-1 || i == height-1){
                        newImage[i][j]=255;
                    }
                }
            }

            int sum = 0;
            for (int i = 1; i < height - 1; i++) {
                for (int j = 1; j < width - 1; j++) {
                    sum=0;
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            sum = (int)(kernal[k+1][l+1]*image[i-k][j-l])+sum;
                        }
                    }
                    if(sum < 0){
                        newImage[i][j] = 0;
                    }
                    else if(sum>255){
                        newImage[i][j] = 255;
                    }else{
                        newImage[i][j] = sum;
                    }
                }
            }
            input.close();

            FileOutputStream output = new FileOutputStream("LowPass_BedRoom.raw");
            for(int i = 0; i<height; i++){
                for (int j = 0; j < width; j++) {
                    output.write(newImage[i][j]);
                }
            }
            output.close();

        }catch(IOException ex){
            System.out.println("Error");
        }
    }
}


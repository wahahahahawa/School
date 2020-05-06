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

public class convolution {
    public static void main (String [] args) {
        
        int height = 600;
        int width = 600;
        int countHeight = 0 , countWidth = 0;
        int[][] data = new int[height][width];
        try{
            File f = new File("bedroom.raw");
            FileInputStream ism = new FileInputStream(f);
            int value;
            while((value = ism.read()) != -1){
                if(countWidth == width){
                    countWidth = 0;
                    countHeight++;
                }
                
                data[countHeight][countWidth] = value;
                countWidth++;
            }

                int[][] kernal = {
                        {-1 , 0 , 1},
                        {-2 , 0 , 2},
                        {-1 , 0 , 1}
                };

                int[][] new_data = new int[height][width];

                int count = 0;
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        new_data[i][j] = data[i][j];
                        if(i == 0 || j % width == 0 || j == width-1 || i == height-1){
                            new_data[i][j] = 255;
                        }
                    }
                }


                int sum = 0;
                for(int i = 1; i < height - 1; i++){
                    for(int j = 1; j < width - 1; j++){
                        //System.out.print(data[i][j] + " ");
                        sum = 0;
                        for(int a = -1; a < 2; a++){
                            for(int b = -1; b < 2; b++){
                                sum = (kernal[a + 1][b + 1] * data[i - a][j - b]) + sum;
                            }
                        }
                        if(sum < 0){
                            new_data[i][j] = 0;
                        }
                        else if(sum > 255){
                            new_data[i][j] = 255;
                        }
                        else {
                            new_data[i][j] = sum;
                        }

                    }

                }

                FileOutputStream output = new FileOutputStream("Convolution_Bedroom.raw");
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        output.write(new_data[i][j]);
                    }
                }
                output.close();

            }

            catch(Exception e){
                System.out.println("Exception: " + e);
            }
    }
}



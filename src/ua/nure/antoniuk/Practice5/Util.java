package ua.nure.antoniuk.Practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Max on 28.11.2017.
 */
public class Util {

    public static String getInput(String fileName){
        StringBuilder sb = new StringBuilder();

        try {
            Scanner scanner = new Scanner(new File(fileName), "Cp1251");
            while(scanner.hasNextLine()){
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            return sb.toString().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    public static void generateFileMatrix(int n, int m, int max) {
    	
    }
    
    public static String readFile(String path){
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static int[][] readMatrix(String fileName){
    	String file = readFile(fileName);
    	String[] rows = file.split(System.lineSeparator());
    	for(String s : rows) {
    		s.replace(System.lineSeparator(), "");
    	}
    	String[] temp = rows[0].split(" ");
    	int[][] intMatr = new int[rows.length][temp.length];
    	for(int i = 0; i < rows.length; i++) {
    		String[] tmp = rows[i].split(" ");
    		for(int j = 0; j < tmp.length; j++) {
    			intMatr[i][j] = Integer.parseInt(tmp[j]);
    		}
    	}
    	return intMatr;
    }
    
    public static int[][] readMatrix2(String fileName){
    	Scanner scanner = null;
		try {
			scanner = new Scanner(new File("part4.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	List<String> list = new ArrayList<>();
    	while(scanner.hasNextLine()) {
    		list.add(scanner.nextLine());
    	}
    	
    	String[] temp = list.get(0).split(" ");
    	int[][] intMatr = new int[list.size()][temp.length];
    	for(int i = 0; i < list.size(); i++) {
    		String[] tmp = list.get(i).split(" ");
    		for(int j = 0; j < tmp.length; j++) {
    			intMatr[i][j] = Integer.parseInt(tmp[j]);
    		}
    	}
    	return intMatr;
    }
}

package tsvParserPackage;

import java.io.*;

public class TsvParser {
    private TsvParser() {
        // Private Constructor
    }

    static void parseTsv(String target_file, String output_file, int[] target_column_num) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
            String tar;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                String arr[] = tar.split("\t");
                String parsed[] = new String[50];
                for(int i=0; i<target_column_num.length; i++)
                    parsed[i] = arr[target_column_num[i]];
                for(int i=0; i< target_column_num.length; i++){
                    if(!parsed[i].equals("\\N"))
                        parsed[i] = parsed[i].replace('\\', 't');
                    if(parsed[i].equals(""))
                        parsed[i] = "\\N";
                }
                String parsedString = "";
                for(int i=0; i< target_column_num.length; i++)
                    parsedString += (parsed[i] + "\t");
                parsedString = parsedString.substring(0, parsedString.length()-1);
                bw.write(parsedString + "\n");
            }
            br.close();
            bw.close();
        } catch (FileNotFoundException e){
            System.out.println("Target file not found");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("IOException occurred");
            e.printStackTrace();
        } finally {
            System.out.println("Parsing " + target_file + " terminated");
        }
    }
    static void errorCheck(String target_file, int num_of_column){
        try {
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            String tar;
            boolean flag = true;
            int cnt = 0;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                String[] arr = tar.split("\t");
                if(arr.length!=num_of_column){
                    flag = false;
                    System.out.println("Invalid row: " + (cnt+1));
                    for(int i=0; i<arr.length; i++)
                        System.out.println(i + " : " + arr[i]);
                }
                cnt++;
            }
            br.close();
            if(flag)
                System.out.println("No error on " + target_file);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.println("Error check on " + target_file + " terminated");
        }
    }

}

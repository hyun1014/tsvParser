package tsvParserPackage;

import java.io.*;

public class BaseTsvParser {
    private BaseTsvParser() {
        // Private Constructor
    }
    // tsv 파일에서 원하는 컬럼들만 뽑아내기
    static void parseTsv(String target_file, String output_file, int[] target_column_num) {
        // 대상 파일명, output 파일, 뽑아올 column 번호 목록(0부터 시작함)
        System.out.println("Parsing " + target_file + " started");
        try{
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
            String tar;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                // column 쪼개기(원본)
                String arr[] = tar.split("\t");
                // 뽑아온 컬럼 목록
                String parsed[] = new String[50];
                for(int i=0; i<target_column_num.length; i++){
                    // 끝부분을 뽑아와야 하는데 아예 빈 문자열이라 arr[]에 제대로 안들어가는 경우가 있음. 그럴 경우 null 처리함
                    // ex) User nickname 컬럼이 마지막 21번 컬럼인데 빈 문자열이면 그냥 개행으로 처리되어 arr[]에 안들어감
                    if(target_column_num[i]>=arr.length)
                        parsed[i] = "\\N";
                    else
                        parsed[i] = arr[target_column_num[i]];
                }
                for(int i=0; i< target_column_num.length; i++){
                    // 백슬래시(\) 이걸 개행으로 인식해서 자꾸
                    // Mysql error 1261 (doesn't contain data for all columns)
                    // 이렇게 에러 떠서 그냥 t 문자로 다 바꿔버림. 그냥 덤프 떠오는거니까 뭐 큰 문제는 없을듯
                    if(!parsed[i].equals("\\N"))
                        parsed[i] = parsed[i].replace('\\', 't');
                    // 빈 문자열은 제대로 인식 못하거나 에러 떠서 NULL 값으로 바꿔버림.
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
    // 에러 검증(가끔 컬럼 개수가 안맞는 row가 있는 경우도 있으므로. 이 경우 mysql에서 infile load할 때 에러 뜸)
    static void errorCheck(String target_file, int num_of_column){
        try {
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            String tar;
            boolean errorFlag = false;
            int cnt = 0;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                String[] arr = tar.split("\t");
                // 컬럼 개수가 안맞는 경우 해당 row들 출력
                if(arr.length!=num_of_column){
                    errorFlag = true;
                    System.out.println("Invalid row: " + (cnt+1));
                    for(int i=0; i<arr.length; i++)
                        System.out.println(i + " : " + arr[i]);
                }
                cnt++;
            }
            br.close();
            if(!errorFlag)
                System.out.println("No error on " + target_file);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.println("Error check on " + target_file + " terminated");
        }
    }

}

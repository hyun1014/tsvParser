package tsvParserPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TsvReader {
    // 해당 파일의 특정 로우를 검사한다. (1부터 시작)
    static void parseRow(String target_file, int tar_row_number){
        try {
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            String tar;
            int cnt=0;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                if(cnt==tar_row_number-1){
                    String[] arr = tar.split("\t");
                    for(int i=0; i<arr.length; i++)
                        System.out.println((i+1) + " : " + arr[i]);
                    // /t 문자 검사용
                    System.out.println(tar.replace('\t', '$'));
                    return;
                }
                cnt++;
            }
            // 해당 row number를 가진 row가 없을 때에 (타겟 row 번호가 파일의 전체 row 개수보다 클 경우)
            System.out.println("Total row count: " + cnt);
            System.out.println("not found");
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    // 파일의 row 개수 검사
    static int rowCount(String target_file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            String tar;
            int cnt=0;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                cnt++;
            }
            System.out.println("Row count: " + cnt);
            br.close();
            return cnt;
        } catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }
    // column 개수는 통일되어야하는데, 아닌 경우가 간혹 있었음
    // /t 처리가 잘못된 것 같은데, column 개수가 통일된 상태인지 확인
    static void checkColumnCount(String target_file){
        List<String[]> sampleRowList = new ArrayList<>(); // sample row 목록
        List<Integer> sampleRowNumber = new ArrayList<>(); // sample로 들어간 것들이 몇번째 row인지
        Set<Integer> columnCountSet = new LinkedHashSet<>(); // 컬럼 개수 종류들 ex) 45, 46 의 경우 컬럼 45개짜리, 컬럼 46개짜리 row, 총 2가지 종류가 있는
        try{
            BufferedReader br = new BufferedReader(new FileReader(target_file));
            int cnt = 0;
            String tar;
            while(true){
                tar = br.readLine();
                if(tar==null)
                    break;
                String[] arr = tar.split("\t");
                if(columnCountSet.add(arr.length)){
                    sampleRowList.add(arr);
                    sampleRowNumber.add(cnt+1);
                }
                cnt++;
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.print("Column count: ");
        for(int rowCount : columnCountSet)
            System.out.print(rowCount + ", ");
        System.out.println();

        System.out.print("Sample row number: ");
        for(int rowNum : sampleRowNumber)
            System.out.print(rowNum + ", ");
        System.out.println();

        System.out.println("Row example");
        for(String[] arr : sampleRowList)
            for(int i=0; i<arr.length; i++)
                System.out.println((i+1) + " : " + arr[i]);
        System.out.println();
    }
}

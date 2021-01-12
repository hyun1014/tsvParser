package tsvParserPackage;

public interface TableTsvParser {
    // tsv 파일에서 원하는 컬럼들만 뽑아내기
    void parseTsv(String target_file, String output_file);
    // 에러 검증(가끔 컬럼 개수가 안맞는 row가 있는 경우도 있으므로. 이 경우 mysql에서 infile load할 때 에러 뜸)
    void errorCheck(String target_file);
}

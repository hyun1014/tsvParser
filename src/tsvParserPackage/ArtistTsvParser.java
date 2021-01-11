package tsvParserPackage;

public class ArtistTsvParser {
    static void parseTsv(String target_file, String output_file){
        // artist_no, artist_nm, artist_additional_nm
        TsvParser.parseTsv(target_file, output_file, new int[]{0, 30, 31});
    }
    static void errorCheck(String target_file){
        TsvParser.errorCheck(target_file, 3);
    }
}

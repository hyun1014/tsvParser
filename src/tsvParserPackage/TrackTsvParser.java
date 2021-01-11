package tsvParserPackage;

public class TrackTsvParser {
    static void parseTrackTsv(String target_file, String output_file) {
        TsvParser.parseTsv(target_file, output_file, new int[]{0,1,45,46});
    }
    static void errorCheck(String target_file){
        TsvParser.errorCheck(target_file, 4);
    }
}

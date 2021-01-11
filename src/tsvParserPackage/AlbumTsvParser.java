package tsvParserPackage;

public class AlbumTsvParser {
    // album_no, album_nm, album_additional_nm
    static void parseAlbumTsv(String target_file, String output_file){
        TsvParser.parseTsv(target_file, output_file, new int[]{0,45,46});
    }
    static void errorCheck(String target_file) {
        TsvParser.errorCheck(target_file, 3);
    }
}

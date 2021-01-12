package tsvParserPackage;

public class ArtistTableTsvParser implements TableTsvParser {
    public void parseTsv(String target_file, String output_file){
        // artist_no, artist_nm, artist_additional_nm
        BaseTsvParser.parseTsv(target_file, output_file, new int[]{0, 30, 31});
    }
    public void errorCheck(String target_file){
        BaseTsvParser.errorCheck(target_file, 3);
    }
}

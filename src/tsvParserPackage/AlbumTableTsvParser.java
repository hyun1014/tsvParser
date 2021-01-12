package tsvParserPackage;

public class AlbumTableTsvParser implements TableTsvParser {
    public void parseTsv(String target_file, String output_file){
        // album_no, album_nm, album_additional_nm
        BaseTsvParser.parseTsv(target_file, output_file, new int[]{0,45,46});
    }
    public void errorCheck(String target_file) {
        BaseTsvParser.errorCheck(target_file, 3);
    }
}

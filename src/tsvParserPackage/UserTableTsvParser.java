package tsvParserPackage;

public class UserTableTsvParser implements TableTsvParser{
    @Override
    public void parseTsv(String target_file, String output_file) {
        // track_no, album_no, track_nm, track_additional_nm
        BaseTsvParser.parseTsv(target_file, output_file, new int[]{0,20});
    }
    @Override
    public void errorCheck(String target_file){
        BaseTsvParser.errorCheck(target_file, 2);
    }
}

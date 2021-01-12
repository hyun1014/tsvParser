package tsvParserPackage;

// album_artist, table_artist같은 table에 사용함. 다들 column 형식이 같아서 그냥 class 하나로 통일
public class WithArtistTableTsvParser implements TableTsvParser{
    @Override
    public void parseTsv(String target_file, String output_file){
        // album_no(pk), artist_no
        BaseTsvParser.parseTsv(target_file, output_file, new int[]{0,1});
    }
    @Override
    public void errorCheck(String target_file){
        BaseTsvParser.errorCheck(target_file, 2);
    }
}

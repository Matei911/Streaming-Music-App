import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseStreams {
    private static ParseStreams instance;
    private ParseStreams() {}
    public static ParseStreams getInstance() {
        if (instance == null) {
            instance = new ParseStreams();
        }
        return instance;
    }
    public ArrayList<Streams> streamsList = new ArrayList<>();
    public void ReadStreams(String filePath) throws IOException, CsvValidationException {
        streamsList.clear();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        reader.readNext(); // this will read the first line
        String[] line;
        while ((line = reader.readNext()) != null) {
            Streams streams = new StreamsBuilder()
                    .withType(Integer.parseInt(line[0]))
                    .withID(Integer.parseInt(line[1]))
                    .withGenre(Integer.parseInt(line[2]))
                    .withNoOfStreams(Long.parseLong(line[3]))
                    .withStreamerID(Integer.parseInt(line[4]))
                    .withLength(Long.parseLong(line[5]))
                    .withDateAdded(Long.parseLong(line[6]))
                    .withName(line[7]).buildStreams();
            streamsList.add(streams);
        }
    }
}

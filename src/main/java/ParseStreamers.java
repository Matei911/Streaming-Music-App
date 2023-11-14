import java.io.*;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ParseStreamers {
    private static ParseStreamers instance;

    private ParseStreamers() {
    }

    public static ParseStreamers getInstance() {
        if (instance == null) {
            instance = new ParseStreamers();
        }
        return instance;
    }

    public ArrayList<Streamers> streamersList = new ArrayList<>();

    public void ReadStreamers(String filePath) throws IOException, CsvValidationException {
        streamersList.clear();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        reader.readNext(); // this will read the first line
        String[] line;
        while ((line = reader.readNext()) != null) {
            Streamers streamers = new UserStreamersBuilder()
                    .withType(Integer.parseInt(line[0]))
                    .withID(Integer.parseInt(line[1]))
                    .withName(line[2])
                    .buildStreamers();
            streamersList.add(streamers);
        }
    }
}




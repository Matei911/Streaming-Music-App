import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseUser {
    private static ParseUser instance;
    private ParseUser() {}
    public static ParseUser getInstance() {
        if (instance == null) {
            instance = new ParseUser();
        }
        return instance;
    }
    public ArrayList<User> usersList = new ArrayList<>();
    public void ReadUsers(String filePath) throws IOException, CsvValidationException {
        usersList.clear();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        reader.readNext(); // this will read the first line
        String[] line;
        while ((line = reader.readNext()) != null) {
        User user = new UserStreamersBuilder()
                    .withID(Integer.parseInt(line[0]))
                    .withName(line[1])
                    .withIDList(line[2])
                    .buildUser();
            usersList.add(user);
        }
    }
}

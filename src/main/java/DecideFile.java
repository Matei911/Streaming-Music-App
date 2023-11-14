import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public class DecideFile {
    private static final String PATH = "src/main/resources/";

    public static void decideFile(final String[] args) {
        ParseStreamers parseStreamers = ParseStreamers.getInstance();
        ParseStreams parseStreams = ParseStreams.getInstance();
        ParseUser parseUser = ParseUser.getInstance();
        ParseCommands parseCommands = new ParseCommands();
        try {
            parseStreamers.ReadStreamers(PATH + args[0]);
            parseStreams.ReadStreams(PATH + args[1]);
            parseUser.ReadUsers(PATH + args[2]);
            parseCommands.ReadCommands(args[3], parseStreams, parseStreamers, parseUser);
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}

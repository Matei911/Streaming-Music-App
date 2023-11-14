import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseCommands {
    private static String getString(String[] parts) {
        //metoda care preia stringul din linia de comanda
        String name = parts[6];
        for (int i = 7; i < parts.length; i++) {
            name = name + " " + parts[i];
        }
        return name;
    }
    private static void firstLine(boolean firstLine) {
        //pune un newline dupa fiecare comanda
        if (!firstLine) {
            System.out.println();
        }
    }
    private static boolean isFirstLineIsAdd(boolean firstLine, boolean firstLineIsAdd) {
        //in caz ca prima comanda era ADD imi punea un newline in plus
        if (firstLine){
            firstLineIsAdd = true;
        }
        return firstLineIsAdd;
    }

    public void ReadCommands(String filemname, ParseStreams streams, ParseStreamers streamers, ParseUser users) throws IOException {
        String CommandPath = "src/main/resources/" + filemname;
        BufferedReader reader = new BufferedReader(new FileReader(CommandPath));
        String line;
        Command command;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");

            boolean firstLineIsAdd = false;
            switch (parts[1]) {
                case "LIST":
                    command = new ListCommand(streams.streamsList, streamers.streamersList, users.usersList, Integer.parseInt(parts[0]));
                    firstLine(firstLine);
                    break;
                case "ADD":
                    firstLineIsAdd = isFirstLineIsAdd(firstLine, firstLineIsAdd);
                    String name = getString(parts);
                    command = new AddCommand(streams.streamsList, Integer.parseInt(parts[0]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), (long) Integer.parseInt(parts[5]), name);
                    break;
                case "DELETE":
                    command = new DeleteCommand(streams.streamsList, Integer.parseInt(parts[0]), Integer.parseInt(parts[2]));
                    break;
                case "LISTEN":
                    command = new ListenCommand(users.usersList, streams.streamsList, Integer.parseInt(parts[0]), Integer.parseInt(parts[2]));
                    break;
                case "RECOMMEND":
                    firstLine(firstLine);
                    command = new RecommendCommand(streams.streamsList, streamers.streamersList, users.usersList, Integer.parseInt(parts[0]), parts[2]);
                    break;
                case "SURPRISE":
                    firstLine(firstLine);
                    command = new SurpriseCommand(streams.streamsList, streamers.streamersList, users.usersList, Integer.parseInt(parts[0]), parts[2]);
                    break;
                default:
                    command = null;
                    break;
            }
            if (command != null) {
                command.execute();
                firstLine = false;
            }
            if (firstLineIsAdd) {
                firstLine = true;
            }
        }
    }

}
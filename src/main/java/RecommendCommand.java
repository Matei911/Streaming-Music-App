import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecommendCommand implements Command {

    private final ArrayList<Streams> streamsList;
    private final ArrayList<Streamers> streamersList;
    private final ArrayList<User> usersList;
    private final int userID;
    private final String name;

    public RecommendCommand(ArrayList<Streams> streamsList, ArrayList<Streamers> streamersList, ArrayList<User> usersList, int userID, String type) {
        this.streamsList = streamsList;
        this.streamersList = streamersList;
        this.usersList = usersList;
        this.userID = userID;
        this.name = type;
    }

    public void execute() {
        int type = getStreamType();
        Set<Integer> streamerIDs = getStreamerIDs();
        List<Streams> recommendedStreams = getRecommendedStreams(type, streamerIDs);
        printRecommendedStreams(recommendedStreams);
    }

    private int getStreamType() {
        int type = 0;
        switch (name) {
            case "SONG":
                type = 1;
                break;
            case "PODCAST":
                type = 2;
                break;
            case "AUDIOBOOK":
                type = 3;
                break;
        }
        return type;
    }

    private Set<Integer> getStreamerIDs() {
        //verifica id-ul userului si adauga Streamurile facute de acel user pentru a nu recomanda aceleasi streamuri
        Set<Integer> streamerIDs = new HashSet<>();
        for (User user : usersList) {
            if (user.getId() == userID) {
                for (int i = 0; i < user.getStreams().size(); i++) {
                    for (Streams stream : streamsList) {
                        if (stream.getId() == Integer.parseInt(String.valueOf(user.getStreams().get(i)))) {
                            streamerIDs.add(stream.getStreamerID());
                        }
                    }
                }
            }
        }
        return streamerIDs;
    }

    private List<Streams> getRecommendedStreams(int type, Set<Integer> streamerIDs) {
        //adauga streamurile din care ar trebuii sa se faca recomandarea
        List<Streams> recommendedStreams = new ArrayList<>();
        for (User user : usersList) {
            if (user.getId() == userID)
                for (Streams stream : streamsList) {
                    boolean shouldPrint = true;
                    for (int i = 0; i < user.getStreams().size(); i++)
                        if (stream.getId() == Integer.parseInt(String.valueOf(user.getStreams().get(i)))) {
                            shouldPrint = false;
                            break;
                        }
                    if (shouldPrint && stream.getStreamType() == type && streamerIDs.contains(stream.getStreamerID())) {
                        recommendedStreams.add(stream);
                    }
                }
        }
        return recommendedStreams;
    }

    private void printRecommendedStreams(List<Streams> recommendedStreams) {
        System.out.print("[");
        int count = recommendedStreams.size();
        if (count > 0) {
            for (int i = count - 1; i >= 0; i--) {
                Streams stream = recommendedStreams.get(i);
                for (Streamers streamer : streamersList) {
                    if (streamer.getId() == Integer.parseInt(String.valueOf(stream.getStreamerID()))) {
                        count--;
                        ListCommand command = new ListCommand(streamsList, streamersList, usersList, streamer.getId());
                        command.afis(stream.getId(), stream.getName(), streamer.getName(), stream.getNoOfStreams(), stream.getLength(), stream.getDateAdded());
                        if (count > 0) {
                            System.out.print(",");
                        }
                    }
                }
            }
        }
        System.out.print("]");
    }
}

import java.util.*;

public class SurpriseCommand implements Command {
    private final ArrayList<Streams> streamsList;
    private final ArrayList<Streamers> streamersList;
    private final ArrayList<User> usersList;
    private final int userID;
    private final String name;

    public SurpriseCommand(ArrayList<Streams> streamsList, ArrayList<Streamers> streamersList, ArrayList<User> usersList, int userID, String type) {
        this.streamsList = streamsList;
        this.streamersList = streamersList;
        this.usersList = usersList;
        this.userID = userID;
        this.name = type;
    }

    public void execute() {
        int type = getStreamType();
        Set<Integer> streamerIDs = getStreamerIDs();
        List<Streams> recommendedStreams = getSurpriseStreams(type, streamerIDs);
        printSurpriseStreams(recommendedStreams);
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

    private List<Streams> getSurpriseStreams(int type, Set<Integer> streamerIDs) {
        //este similara cu getRecommendedStreams
        List<Streams> recommendedStreams = new ArrayList<>();
        for (User user : usersList) {
            if (user.getId() == userID) {
                for (Streams stream : streamsList) {
                    boolean shouldPrint = true;
                    for (int i = 0; i < user.getStreams().size(); i++) {
                        if (stream.getId() == Integer.parseInt(String.valueOf(user.getStreams().get(i)))) {
                            shouldPrint = false;
                            break;
                        }
                    }
                    if (shouldPrint && stream.getStreamType() == type && !streamerIDs.contains(stream.getStreamerID())) {
                        recommendedStreams.add(stream);
                    }
                }
            }
        }
        //se sorteaza in functie de data adaugarii si numarul de streams
        recommendedStreams.sort((b, a) -> {
            int dateComparison = b.getDateAdded().compareTo(a.getDateAdded());
            if (dateComparison == 0) {
                return Integer.compare(Math.toIntExact(b.getNoOfStreams()), Math.toIntExact(a.getNoOfStreams()));
            }
            return dateComparison;
        });
        return recommendedStreams;
    }


    private void printSurpriseStreams(List<Streams> recommendedStreams) {
        //sunt afisate maxim 3 streamuuri
        String[] names = new String[recommendedStreams.size()];
        System.out.print("[");
        if (recommendedStreams.size() > 3) {
            recommendedStreams = recommendedStreams.subList(1, 4);
        }
        int count = recommendedStreams.size();
        for (int i = 0; i < recommendedStreams.size(); i++)
            names[i] = recommendedStreams.get(i).getName();
        if (count > 0) {
            for (int i = count - 1; i >= 0; i--) {
                Streams stream = recommendedStreams.get(i);
                for (Streamers streamer : streamersList) {
                    if (streamer.getId() == Integer.parseInt(String.valueOf(stream.getStreamerID()))) {
                        count--;
                        ListCommand command = new ListCommand(streamsList, streamersList, usersList, streamer.getId());
                        command.afis(stream.getId(), names[i], streamer.getName(), stream.getNoOfStreams(), stream.getLength(), stream.getDateAdded());
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

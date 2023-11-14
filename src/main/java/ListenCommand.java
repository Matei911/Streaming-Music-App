import java.util.ArrayList;

public class ListenCommand implements Command {
    private final ArrayList<User> usersList;
    private final ArrayList<Streams> streamsList;
    private final int id;
    private final int streamerID;

    public ListenCommand(ArrayList<User> usersList,ArrayList<Streams> streamsList, int id, int streamerID) {

        this.usersList = usersList;
        this.streamsList = streamsList;
        this.id = id;
        this.streamerID = streamerID;
    }

    public void execute() {
        for (User user : usersList) {
            if (user.getId() == id) {
                user.getStreams().add(streamerID);
                //increment the no of streams
                for (Streams streamers : streamsList) {
                    if (streamers.getId() == streamerID) {
                        streamers.setNoOfStreams(streamers.getNoOfStreams() + 1);
                    }
                }
            }
        }
    }
}

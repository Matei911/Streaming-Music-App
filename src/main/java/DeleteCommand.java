import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final ArrayList<Streams> streamsList;
    private final int streamerID;
    private final int id;

    public DeleteCommand(ArrayList<Streams> streamsList, int streamerID, int id) {
        this.streamsList = streamsList;
        this.streamerID = streamerID;
        this.id = id;
    }

    public void execute() {
        for (int i = 0; i < streamsList.size(); i++) {
            if (streamsList.get(i).getId() == id && streamsList.get(i).getStreamerID() == streamerID) {
                streamsList.remove(i);
            }
        }
    }
}

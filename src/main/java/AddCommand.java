import java.util.ArrayList;

public class AddCommand implements Command{
    private final ArrayList<Streams> streamsList;
    private final int streamerID;
    private final int streamType;
    private final int id;

    private final int streamGenre;
    private final Long length;
    private final String name;

public AddCommand(ArrayList<Streams> streamsList, int id, int streamerID, int streamType, int streamGenre, Long length, String name) {
        this.streamsList = streamsList;
        this.id = id;
        this.streamerID = streamerID;
        this.streamType = streamType;
        this.streamGenre = streamGenre;
        this.length = length;
        this.name = name;
    }

    public void execute() {
        Streams stream = new Streams();
        stream.setId(streamType);
        stream.setStreamerID(id);
        stream.setStreamType(streamerID);
        stream.setStreameGenre(1);
        stream.setLength(length);
        stream.setName(name);
        stream.setNoOfStreams(0L);
        stream.setDateAdded(System.currentTimeMillis() / 1000L);
        streamsList.add(0, stream);
    }
}

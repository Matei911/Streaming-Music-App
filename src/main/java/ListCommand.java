import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ListCommand implements Command {
    private final ArrayList<Streams> streamsList;
    private final ArrayList<Streamers> streamersList;
    private final ArrayList<User> usersList;
    private final int id;

    public ListCommand(ArrayList<Streams> streamsList, ArrayList<Streamers> streamersList, ArrayList<User> usersList, int id) {
        this.streamsList = streamsList;
        this.streamersList = streamersList;
        this.usersList = usersList;
        this.id = id;
    }

    public void afis(Integer id, String name, String streamerName, Long noOfStreams, Long length, Long dateAdded) {
        //formatez timpul si data
        Date date = new Date(dateAdded * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time;
        Duration duration = Duration.ofSeconds(length);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);
        long secs = duration.getSeconds();
        if (hours != 0) {
            time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        } else {
            time = String.format("%02d:%02d", minutes, secs);
        }
        System.out.print("{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"streamerName\":\"" + streamerName + "\",\"noOfListenings\":\"" + noOfStreams + "\",\"length\":\"" + time + "\",\"dateAdded\":\"" + dateFormat.format(date) + "\"}");
    }

    public void iterateForStreamer(int id) {
        boolean found = false;
        for (Streamers streamers : streamersList) {
            if (streamers.getId() == id) {
                for (int i = streamsList.size() - 1; i >= 0; i--) {
                    Streams streamer = streamsList.get(i);
                    if (streamer.getStreamerID() == id) {
                        if (!found) {
                            System.out.print("[");
                            found = true;
                        } else {
                            System.out.print(",");
                        }
                        afis(streamer.getId(), streamer.getName(), streamers.getName(), streamer.getNoOfStreams(), streamer.getLength(), streamer.getDateAdded());
                    }
                }
            }
        }
        if (found) {
            System.out.print("]");
        }
    }

    public void iterateForUser(int id) {
        for (int i = streamsList.size() - 1; i >= 0; i--) {
            Streams streamer = streamsList.get(i);
            if (streamer.getId() == id) {
                int streamerId = streamer.getStreamerID();
                for (Streamers streamers : streamersList) {
                    if (streamers.getId() == streamerId) {
                        afis(streamer.getId(), streamer.getName(), streamers.getName(), streamer.getNoOfStreams(), streamer.getLength(), streamer.getDateAdded());
                    }
                }
            }
        }
    }

    public void execute() {
        iterateForStreamer(id);
        for (User user : usersList) {
            if (user.getId() == id) {
                List<Integer> streams = user.getStreams();
                if (!streams.isEmpty()) {
                    System.out.print("[");
                    for (int i = 0; i < streams.size(); i++) {
                        Integer streamerId = streams.get(i);
                        iterateForUser(streamerId);
                        if (i != streams.size() - 1) {
                            System.out.print(",");
                        }
                    }
                    System.out.print("]");
                }
            }
        }
    }
    /**
     * Am folosit doua iterari pentru ca nu stiu daca id-ul primit este de tipul user sau streamer
     * Pentru fiecare caz am facut o metoda separata si o singura metoda de afisare a datelor
     */
}


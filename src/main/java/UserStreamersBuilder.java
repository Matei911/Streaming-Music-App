import java.util.ArrayList;
import java.util.List;

public class UserStreamersBuilder {
    private final User user = new User();
    private final Streamers streamers = new Streamers();
    private final List<Integer> streams = new ArrayList<>();
    public UserStreamersBuilder withID(Integer id) {
        streamers.setId(id);
        user.setId(id);
        return this;
    }

    public UserStreamersBuilder withName(String name) {
        streamers.setName(name);
        user.setName(name);
        return this;
    }
    public UserStreamersBuilder withType(Integer type) {
        streamers.setType(type);
        return this;
    }
    public UserStreamersBuilder withIDList(String StreamIDList){
        String[] streamIDList = StreamIDList.split(" ");
        for (String s : streamIDList) {
            streams.add(Integer.parseInt(s));
        }
        return this;
    }
    public Streamers buildStreamers() {
        return streamers;
    }
    public User buildUser() {
        user.setStreams(this.streams);
        return user;
    }

}

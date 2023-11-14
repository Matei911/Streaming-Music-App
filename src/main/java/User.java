import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private List<Integer> streams = new ArrayList<>();
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getStreams() {
        return streams;
    }

    public void setStreams(List<Integer> streams) {
        this.streams = streams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class StreamsBuilder {
    private final Streams streams = new Streams();
    public StreamsBuilder withID(Integer id) {
        streams.setId(id);
        return this;
    }
    public StreamsBuilder withName(String name) {
        streams.setName(name);
        return this;
    }
    public StreamsBuilder withType(Integer type) {
        streams.setStreamType(type);
        return this;
    }

    public StreamsBuilder withGenre(Integer genre) {
        streams.setStreameGenre(genre);
        return this;
    }
    public StreamsBuilder withNoOfStreams(Long noOfStreams) {
        streams.setNoOfStreams(noOfStreams);
        return this;
    }
    public StreamsBuilder withStreamerID(Integer streamerID) {
        streams.setStreamerID(streamerID);
        return this;
    }
    public StreamsBuilder withLength(Long length) {
        streams.setLength(length);
        return this;
    }
    public StreamsBuilder withDateAdded(Long dateAdded) {
        streams.setDateAdded(dateAdded);
        return this;
    }
    public Streams buildStreams() {
        return streams;
    }
}

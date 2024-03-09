# Streaming-Music-App

The Streaming-Music-App leverages a sophisticated algorithm to suggest music tracks based on the user's listening history, offering a highly personalized and engaging experience. In addition to music recommendations, the application supports functionalities to listen, add, or delete songs, catering to a wide range of user preferences.

## Design Patterns

To ensure a robust and maintainable codebase, the following four design patterns have been implemented:

- **Singleton:** Applied in classes where data is appended to `ArrayLists`, specifically in scenarios where only a single instance of a class is required throughout the application.
- **Facade:** Utilized to simplify the interaction with `ArrayLists` by hiding the complexities involved in data addition and command implementation.
- **Command:** Employed to handle the execution of six distinct commands within the program: LIST, ADD, DELETE, LISTEN, RECOMMEND, and SURPRISE.
- **Builder:** Used for creating objects with their specific fields in a more understandable and cleaner way, particularly for `Streams`, `Streamers`, and `Users`.

## Classes

The class structure is designed to streamline the application's functionality and ensure clear separation of concerns:

### Facade Pattern
- **DecideFile:** Acts as a facade, encapsulating method calls to add data from each input into `ArrayLists`, simplifying the data handling process.

### Parsing Classes
- **ParseStreams, ParseStreamers, ParseUsers:** These classes are dedicated to extracting data from CSV files and populating `ArrayLists` accordingly. They are instrumental in the initial data setup.
- **ParseCommands:** Unlike the other parsing classes, this one checks text files and allocates the results to specific command classes for execution.

### Builder Classes
- **BuildStreams, BuildStreamers, BuildUsers:** These classes contain mechanisms for adding fields to objects, adhering to the Builder pattern to facilitate object creation with specific attributes.

### Command Classes
- **ListCommand, AddCommand, DeleteCommand, ListenCommand, RecommendCommand, SurpriseCommand:** Named after the commands they execute, these classes are central to the application's functionality, allowing users to interact with the music library through various actions.

## Conclusion

The Streaming-Music-App is a testament to thoughtful software design and development, integrating key design patterns to enhance code readability, maintainability, and user experience. Through its algorithm-driven music suggestions and comprehensive command set, it stands out as a personalized music listening platform.

# Streaming-Music-App

The app utilizes an algorithm to suggest music based on the user’s listening history, providing a
personalized and engaging experience. The app also allows you to listen, add, or delete different songs.

Design Patterns

The four design patterns I have chosen to implement are:

Singleton
Facade
Command
Builder

The rationale behind selecting these design patterns is as follows:

-Singletons were used in classes where data is added to ArrayLists, classes that are not needed elsewhere in the program.

-The Facade was employed to "hide" the way data is added to the respective ArrayLists and to conceal the implementation of commands.

-Command is used to execute the six commands used in the program (LIST, ADD, DELETE, LISTEN, RECOMMEND, SURPRISE).

-Builder is utilized to add each object with its specific fields to the list in a more easily understandable manner (Streams, Streamers, Users).

Classes:

DecideFile is a class that acts as a facade. Inside it, there are only method calls to add data from each input into ArrayLists.

Classes containing "Parse" at the beginning of their names were used solely to extract data from the received CSV files and add them to ArrayLists (ParseStreams, ParseStreamers, ParseUsers).

An exception to this rule is the ParseCommands class, which checks text files and distributes the result to command classes.

Classes ending with "Build" have the mechanism for adding fields to an object (BuildStreams, BuildStreamers, BuildUsers).

Classes starting with "Command" were used to execute the commands received from the user (ListCommand, AddCommand, DeleteCommand, ListenCommand, RecommendCommand, SurpriseCommand).


# README for FSM Visualizer

## Overview

FSM Visualizer is a Java application designed to generate visual representations of Finite State Machines (FSM) from JSON files. The visualizer outputs an image of the FSM, where states are represented as nodes and transitions are depicted as directed edges between these nodes. Initial states are highlighted in green, final states in red, and transitions include labels to indicate the action and caller.

## Requirements

- Java JDK 8 or later
- Maven (for managing dependencies)
- The `org.json` library for JSON parsing
- The `guru.nidi.graphviz` library for generating and rendering the FSM graph

## Setup

Before running the FSM Visualizer, ensure you have Maven installed and configured on your system. Then, add the following dependencies to your `pom.xml` file to include the necessary libraries:

```xml
<dependencies>
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>
    <dependency>
        <groupId>guru.nidi</groupId>
        <artifactId>graphviz-java</artifactId>
        <version>0.18.1</version>
    </dependency>
</dependencies>
```

## Running the Application

Compile the `FSMVisualizer` class, and run it using the Java command-line tool, providing the source JSON file path as the first argument and the output image file path as the second argument.

```bash
java FSMVisualizer <source_json_file_path> <output_image_file_path>
```

For example:

```bash
java FSMVisualizer "D:/WorkSpace/Parser_Java/InputFiles/Examples/simpleMarketPlace_global.json" "D:/WorkSpace/Parser_Java/Output/simpleMarketPlace_global.png"
```

## JSON File Format

The source JSON file should adhere to the following structure:

```json
{
  "initialState": "StateName",
  "finalStates": ["FinalState1", "FinalState2"],
  "transitions": [
    {
      "from": "StateA",
      "to": "StateB",
      "caller": {"callerKey": ["callerValue"]},
      "actionLabel": "ActionName",
      "newParticipants": {"participantKey": "participantValue"}
    }
    // Add more transitions as needed
  ]
}
```

- `initialState` specifies the name of the initial state.
- `finalStates` is an array of names of final states.
- Each item in the `transitions` array represents a transition from one state to another, including the caller information and the action label. If the transition introduces new participants, they can be specified in `newParticipants`.

## Output

The application generates a PNG image visualizing the FSM described in the input JSON file. Initial states are marked in green, final states in red, and transitions show the caller and action.

## Troubleshooting

Ensure the paths provided to the application are correct and accessible. If you encounter any issues related to dependencies, verify your Maven setup and the inclusion of the correct library versions in your `pom.xml`.

## License

This project is licensed under the Apache License, Version 2.0. See the included LICENSE file for more details.

## Contributing

Contributions to the FSM Visualizer are welcome. Please follow the standard fork and pull request workflow.

For any bugs, feature requests, or contributions, please open an issue or a pull request with a clear description of the changes or the issue being addressed.
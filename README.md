![repository banner](design/banner.png)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?logo=openjdk&logoColor=white)
![coverage](.github/badges/jacoco.svg)

# About
A minimal typing test built with Java. 

![](design/design.excalidraw.png)

## Features
- Data persistence across sessions
- Multiple customizations (mode, difficulty, sound effects, ...)
- Detailed statistics with a chart after each test
- Model-View-Controller pattern
- Tested with JUnit
- JavaDoc documentation

## Tools
- Maven for build
- JUnit for testing
- AWT, Swing, and Flatlaf for GUI
- Java Preferences API to save user settings

## Installation

### Method 1
> 🔴 **Requirement**: You must have Java JDK 11+ installed.

1. Download JAR file from the latest release. 
2. Navigate to the directory where your JAR is found.
3. Run the JAR file:
  ```bash
  java -jar tizc.jar
  ```
### Method 2
> 🔴 **Requirement**: You must have Maven installed.

Download the latest release of the project the run `src/main/java/com/github/creme332/App.java` in an IDE.

## Update jar file
```bash
mvn clean compile assembly:single
```
## Code coverage
To generate jacoco report:
```bash
mvn jacoco:prepare-agent test install jacoco:report
```

## Usage
Click on the `Play` button and start typing when you are ready. As soon as you make a mistake, the cursor will stop, and you will have to type the correct letter.

> 🟢 **Tip**: When a mistake is made, there is no need to press `Backspace`.

> 🟢 **Tip**: Press `Tab` to restart/reset test at any time. The timer only starts when you start typing.

## To-do
- [ ] when game ends in death mode, wait 2s before showing game over screen
- [ ] Refactor PlayScreen by splitting view into subviews
- [ ] Connect to mysql database
  - [ ] Create a dynamic scoreboard
  - [ ] Save high score
- [ ] Add new features
  - [ ] Add time limit and cancel tests longer than this limit
  - [ ] Track worst keys typed
  - [ ] Support for capital letters, punctuation, numbers.
- [ ] Use JPackage to convert .jar to .exe
- [ ] Add social preview banner to public repository settings
- [ ] Write tests for UI
- [ ] [Make UI responsive](https://www.youtube.com/watch?v=ZJsjlucSoXM&ab_channel=SIMPLECODE)

## References
- List of english words in `words.txt`: https://www.ef.com/wwen/english-resources/english-vocabulary/top-1000-words/
- Font from [Google Fonts](https://fonts.google.com/specimen/Poppins)
- Icons from [Flaticon](https://www.flaticon.com/free-icons/)
- [Passive View MVC Pattern](https://martinfowler.com/eaaDev/PassiveScreen.html)


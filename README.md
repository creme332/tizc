# tizc
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?logo=openjdk&logoColor=white)
![coverage](.github/badges/jacoco.svg)

A minimal typing test built with Java. 

![GIF of typing test](assets/tizc.gif)

## Features
- Live errors, wpm, and accuracy displays
- Adjustable difficulty
- Typing sound effect
- Detailed statistics with chart after each test
- Model-View-Controller pattern
- Tested with JUnit
- JavaDoc documentation

## Tools
- Maven for build
- JUnit for testing
- AWT, Swing, and Flatlaf for GUI

## Installation

There are two ways to install this project and both ways **requires [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/)**. The easiest way to run this project is by using Method 1. Method 2 is recommended if you plan on making changes to the application.

### Method 1

1. Download jar file from the latest release. 
2. Navigate to the directory where your jar is found.
3. Run the jar file:
  ```bash
  java -jar tizc-0.jar
  ```
### Method 2
> 🔴 **Additional requirements**: You must have Git and Maven installed.

Clone repository:
```bash
git clone git@github.com:creme332/tizc.git
```
Navigate to the root directory of the project:
```bash
cd tizc
```
Install dependencies:
```bash
mvn clean install
```
Run `src/main/java/com/github/creme332/App.java` in an IDE.

#### Useful commands
To create a jar file:
```bash
mvn clean compile assembly:single
```

To generate jacoco code coverage report:
```bash
mvn jacoco:prepare-agent test install jacoco:report
```

## Usage
Click on the `Play` button and start typing when you are ready. As soon as you make a mistake, the cursor will stop, and you will have to type the correct letter.

> 🟢 **Tip**: When a mistake is made, there is no need to press `Backspace`.

> 🟢 **Tip**: Press `Tab` to restart/reset test at any time. The timer only starts when you start typing.

## To-do
- [ ] In death mode, wait 2s before showing game over screen
- [ ] Add more tests including UI tests
- [ ] Refactor PlayScreen by splitting view into subviews
- [ ] Connect to mysql database
  - [ ] Create a dynamic scoreboard
  - [ ] Save high score
- [ ] Add new features
  - [ ] Add time limit and cancel tests longer than this limit
  - [ ] Track worst keys typed
  - [ ] Support capital letters, punctuation, numbers.
- [ ] Use JPackage to convert .jar to .exe
- [ ] [Make UI responsive](https://www.youtube.com/watch?v=ZJsjlucSoXM&ab_channel=SIMPLECODE)

## References
- List of english words in `words.txt`: https://www.ef.com/wwen/english-resources/english-vocabulary/top-1000-words/
- Font from [Google Fonts](https://fonts.google.com/specimen/Poppins)
- [Passive View MVC Pattern](https://martinfowler.com/eaaDev/PassiveScreen.html)


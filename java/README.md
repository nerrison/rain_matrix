

---

# Matrix Rain Effect

This Java application creates a "Matrix Rain" effect with an initial display of the author's name falling from the top of the screen. After the name has fallen completely, the Matrix rain effect starts, simulating the iconic falling code from the Matrix movies.

## Features

- **Author Name Animation**: Displays the name "Nerrison" falling from the top of the screen.
- **Matrix Rain Effect**: After the name animation completes, a Matrix rain effect is displayed with characters falling down the screen.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK installed on your system. This code requires Java 8 or higher.

## Installation

1. **Download and Install JDK**

   - **macOS**: Use [Homebrew](https://brew.sh) to install OpenJDK:
     ```sh
     brew install openjdk
     ```

   - **Windows**: Download and install JDK from the [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) page or from [AdoptOpenJDK](https://adoptopenjdk.net).

   - **Linux**: Use your package manager to install OpenJDK, e.g.,
     ```sh
     sudo apt install openjdk-11-jdk
     ```

2. **Verify Installation**

   To verify that JDK is correctly installed, run:
   ```sh
   java -version
   ```

## How to Compile and Run

1. **Save the Code**

   Save the provided Java code into a file named `MatrixRain.java`.

2. **Compile the Code**

   Open a terminal or command prompt and navigate to the directory containing `MatrixRain.java`. Compile the Java code with the following command:
   ```sh
   javac MatrixRain.java
   ```

3. **Run the Application**

   After compilation, run the application using:
   ```sh
   java MatrixRain
   ```

## Code Explanation

- **Class Definition**

  The `MatrixRain` class extends `JPanel` and represents the main component for drawing the animation.

- **Fields**

  - `fontSize`: The size of the font used for the characters.
  - `chars`: The set of characters used in the Matrix rain effect.
  - `columns`: The number of columns based on the screen width.
  - `drops` and `nameDrops`: Arrays to manage the falling position of characters and the author's name, respectively.
  - `random`: A `Random` instance for generating random values.
  - `timer`: A `Timer` that triggers the animation by calling `repaint()` at regular intervals.
  - `nameFallen`: A flag to determine whether the name animation has finished.
  - `authorName`: The name to display initially.

- **Constructor**

  The constructor initializes the component with the screen size, sets the background color, enables double buffering, and starts a timer for animation.

- **`paintComponent` Method**

  - **Background**: Clears the screen with a semi-transparent black background to create a trailing effect.
  - **Name Animation**: If `nameFallen` is `false`, the code animates the author's name falling from the top.
  - **Matrix Rain Effect**: Once the name has fallen, the Matrix rain effect starts, with characters falling from the top of the screen.

- **`main` Method**

  Creates a `JFrame` to display the `MatrixRain` panel, sets its size, and makes it visible.

## Notes

- **Customizations**: You can adjust the `fontSize` and the `authorName` to fit your preferences.
- **Performance**: The application uses double buffering and a timer for smoother animation. Adjust the timer delay if needed.

---


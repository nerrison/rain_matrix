
# Matrix Rain Effect in C

This project implements a Matrix rain effect similar to the one seen in the movie "The Matrix" using the C programming language and the `ncurses` library. 

## Dependencies

Before you can compile and run this code, you need to install the following dependencies:

- **ncurses**: A library that facilitates text-based user interfaces in a terminal-independent manner.

### Installing Dependencies

#### On macOS

1. **Install Homebrew** if you don't already have it:
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **Install ncurses** using Homebrew:
   ```bash
   brew install ncurses
   ```

#### On Ubuntu/Debian

1. **Update your package list**:
   ```bash
   sudo apt-get update
   ```

2. **Install ncurses**:
   ```bash
   sudo apt-get install libncurses5-dev libncursesw5-dev
   ```

#### On Windows

For Windows, it's recommended to use **Cygwin** or **WSL (Windows Subsystem for Linux)** to provide a Unix-like environment.

1. **Install Cygwin**:
   - Download and run the [Cygwin setup](https://cygwin.com/install.html).
   - During installation, select `ncurses` from the package list.

2. **Install WSL**:
   - Enable WSL and install a Linux distribution by following the instructions from Microsoft [here](https://docs.microsoft.com/en-us/windows/wsl/install).

## Compiling the Code

### On macOS and Linux

1. **Navigate to the project directory**:
   ```bash
   cd /path/to/your/project
   ```

2. **Compile the code**:
   ```bash
   gcc -o matrix_rain matrix_rain.c -lncurses
   ```
   ```bash
    gcc -o matrix_rain matrix_rain.c -I/your/path/to/ncurses/include -L/your/path/to/ncurses/lib -lncurses
   ```

3. **Run the compiled program**:
   ```bash
   ./matrix_rain
   ```

### On Windows (Cygwin)

1. **Open Cygwin terminal**.

2. **Navigate to the project directory**:
   ```bash
   cd /path/to/your/project
   ```

3. **Compile the code**:
   ```bash
   gcc -o matrix_rain matrix_rain.c -lncurses
   ```

4. **Run the compiled program**:
   ```bash
   ./matrix_rain
   ```

## Code Explanation

Here's a detailed explanation of the code:

### Header Files

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <ncurses.h>
```
- `stdio.h`: Standard I/O functions.
- `stdlib.h`: Standard library functions.
- `unistd.h`: Provides access to the POSIX operating system API.
- `time.h`: Provides functions for manipulating date and time.
- `ncurses.h`: Provides functions for creating text-based user interfaces.

### Definitions

```c
#define WIDTH 80
#define HEIGHT 24
#define FONT_SIZE 1
#define NUM_COLUMNS (WIDTH / FONT_SIZE)
#define CHAR_SET "abcdefghijklmnopqrstuvwxyz0123456789"
```
- `WIDTH` and `HEIGHT`: Dimensions of the terminal window.
- `FONT_SIZE`: Size of the font used.
- `NUM_COLUMNS`: Number of columns in the terminal.
- `CHAR_SET`: The characters used in the matrix rain effect.

### Initialization

```c
void initScreen() {
    initscr();
    noecho();
    curs_set(FALSE);
    timeout(0);
    start_color();
    init_pair(1, COLOR_WHITE, COLOR_BLACK); // Characters color
    init_pair(2, COLOR_BLACK, COLOR_BLACK); // Background shadow color
    srand(time(NULL));
}
```
- `initscr()`: Initializes the ncurses mode.
- `noecho()`: Disables echoing of typed characters.
- `curs_set(FALSE)`: Hides the cursor.
- `timeout(0)`: Sets a non-blocking input mode.
- `start_color()`: Initializes color functionality.
- `init_pair()`: Sets up color pairs.
- `srand(time(NULL))`: Seeds the random number generator.

### Drawing Function

```c
void drawMatrix() {
    char chars[] = CHAR_SET;
    int drop[NUM_COLUMNS] = {0};

    while (1) {
        // Create the shadow effect by slightly darkening the background
        attron(COLOR_PAIR(2));
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                mvprintw(y, x, " ");
            }
        }
        attroff(COLOR_PAIR(2));

        // Draw the matrix effect
        attron(COLOR_PAIR(1));
        for (int x = 0; x < NUM_COLUMNS; x++) {
            int y = drop[x];
            char text = chars[rand() % (sizeof(chars) - 1)];
            mvprintw(y, x * FONT_SIZE, "%c", text);

            if (y >= HEIGHT || rand() % 100 < 5) {
                drop[x] = 0;
            } else {
                drop[x]++;
            }
        }
        attroff(COLOR_PAIR(1));

        refresh();
        usleep(50000); // Adjust delay for speed of animation
    }
}
```
- `attron()`: Turns on the specified attribute.
- `attroff()`: Turns off the specified attribute.
- `mvprintw(y, x, " ")`: Prints a space at the specified location to create the shadow effect.
- `mvprintw(y, x * FONT_SIZE, "%c", text)`: Prints the character at the specified location.
- `usleep(50000)`: Adds a delay to control the speed of the animation.

### Main Function

```c
int main() {
    initScreen();
    drawMatrix();
    endwin();
    return 0;
}
```
- `initScreen()`: Initializes the screen and settings.
- `drawMatrix()`: Runs the matrix rain effect.
- `endwin()`: Ends the ncurses mode and restores the terminal.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

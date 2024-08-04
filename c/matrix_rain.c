#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <ncurses.h>

#define WIDTH 80
#define HEIGHT 24
#define FONT_SIZE 1
#define NUM_COLUMNS (WIDTH / FONT_SIZE)
#define CHAR_SET "0123456789/*-+/<>?;:[]~!@#$^&*()+=abcdefghijklmnopqrstuvwxyz"

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

int main() {
    initScreen();
    drawMatrix();
    endwin();
    return 0;
}

import pygame
import random
import sys

# Initialize pygame
pygame.init()

# Screen dimensions (set to medium size)
WIDTH, HEIGHT = 800, 600
FONT_SIZE = 16
columns = WIDTH // FONT_SIZE

# Colors
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)

# Initialize screen
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption('Matrix Rain Effect')
screen.fill(BLACK)

# Set up font
font = pygame.font.Font(None, FONT_SIZE)

# Create a list to hold the positions of the drops
drops = [0] * columns

def draw_matrix():
    screen.fill(BLACK)  # Clear the screen with black background

    for x in range(columns):
        char = random.choice("0123456789/*-+/<>?;:[]~!@#$%^&*()+=abcdefghijklmnopqrstuvwxyz")
        y = drops[x] * FONT_SIZE
        text = font.render(char, True, GREEN)
        screen.blit(text, (x * FONT_SIZE, y))

        if y > HEIGHT or random.random() > 0.975:
            drops[x] = 0
        else:
            drops[x] += 1

    pygame.display.flip()  # Update the screen

def main():
    clock = pygame.time.Clock()

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

        draw_matrix()
        clock.tick(30)  # Frame rate

if __name__ == '__main__':
    main()

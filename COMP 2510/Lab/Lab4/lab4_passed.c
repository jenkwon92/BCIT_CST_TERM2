#include <stdio.h>
#include <stdlib.h>

#define GRID_SIZE 20
#define BORDER '*'
#define PARTICLE '+'

typedef struct {
    int x;
    int y;
    int dx;
    int dy;
} Particle;

int isCollision(Particle p1, Particle p2) {
    return (p1.x == p2.x) && (p1.y == p2.y);
}

void moveParticle(Particle* particle) {
    // Move the particle according to its velocity
    particle->x += particle->dx;
    particle->y += particle->dy;

    // Check for collision with borders and adjust position and direction
    if (particle->x < 0) {
        particle->dx = -particle->dx;
        particle->x = 0 - particle->x; // Reflect position inside the boundary
    }
    else if (particle->x >= GRID_SIZE) {
        particle->dx = -particle->dx;
        particle->x = 2 * GRID_SIZE - 2 - particle->x; // Reflect position inside the boundary
    }

    if (particle->y < 0) {
        particle->dy = -particle->dy;
        particle->y = 0 - particle->y; // Reflect position inside the boundary
    }
    else if (particle->y >= GRID_SIZE) {
        particle->dy = -particle->dy;
        particle->y = 2 * GRID_SIZE - 2 - particle->y; // Reflect position inside the boundary
    }
}

int main(int argc, char* argv[]) {
    if (argc != 4) {
        printf("Usage: %s input.txt output.txt num_seconds\n", argv[0]);
        return 1;
    }

    char* inputFileName = argv[1];
    char* outputFileName = argv[2];
    int numSeconds = atoi(argv[3]);

    // Read input from file
    FILE* inputFile = fopen(inputFileName, "r");
    if (inputFile == NULL) {
        perror("Error opening input file");
        return 1;
    }

    int numParticles = 0;
    Particle particles[100];

    while (fscanf(inputFile, "%d,%d,%d,%d", &particles[numParticles].x, &particles[numParticles].y,
        &particles[numParticles].dx, &particles[numParticles].dy) == 4) {
        numParticles++;
    }

    fclose(inputFile);

    // Simulate particle movement
    for (int t = 0; t < numSeconds; t++) {
        // Move each particle
        for (int i = 0; i < numParticles; i++) {
            moveParticle(&particles[i]);
        }

        // Handle particle collisions
        for (int i = 0; i < numParticles - 1; i++) {
            for (int j = i + 1; j < numParticles; j++) {
                if (isCollision(particles[i], particles[j])) {
                    for (int k = j; k < numParticles - 1; k++) {
                        particles[k] = particles[k + 1];
                    }
                    numParticles--;

                    for (int k = i; k < numParticles - 1; k++) {
                        particles[k] = particles[k + 1];
                    }
                    numParticles--;

                    i--;
                    break;
                }
            }
        }
    }

    // Create the grid and place particles
    char grid[GRID_SIZE][GRID_SIZE];

    for (int i = 0; i < GRID_SIZE; i++) {
        for (int j = 0; j < GRID_SIZE; j++) {
            grid[i][j] = ' ';
        }
    }

    for (int i = 0; i < numParticles; i++) {
        int x = particles[i].x;
        int y = GRID_SIZE - 1 - particles[i].y;
        grid[y][x] = PARTICLE;
    }

    // Print the grid with borders
    FILE* outputFile = fopen(outputFileName, "w");
    if (outputFile == NULL) {
        perror("Error opening output file");
        return 1;
    }

    for (int i = 0; i < GRID_SIZE + 2; i++) {
        fprintf(outputFile, "%c", BORDER);
    }
    fprintf(outputFile, "\n");

    for (int i = 0; i < GRID_SIZE; i++) {
        fprintf(outputFile, "%c", BORDER);
        for (int j = 0; j < GRID_SIZE; j++) {
            fprintf(outputFile, "%c", grid[i][j]);
        }
        fprintf(outputFile, "%c\n", BORDER);
    }

    for (int i = 0; i < GRID_SIZE + 2; i++) {
        fprintf(outputFile, "%c", BORDER);
    }
    fprintf(outputFile, "\n");

    fclose(outputFile);

    return 0;
}

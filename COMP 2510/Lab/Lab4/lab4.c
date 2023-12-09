#include <stdio.h>
#include <stdlib.h>

#define GRID_SIZE 22
#define BORDER '*'
#define PARTICLE '+'

int main(int argc, char *argv[]) {
    FILE *inputFile, *outputFile;

    inputFile = fopen(argv[1], "r");
    outputFile = fopen(argv[2], "w");
    int sec = atoi(argv[3]);

    // Initialize the grid including borders.
    char grid[GRID_SIZE][GRID_SIZE] = {0};

    int x, y, xV, yV;
    int count = 0;

    // Read the input file and count the particles.
    while (fscanf(inputFile, "%d,%d,%d,%d", &x, &y, &xV, &yV) != EOF) {
        count++;
    }

    // Close and reopen the input file to start from the beginning.
    fclose(inputFile);
    inputFile = fopen(argv[1], "r");

    // Read particle data into arrays.
    int *xValues = (int *)malloc(count * sizeof(int));
    int *yValues = (int *)malloc(count * sizeof(int));
    int *xVelocities = (int *)malloc(count * sizeof(int));
    int *yVelocities = (int *)malloc(count * sizeof(int));

    for (int i = 0; i < count; i++) {
        fscanf(inputFile, "%d,%d,%d,%d", &xValues[i], &yValues[i], &xVelocities[i], &yVelocities[i]);
        // Adjust initial positions
        xValues[i]++; 
        yValues[i]++;
    }

    fclose(inputFile);

    int index = GRID_SIZE - 1;
    int k = 0;

    for (int i = 0; i <= index; i++) {
        for (int j = 0; j <= index; j++) {
            if (i == 0 || i == index || j == 0 || j == index) {
                grid[i][j] = BORDER;
            } else {
                grid[i][j] = ' ';
            }
        }
    }

    for (int i = 0; i < sec; i++) {
        for (int j = 0; j < count; j++) {
            xValues[j] += xVelocities[j];
            yValues[j] += yVelocities[j];

            // Handle bouncing off the borders
            if (xValues[j] > index || xValues[j] < 1) {
                xVelocities[j] = -xVelocities[j];
            }

            if (yValues[j] > index || yValues[j] < 1) {
                yVelocities[j] = -yVelocities[j];
            }
        }

        // Detect and handle collisions
        int collisions[index][index];
        for (int j = 0; j < index; j++) {
            for (int l = 0; l < index; l++) {
                collisions[j][l] = 0;
            }
        }

        for (int j = 0; j < count; j++) {
            int x = xValues[j];
            int y = yValues[j];
            if (collisions[x][y]) {
                xValues[j] = -1;
                yValues[j] = -1;
            } else {
                collisions[x][y] = 1;
            }
        }

        int tmpCount = 0;
        for (int j = 0; j < count; j++) {
            if (xValues[j] != -1) {
                xValues[tmpCount] = xValues[j];
                yValues[tmpCount] = yValues[j];
                tmpCount++;
            }
        }
        count = tmpCount;
    }

    for (int i = 0; i < count; i++) {
        int x = xValues[i];
        int y = yValues[i];
        if (x >= 1 && x <= index && y >= 1 && y <= index) {
            grid[index - y][x] = PARTICLE;
        }
    }

    for (int i = 0; i <= index; i++) {
        for (int j = 0; j <= index; j++) {
            fprintf(outputFile, "%c", grid[i][j]);
        }
        fprintf(outputFile, "\n");
    }

    fclose(outputFile);

    free(xValues);
    free(yValues);
    free(xVelocities);
    free(yVelocities);

    return 0;
}

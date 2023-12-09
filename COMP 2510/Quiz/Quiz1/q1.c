#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *ANum = "A01263922";

void rotate_matrix(int** matrix, int n) {
    int temp;
    for (int i = 0; i < n / 2; i++)
    {
        for (int j = i; j < n - i - 1; j++)
        {
            temp = *(*(matrix + i) + j);
            *(*(matrix + i) + j) = *(*(matrix + n - j - 1) + i);
            *(*(matrix + n - j - 1) + i) = *(*(matrix + n - i - 1) + n - j - 1);
            *(*(matrix + n - i - 1) + n - j - 1) = *(*(matrix + j) + n - i - 1);
            *(*(matrix + j) + n - i - 1) = temp;
        }
    }
}

int main() {
    FILE *file = fopen("input.txt", "r");
    if (file == NULL) {
        printf("Failed to open the input file.\n");
        return 1;
    }

    int n;
    fscanf(file, "%d", &n);  // assuming the first line of the file contains the matrix size

    int** matrix = malloc(n * sizeof(int*));
    for (int i = 0; i < n; ++i) {
        *(matrix + i) = malloc(n * sizeof(int));
        for (int j = 0; j < n; ++j) {
	    fscanf(file, "%1d", (*(matrix + i) + j));
        }
    }
    fclose(file);

    rotate_matrix(matrix, n);

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
	  printf("%d", *(*(matrix + i) + j));
        }
        printf("\n");
    }

    for (int i = 0; i < n; ++i) {
      free(*(matrix + i));
    }
    free(matrix);

    // Create an output file with the name stored in ANum
    FILE *outputFile = fopen(ANum, "w");

    // Check if the output file was created successfully
    if (outputFile == NULL) {
        printf("Failed to create the output file.\n");
        return 1;
    }

    fclose(outputFile);

    return 0;
}

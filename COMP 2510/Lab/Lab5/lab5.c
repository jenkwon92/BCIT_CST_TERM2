#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_LENGTH 256
#define MAX_LINES 100

void reverseLinesAndChars(FILE *file) {
    char lines[MAX_LINES][MAX_LINE_LENGTH];
    int line_count = 0;

    while (line_count < MAX_LINES && fgets(lines[line_count], sizeof(lines[0]), file) != NULL) {
        line_count++;
    }
    
    for (int i = line_count - 1; i >= 0; i--) {
        int start = 0;
        int end = strlen(lines[i]) - 1;

        while (start < end) {
            char temp = lines[i][start];
            lines[i][start] = lines[i][end];
            lines[i][end] = temp;
            start++;
            end--;
        }
        
        printf("%s", lines[i]);
    }
    printf("\n");
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return 1;
    }

    const char *filename = argv[1];
    FILE *file = fopen(filename, "r");

    if (file == NULL) {
        printf("Failed to open the file: %s\n", filename);
        return 1;
    }

    reverseLinesAndChars(file);

    fclose(file);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

// update this with your A number
char a_num[] = "01263922";

void rotateArray(int arr[10][10], int n, int rows, int cols) {
    int temp[10][10];

    switch((n/90)%4){
        case 1:
            for(int x = 0; x < rows; x++){
                for(int y = 0; y < sizeof(arr[0])/sizeof(int); y++){
                    temp[x][y] = arr[rows-1-y][x];
                }
            }
            break;

        case 2:
            for(int x = 0; x < rows; x++){
                for(int y = 0; y < sizeof(arr[0])/sizeof(int); y++){
                    temp[x][y] = arr[rows-1-x][rows-1-y];
                }
            }
            break;

        case 3:
            for(int x = 0; x < rows; x++){
                for(int y = 0; y < sizeof(arr[0])/sizeof(int); y++){
                    temp[x][y] = arr[y][rows-1-x];
                }
            }
            break;

        default:
            for(int x = 0; x < rows; x++){
                for(int y = 0; y < sizeof(arr[0])/sizeof(int); y++){
                    temp[x][y] = arr[x][y];
                }
            }
            break;
    }

    for(int i = 0; i < rows; i++){
        for(int j = 0; j <rows; j++){
            arr[i][j] = temp[i][j];
        }
    }

    // Do NOT write any code below
    // Output the rotated array
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if(arr[i][j] == 0)
                printf(" ");
            else
                printf("%d", arr[i][j]);
        }
        printf("\n");
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s <input_file> <input_angle_degrees>\n", argv[0]);
        return 1;
    }

    char *inputFileName = argv[1];
    int rotationAngle = atoi(argv[2]);
    int arr[10][10] = {0};  // Initialize the array with zeros
    int rows = 0, cols = 0;
    char inputChar;

    // Read the input array from the specified file
    FILE *file = fopen(inputFileName, "r");
    if (file == NULL) {
        perror("Error opening input file");
        return 1;
    }

    // Read the input array character by character
    while (rows < 10 && (inputChar = fgetc(file)) != EOF) {
        if (inputChar == '\n') {
            rows++;
            cols = 0;
        } else if (cols < 10 && inputChar >= '0' && inputChar <= '9') {
            arr[rows][cols++] = inputChar - '0';
        }
    }

    fclose(file);

    printf("A%s\n", a_num);

    // Determine the actual dimensions of the input array
    if (cols == 0) {
        cols = 10;
    }

    printf("Input array:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if(arr[i][j] == 0)
                printf(" ");
            else
                printf("%d", arr[i][j]);
        }
        printf("\n");
    }

    printf("\nRotated array (%d degrees):\n", rotationAngle);
    rotateArray(arr, rotationAngle, rows, cols);

    return 0;
}

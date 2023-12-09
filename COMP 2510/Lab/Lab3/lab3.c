#include <stdio.h>
#include <stdlib.h>

// Function to rotate a 2D array by a given angle (in degrees)
void rotateArray(char **arr, int n, int rows, int cols)
{
    char **arrTemp = (char **)malloc(rows * sizeof(char *));
    for (int x = 0; x < rows; x++)
    {
        arrTemp[x] = (char *)malloc(cols * sizeof(char));
    }

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            switch((n / 90) % 4){
                case 1:
                    arrTemp[i][j] = arr[cols - 1 - j][i];
                    break;
                case 2:
                    arrTemp[i][j] = arr[rows - 1 - i][cols - 1 - j];
                    break;
                case 3:
                    arrTemp[i][j] = arr[j][rows - 1 - i];
                    break;
                default:
                    arrTemp[i][j] = arr[i][j];
                    break;
            }
        }
    }

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            arr[i][j] = arrTemp[i][j];
        }
    }

    for (int i = 0; i < rows; i++)
    {
        free(arrTemp[i]);
    }
    free(arrTemp);
}

// Function to zoom (expand or shrink) a 2D array by a given factor
void zoomArray(char **arr, double zoomFactor, int *rows, int *cols)
{
    int rowsTemp = (*rows * zoomFactor) + 0.5;
    int colsTemp = (*cols * zoomFactor) + 0.5;

    char **tempArr = (char **)malloc(rowsTemp * sizeof(char *));
    for (int i = 0; i < rowsTemp; i++)
    {
        *(tempArr + i) = (char *)malloc(colsTemp * sizeof(char));
    }
    for (int r = 0; r < rowsTemp; r++)
    {
        for (int c = 0; c < colsTemp; c++)
        {
            int originR = r / zoomFactor;
            int originC = c / zoomFactor;
            tempArr[r][c] = arr[originR][originC];
        }
    }

    for (int i = 0; i < rowsTemp; i++)
    {
        for (int j = 0; j < colsTemp; j++)
        {
            arr[i][j] = tempArr[i][j];
        }
    }

    for (int i = 0; i < rowsTemp; i++)
    {
        free(tempArr[i]);
    }
    free(tempArr);

    *rows = rowsTemp;
    *cols = colsTemp;
}

int main(int argc, char *argv[])
{
    if (argc != 4)
    {
        printf("Usage: %s <input_file> <angle_degrees> <zoom_factor>\n", argv[0]);
        return 1;
    }

    char *inputFileName = argv[1];
    int angle = atoi(argv[2]);
    double zoomFactor = atof(argv[3]); // Use atof to convert the zoom factor to a double

    // Initialize a 2D array of size 30x30 as a pointer-to-pointer
    char **arr = (char **)malloc(30 * sizeof(char *));
    for (int i = 0; i < 30; i++)
    {
        arr[i] = (char *)malloc(30 * sizeof(char));
    }

    // Open the input file for reading
    FILE *file = fopen(inputFileName, "r");
    if (file == NULL)
    {
        printf("Failed to open the input file.\n");
        return 1;
    }

    // Read the input array from the specified file and populate arr
    int rows = 0;
    int cols = 0;
    char c;

    while (rows < 30 && cols < 30 && fscanf(file, "%c", &arr[rows][cols]) == 1)
    {
        if (arr[rows][cols] == '\n')
        {
            rows++;
            cols = 0;
        }
        else
        {
            cols++;
        }
    }
    cols = rows;

    // Close the input file
    fclose(file);

    // Print the array
    printf("Input Array:\n");
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    // Call rotateArray or zoomArray based on the angle and zoomFactor
    // For example:
    rotateArray(arr, angle, rows, cols);
    zoomArray(arr, zoomFactor, &rows, &cols);

    // Print the array
    printf("Output %dx%d Array:\n", rows, cols);
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    // Free dynamically allocated memory when done
    for (int i = 0; i < 30; i++)
    {
        free(arr[i]);
    }
    free(arr);

    return 0;
}

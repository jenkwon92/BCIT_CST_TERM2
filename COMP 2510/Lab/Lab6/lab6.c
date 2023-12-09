#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <errno.h>

typedef struct
{
    char firstName[50];
    char lastName[50];
    char gpaString[10];
    double gpa;
} DomesticStudent;

typedef struct
{
    char firstName[50];
    char lastName[50];
    char gpaString[10]; 
    double gpa;
    int toeflScore;
} InternationalStudent;

// Function declarations
int validateGPA(char *gpaString);
void writeErrorAndExit(FILE *outputFile, const char *error);
bool isValidInteger(const char *str);

int main(int argc, char *argv[])
{
    // Check for proper command line arguments
    if (argc != 4)
    {
        printf("Error: Incorrect number of arguments.\n");
        return 1;
    }

    // Attempt to open input and output files
    FILE *inputFile = fopen(argv[1], "r");
    FILE *outputFile = fopen(argv[2], "w");
    if (inputFile == NULL || outputFile == NULL)
    {
        writeErrorAndExit(outputFile, "Error: Could not open file.\n");
    }

    // When option is not integer.
    if (!isValidInteger(argv[3]))
    {
        printf("Error: The option must be an integer.\n");
        return 1;
    }

    int option = atoi(argv[3]);

    // When option is not between 1 and 3.
    if (option < 1 || option > 3)
    {
        printf("Error: Invalid option. Please choose between 1 and 3.");
        return 1;
    }

    char line[100];
    DomesticStudent domestic;
    InternationalStudent international;
    char outputData[10000] = "";
    char *error = NULL;

    // Read each line from the input file
    while (fgets(line, sizeof(line), inputFile))
    {
        // Reset structures
        memset(&domestic, 0, sizeof(domestic));
        memset(&international, 0, sizeof(international));

        // Scan the line depending on the student status
        char *token = strtok(line, " ");
        int tokenCount = 0;
        int isInternational = 0; // Flag to check if the student is international

        while (token != NULL)
        {
            if (tokenCount == 0)
            {
                strncpy(domestic.firstName, token, sizeof(domestic.firstName) - 1);
                strncpy(international.firstName, token, sizeof(international.firstName) - 1);
            }
            else if (tokenCount == 1)
            {
                strncpy(domestic.lastName, token, sizeof(domestic.lastName) - 1);
                strncpy(international.lastName, token, sizeof(international.lastName) - 1);
            }
            else if (tokenCount == 2)
            {
                // Store the original GPA string
                strncpy(domestic.gpaString, token, sizeof(domestic.gpaString) - 1);
                strncpy(international.gpaString, token, sizeof(international.gpaString) - 1);

                if (!validateGPA(token))
                {
                    if (atof(token) < 0)
                    {
                        error = "Error: GPA must be a not negative number.\n";
                        break;
                    }
                    else
                    {
                        error = "Error: Invalid GPA format.\n";
                        break;
                    }
                }
                domestic.gpa = atof(token);
                international.gpa = atof(token);
            }
            else if (tokenCount == 3)
            {
                if (strcmp(token, "I") == 0)
                {
                    isInternational = 1;
                    // 국제 학생의 경우, 다음 토큰이 TOEFL 점수여야 하므로, 아직 해당 점수가 없다고 가정합니다.
                    international.toeflScore = -1; // TOEFL 점수가 아직 설정되지 않았음을 나타냅니다.
                }
                else if (strcmp(token, "D") != 0)
                {
                    error = "Error: Invalid student type.\n";
                    break;
                }
            }
            else if (tokenCount == 4)
            {
                if (!isInternational)
                {
                    // 국내 학생이면 TOEFL 점수가 있으면 안 됩니다.
                    error = "Error: Domestic student should not have a TOEFL score.\n";
                    break;
                }
                else
                {
                    international.toeflScore = atoi(token);

                    char *endptr;
                    double toeflScore = strtod(token, &endptr);
                    
                    // Check if conversion to double was successful and if the rest of the string is empty
                    if (*endptr != '\0' || errno == ERANGE)
                    {
                        error = "Error: Invalid TOEFL score format.\n";
                        break;
                    }

                    int intToeflScore = (int)toeflScore;

                    

                    if (intToeflScore < 0 || intToeflScore > 120)
                    {
                        error = "Error: Invalid TOEFL score range.\n";
                        break;
                    }

                    

                    international.toeflScore = intToeflScore;
                }
            }

            token = strtok(NULL, " \n");
            tokenCount++;
        }

        if (error != NULL)
        {
            writeErrorAndExit(outputFile, error);
        }

        if ((error == NULL) && (isInternational && tokenCount != 5) || (!isInternational && tokenCount != 4))
        {
            error = "Error: Invalid input format.\n";
            writeErrorAndExit(outputFile, error);
            break;
        }

        // TOEFL 점수 검사
        if (isInternational && (international.toeflScore < 70 || international.toeflScore > 120))
        {
            // TOEFL 점수가 70점 미만이거나 120점을 초과하면 이 학생을 건너뜁니다.
            continue;
        }

        // Check if there is an error or if the GPA is less than 3.9
        if (error != NULL || domestic.gpa < 3.9)
        {
            if (error != NULL)
                writeErrorAndExit(outputFile, error); // If there's an error, write to file and exit

            continue; // If GPA is less than 3.9, skip this student
        }

        // Prepare the student data string based on the student type and selected option
        char studentData[200] = ""; // Moved inside the loop to reset it each iteration

        if ((option == 1 || option == 3) && !isInternational)
        {
            // 국내 학생 데이터 기록
            sprintf(studentData, "%s %s %s D\n", domestic.firstName, domestic.lastName, domestic.gpaString);
        }
        else if ((option == 2 || option == 3) && isInternational)
        {
            if (international.toeflScore == -1)
            {
                // 국제 학생인데 TOEFL 점수가 없는 경우
                error = "Error: International student must have a TOEFL score.\n";
                writeErrorAndExit(outputFile, error);
            }
            // 국제 학생 데이터 기록
            sprintf(studentData, "%s %s %s I %d\n", international.firstName, international.lastName, international.gpaString, international.toeflScore);
        }

        // Append the student data to the output data
        strcat(outputData, studentData);
    }

    // Write all valid student data to the output file
    fputs(outputData, outputFile);

    // Clean up and close files
    fclose(inputFile);
    fclose(outputFile);

    return 0;
}

// Validates the GPA string to ensure it is a proper decimal, does not exceed three decimal places, and is not negative
int validateGPA(char *gpaString)
{
    int dotCount = 0;
    int digitAfterDot = 0;

    // Check for a negative sign
    if (gpaString[0] == '-')
    {
        return 0; // GPA cannot be negative
    }

    for (int i = 0; gpaString[i] != '\0'; i++)
    {
        if (!isdigit(gpaString[i]))
        {
            if (gpaString[i] == '.' && dotCount == 0)
            {
                dotCount++;
            }
            else
            {
                // Invalid character or more than one dot
                return 0;
            }
        }
        else if (dotCount == 1)
        { // Count the digits after the dot
            digitAfterDot++;
            if (digitAfterDot > 3)
            {
                // If more than three digits after the dot, return invalid
                return 0;
            }
        }
    }
    // Valid if there's exactly one dot or no dot (integer)
    return (dotCount == 1 && digitAfterDot > 0 && digitAfterDot <= 3) || (dotCount == 0);
}

// Writes an error message to the output file and exits the program
void writeErrorAndExit(FILE *outputFile, const char *error)
{
    fputs(error, outputFile);
    fclose(outputFile);
    exit(1);
}

bool isValidInteger(const char *str)
{
    if (*str == '-' || *str == '+')
        str++; // Handle negative or positive signs

    if (!*str)
        return false; // String is just a sign

    while (*str)
    {
        if (!isdigit((unsigned char)*str))
            return false;
        str++;
    }
    return true;
}

void writeError(FILE *outputFile, const char *error)
{
    // Write the error message to the outputFile
    fputs(error, outputFile);
}

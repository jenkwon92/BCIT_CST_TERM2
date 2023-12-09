#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_INT 2147483647

const char *months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

// Define the Student struct
typedef struct {
    char firstName[50];
    char lastName[50];
    char birthMonth[5];
    int birthDay;
    int birthYear;
    char gpa[10];
    char status;
    int toefl;
} Student;

// Function prototypes
int countLinesInFile(const char *filename);
int checkDate(int year, int month, int day);
int isNumeric(const char *str);
int countDecimalDigits(char *str);
int checkError(Student *s);
int handleError(int errorCode, int errorLine, FILE * output);
void mergeSort(Student arr[], int l, int r);
void merge(Student arr[], int l, int m, int r);
int compareStudents(Student a, Student b);

int countLinesInFile(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        return -1; // Error opening file
    }
    int count = 0;
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        if (ch == '\n') {
            count++;
        }
    }
    count++;
    fclose(file);
    return count;
}

// Check if the date is valid
int checkDate(int year, int month, int day) {
    // Check if year, month and day is in valid range
    if (year < 1950 || year > 2010 || month < 1 || month > 12 || day < 1) return 0; 

    // Check if day is valid in month
    int daysInMonth[] = {0, 31, (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (day > daysInMonth[month])
        return 0;  
    // Return 1 if date is valid
    return 1;  
}

// Check if GPA is numeric
int isNumeric(const char *str) {
    int count = 0;
    for (int i = 0; str[i] != '\0'; ++i) {
        if (str[i] == '.') count++;
        if (((str[i] < '0' || str[i] > '9') && str[i] != '.' )|| count > 1) {
            return 0;
        }
    }
    return 1;
}

// Count the number of decimal digits in a string
int countDecimalDigits(char *str) {
    if (strchr(str, '.') == NULL) return 0; // No decimal point
    int indexPoint = strchr(str, '.') - str;
    return strlen(str) - indexPoint - 1;
}

// Check if there's any error in the record
int checkError(Student *s) {
    // Check first name
    if (strlen(s->firstName) == 0) return 1;
    // Check last name
    if (strlen(s->lastName) == 0) return 2;
    // Check birth month: if valid, convert to number
    int month = -999;
    for (int i = 0; i < 12; i++) {
        if (strcasecmp(s->birthMonth, months[i]) == 0) {
            month = i + 1;
            break;
        }
    }
    if (month == -999) return 3;
    // Check the date: proper date
    if (!checkDate(s->birthYear, month, s->birthDay)) return 4;
    // Check GPA: 0.0-4.3, no more than 3 decimal digits
    if (!isNumeric(s->gpa)|| atof(s->gpa)<0.0 || atof(s->gpa) > 4.3 || countDecimalDigits(s->gpa) > 3) return 5;
    // Check status: either 'D' or 'I'
    if (s->status != 'D' && s->status != 'I') return 6;
    // Check toefl: 0-120
    if ((s->toefl < 0 || s->toefl > 120)&& s->toefl != MAX_INT) return 7;
    return 0;
}

// Handle errors based on error code
int handleError(int errorCode, int errorLine, FILE * output) {
    switch(errorCode){
        case 1:
            fprintf(output, "Error in line %d: First name is missing.\n", errorLine);
            break;
        case 2:
            fprintf(output, "Error in line %d: Last name is missing.\n", errorLine);
            break;
        case 3:
            fprintf(output, "Error in line %d: Invalid month of birth.\n", errorLine);
            break;
        case 4:
            fprintf(output, "Error in line %d: Invalid date of birth.\n", errorLine);
            break;
        case 5:
            fprintf(output, "Error in line %d: Invalid GPA.\n", errorLine);
            break;
        case 6:
            fprintf(output, "Error in line %d: Invalid status.\n", errorLine);
            break;
        case 7:
            fprintf(output, "Error in line %d: Invalid TOEFL score.\n", errorLine);
            break;
    }
    return 1;
}

// Merge sort function
void mergeSort(Student arr[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

// Merge function for merge sort
void merge(Student arr[], int l, int m, int r) {
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;

    Student L[n1], R[n2];
    for (i = 0; i < n1; i++) L[i] = arr[l + i];
    for (j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2) {
        if (compareStudents(L[i], R[j]) <= 0) arr[k++] = L[i++];
        else arr[k++] = R[j++];
    }
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}

// Function to compare two students based on the sorting criteria
int compareStudents(Student a, Student b) {
    // 1. Year of birth
    if (a.birthYear < b.birthYear) {
        return -1;
    } else if (a.birthYear > b.birthYear) {
        return 1;
    }

    // 2. Month of birth
    int monthA, monthB;
    for (int i = 0; i < 12; i++) {
        if (strcasecmp(a.birthMonth, months[i]) == 0) {
            monthA = i;
        }
        if (strcasecmp(b.birthMonth, months[i]) == 0) {
            monthB = i;
        }
    }
    if (monthA < monthB) {
        return -1;
    } else if (monthA > monthB) {
        return 1;
    }

    // 3. Day of birth
    if (a.birthDay < b.birthDay) {
        return -1;
    } else if (a.birthDay > b.birthDay) {
        return 1;
    }

    // 4. Last name (alphabetical order)
    int lastNameComparison = strcasecmp(a.lastName, b.lastName);
    if (lastNameComparison != 0) {
        return lastNameComparison;
    }

    // 5. First name (alphabetical order)
    int firstNameComparison = strcasecmp(a.firstName, b.firstName);
    if (firstNameComparison != 0) {
        return firstNameComparison;
    }

    // 6. GPA
    if (atof(a.gpa) < atof(b.gpa)) {
        return -1;
    } else if (atof(a.gpa) > atof(b.gpa)) {
        return 1;
    }

    // 7. TOEFL (Domestic students take precedence if TOEFL score is missing)
    if (a.status == 'D' && b.status == 'I') {
        return -1;
    } else if (a.status == 'I' && b.status == 'D') {
        return 1;
    } else if (a.status == 'I' && b.status == 'I' && a.toefl < b.toefl) {
        return -1;
    } else if (a.status == 'I' && b.status == 'I' && a.toefl > b.toefl) {
        return 1;
    }

    // 8. Domestic > International
    if (a.status == 'D' && b.status == 'D') {
        return 0;
    } else if (a.status == 'I' && b.status == 'I') {
        return 0;
    }

    // Default case, should not reach this point
    return 0;
}


int main(int argc, char *argv[]) {
    // Check command line arguments
    if (argc != 4) {
        printf("Error: Incorrect number of arguments\n Usage: ./<name of executable> <input file> <output file> <option>");
        return 1;
    }
    
    // Parse the option argument
    char *temp = argv[3];
    int option = atoi(argv[3]);
    if (option < 1 || option > 3 || strlen(temp) > 1) {
        printf("Error: Invalid option\n");
        return 1;
    }
    
    // Count the number of lines in the input file
    int numLines = countLinesInFile(argv[1]);
    if (numLines < 0) {
        printf("Error: Could not open input file\n");
        return 1;
    }

    // Open the input file
    FILE *inputFile = fopen(argv[1], "r");
    if (inputFile == NULL) {
        printf("Error: Could not open input file\n");
        return 1;
    }

    // Allocate memory for the array of Student structs
    Student *students = (Student *)malloc(numLines * sizeof(Student));
    for (int i = 0; i < numLines; i++) {
        students[i].toefl = MAX_INT;
    }
    if (students == NULL) {
        printf("Error: Memory allocation failed\n");
        return 1;
    }

    // Open the output file
    FILE *output = fopen(argv[2], "w");
    if (output == NULL) {
        printf("Error: Could not open output file\n");
        free(students); // Free allocated memory before returning
        return 1;
    }

    // Read the content of the file into an array of Student structs
    int numStudents = 0;
    char line[255]; // Buffer to store each line

    while (fgets(line, sizeof(line), inputFile) != NULL) {
        // Count spaces in line
        int numSpaces = 0;
        for (int i = 0; i < strlen(line); i++) {
            if (line[i] == ' ') {
                numSpaces++;
            }
        }
        char status[255];
        char temp[255];
        if (sscanf(line, "%s", temp) == -1) continue;
        if (sscanf(line, "%s %s %3s-%d-%d %s %s %d", 
            students[numStudents].firstName,
            students[numStudents].lastName, 
            students[numStudents].birthMonth,
            &students[numStudents].birthDay, 
            &students[numStudents].birthYear,
            students[numStudents].gpa,
            status,
            &students[numStudents].toefl) < 7) {
            // Handle format error
            fprintf(output, "Error in line %d: Invalid format of student information \n", numStudents + 1);
            free(students); 
            return 1;
        } else if (strlen(status) == 1) {
            students[numStudents].status = status[0];
        } else {
            fprintf(output, "Error in line %d: Invalid format of student information \n", numStudents + 1);
            free(students); 
            return 1;
        }

        // Error handling: domestic student shouldn't have a TOEFL score
        if (students[numStudents].status == 'D') {
            if (numSpaces > 4) {
                fprintf(output, "Error in line %d: Invalid format of student information \n", numStudents + 1);
                free(students); 
                return 1;
            }
            if (students[numStudents].toefl != MAX_INT) {
                fprintf(output, "Error in line %d: Domestic students shouldn't have a TOEFL score\n", numStudents + 1);
                free(students); 
                return 1;
            }
        // Error handling: international student should have a TOEFL score
        } else if (students[numStudents].status == 'I') {
            if (numSpaces > 5) {
                fprintf(output, "Error in line %d: Invalid format of student information \n", numStudents + 1);
                free(students); 
                return 1;
            }
            if (students[numStudents].toefl == MAX_INT) {
                fprintf(output, "Error in line %d: International students should have a valid TOEFL score\n", numStudents + 1);
                free(students); 
                return 1;
            }
        }
        int errorCode = checkError(&students[numStudents]);
        if (errorCode) {
            free(students); 
            return handleError(errorCode, numStudents+1, output);
        }
        numStudents++;
    }
    fclose(inputFile);

    // Sort the array of students
    mergeSort(students, 0, numStudents - 1);

    // Write the sorted output to the file
    for (int i = 0; i < numStudents; i++) {
        // Check the student's status against the option
        if ((option == 1 && students[i].status == 'D') ||
            (option == 2 && students[i].status == 'I') ||
            (option == 3)) {
                if (students[i].toefl != MAX_INT) {
                    fprintf(output, "%s %s %s-%d-%d %s %c %d\n", students[i].firstName,
                            students[i].lastName, students[i].birthMonth, students[i].birthDay,
                            students[i].birthYear, students[i].gpa, students[i].status,
                            students[i].toefl);
                } else {
                    fprintf(output, "%s %s %s-%d-%d %s %c\n", students[i].firstName,
                            students[i].lastName, students[i].birthMonth, students[i].birthDay,
                            students[i].birthYear, students[i].gpa, students[i].status);
                }
        }
    }
    fclose(output);
    free(students);
    return 0;
}

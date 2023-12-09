#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define INITIAL_CAPACITY 10000

typedef struct
{
    char firstName[100];
    char lastName[100];
    int day, month, year;
    char gpa[10];
} DomesticStudent;

typedef struct
{
    char firstName[100];
    char lastName[100];
    int day, month, year;
    char gpa[10];
    int toefl;
} InternationalStudent;

typedef struct
{
    int type; // 1 for Domestic, 2 for International
    union
    {
        DomesticStudent d;
        InternationalStudent i;
    } data;
} Student;

char *error = NULL;
int myIsDigit(char c);
int isValidGPA(const char *gpa);

// Comparison check
char toLower(char c)
{
    if (c >= 'A' && c <= 'Z')
    {
        return c - 'A' + 'a';
    }
    return c;
}

// Convert month string to integer
int monthToNum(char *month)
{
    char months[12][4] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    for (int i = 0; i < 12; i++)
    {
        if (toLower(month[0]) == toLower(months[i][0]) &&
            toLower(month[1]) == toLower(months[i][1]) &&
            toLower(month[2]) == toLower(months[i][2]))
        {
            return i + 1;
        }
    }
    return -1;
}

int compareStudents(const void *a, const void *b)
{
    Student *studentA = (Student *)a;
    Student *studentB = (Student *)b;

    // Year comparison
    if (studentA->data.d.year != studentB->data.d.year)
        return studentA->data.d.year - studentB->data.d.year;

    // Month comparison
    if (studentA->data.d.month != studentB->data.d.month)
        return studentA->data.d.month - studentB->data.d.month;

    // Day comparison
    if (studentA->data.d.day != studentB->data.d.day)
        return studentA->data.d.day - studentB->data.d.day;

    // Use strcasecmp for case-insensitive comparison
    int lastNameComparison = strcasecmp(studentA->data.d.lastName, studentB->data.d.lastName);
    if (lastNameComparison != 0)
        return lastNameComparison;

    int firstNameComparison = strcasecmp(studentA->data.d.firstName, studentB->data.d.firstName);
    if (firstNameComparison != 0)
        return firstNameComparison;

    float gpaA = atof(studentA->data.d.gpa);
    float gpaB = atof(studentB->data.d.gpa);
    if (gpaA != gpaB)
    {
        return (gpaA < gpaB) ? -1 : 1; // Assuming you want a descending order for GPA
    }

    // Type comparison: Domestic vs. International
    if (studentA->type != studentB->type)
        return studentA->type - studentB->type;

    // TOEFL comparison only if both are international
    if (studentA->type == 2 && studentB->type == 2)
        return studentA->data.i.toefl - studentB->data.i.toefl;

    return 0;
}

void mergeSort(Student students[], int l, int r)
{
    if (l < r)
    {
        int m = l + (r - l) / 2;
        mergeSort(students, l, m);
        mergeSort(students, m + 1, r);

        int n1 = m - l + 1;
        int n2 = r - m;

        Student L[n1], R[n2];
        for (int i = 0; i < n1; i++)
            L[i] = students[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = students[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2)
        {
            if (compareStudents(&L[i], &R[j]) <= 0)
            {
                students[k] = L[i];
                i++;
            }
            else
            {
                students[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            students[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            students[k] = R[j];
            j++;
            k++;
        }
    }
}

char globalError[200] = "";

int myIsDigit(char c) {
    return c >= '0' && c <= '9';
}

//Check the GPA using dot
int isValidGPA(const char *gpa) {
    int dotSeen = 0;
    while (*gpa) {
        if (*gpa == '.') {
            if (dotSeen) return 0; 
            dotSeen = 1;
        } else if (!myIsDigit(*gpa)) {
            return 0; 
        }
        gpa++;
    }
    return 1; 
}

void parseLine(char *line, Student *student)
{
    char month[4], status;
    int day, year, fieldsRead;
    char gpaStr[10];
    InternationalStudent iStudent;
    DomesticStudent dStudent;

    // Try reading the line as an International student
    fieldsRead = sscanf(line, "%s %s %3s-%d-%d %s %c %d",
                        iStudent.firstName, iStudent.lastName, month, &day, &year, gpaStr, &status, &iStudent.toefl);

    // Determine the type of student and perform validation
    if (status == 'I' && fieldsRead == 8)
    {
        // Handle International student
        int monthInt = monthToNum(month);

        if (monthInt == -1)
        {
            snprintf(globalError, sizeof(globalError), "Error: Invalid month in line: %s", line);
            return;
        }
        else if (day < 1 || day > 31)
        {
            snprintf(globalError, sizeof(globalError), "Error: Invalid day in line: %s", line);
            return;
        }
        else if (year < 1950 || year > 2010)
        {
            snprintf(globalError, sizeof(globalError), "Error: Invalid year in line: %s", line);
            return;
        }
        else if (iStudent.toefl < 0 || iStudent.toefl > 120)
        {
            snprintf(globalError, sizeof(globalError), "Error: Invalid TOEFL score in line: %s", line);
            return;
        }
        else
        {
            // If all checks pass, assign the values to the student struct
            iStudent.month = monthInt;
            iStudent.day = day;
            iStudent.year = year;
            strcpy(iStudent.gpa, gpaStr);
            student->type = 2;
            student->data.i = iStudent;
        }
    }
    else
    {
        // Reset fields and try reading the line as a Domestic student
        memset(&iStudent, 0, sizeof(iStudent));
        status = '\0';
        fieldsRead = sscanf(line, "%s %s %3s-%d-%d %s %c",
                            dStudent.firstName, dStudent.lastName, month, &day, &year, gpaStr, &status);
        if (status == 'D' && (fieldsRead == 7))
        {
            // Handle Domestic student
            int monthInt = monthToNum(month);
            if (monthInt == -1)
            {
                snprintf(globalError, sizeof(globalError), "Error: Invalid month in line: %s", line);
                return;
            }
            else if (day < 1 || day > 31)
            {
                snprintf(globalError, sizeof(globalError), "Error: Invalid day in line: %s", line);
                return;
            }
            else if (year < 1950 || year > 2010)
            {
                snprintf(globalError, sizeof(globalError), "Error: Invalid year in line: %s", line);
                return;
            }
            else
            {
                // If all checks pass, assign the values to the student struct
                dStudent.month = monthInt;
                dStudent.day = day;
                dStudent.year = year;
                strcpy(dStudent.gpa, gpaStr);
                student->type = 1;
                student->data.d = dStudent;
            }
        }
        else
        {
            // If neither International nor Domestic student line formats match, print an error message
            snprintf(globalError, sizeof(globalError), "Error: Invalid line format: %s", line);
            return;
        }
    }
    // Additional error handling for out-of-range values or missing data
    if (day == -1 || year == -1)
    {
        snprintf(globalError, sizeof(globalError), "Error: Missing or invalid date or GPA in line: %s", line);
        return;
    }
    if (strlen(month) < 3)
    {
        snprintf(globalError, sizeof(globalError), "Error: Missing or invalid month in line: %s", line);
        return;
    }
    
    
char* ptr = gpaStr;
    int dotCount = 0; 
    while (*ptr) {
        if (!myIsDigit(*ptr) && *ptr != '.') {
            snprintf(globalError, sizeof(globalError), "Error: Invalid GPA format in line: %s", line);
            return;
        }
        if (*ptr == '.') {
            dotCount++;
            if (dotCount > 1) { 
                snprintf(globalError, sizeof(globalError), "Error: Invalid GPA format in line: %s", line);
                return;
            }
        }
        ptr++;
    }

    if (!isValidGPA(gpaStr)) {
        snprintf(globalError, sizeof(globalError), "Error: GPA contains invalid characters in line: %s", line);
        return;
    }

}



void writeToFile(Student students[], int count, int option, FILE *output)
{
    for (int i = 0; i < count; i++)
    {
        if (option == 1 && students[i].type == 2)
            continue;
        if (option == 2 && students[i].type == 1)
            continue;

        char month[4];
        int monthNum = students[i].data.d.month;
        switch (monthNum)
        {
        case 1:
            strcpy(month, "Jan");
            break;
        case 2:
            strcpy(month, "Feb");
            break;
        case 3:
            strcpy(month, "Mar");
            break;
        case 4:
            strcpy(month, "Apr");
            break;
        case 5:
            strcpy(month, "May");
            break;
        case 6:
            strcpy(month, "Jun");
            break;
        case 7:
            strcpy(month, "Jul");
            break;
        case 8:
            strcpy(month, "Aug");
            break;
        case 9:
            strcpy(month, "Sep");
            break;
        case 10:
            strcpy(month, "Oct");
            break;
        case 11:
            strcpy(month, "Nov");
            break;
        case 12:
            strcpy(month, "Dec");
            break;
        default:
            strcpy(month, "");
        }

        if (students[i].type == 1)
        {
            fprintf(output, "%s %s %s-%d-%d %s D\n", students[i].data.d.firstName, students[i].data.d.lastName, month, students[i].data.d.day, students[i].data.d.year, students[i].data.d.gpa);
        }
        else
        {
            fprintf(output, "%s %s %s-%d-%d %s I %d\n", students[i].data.i.firstName, students[i].data.i.lastName, month, students[i].data.i.day, students[i].data.i.year, students[i].data.i.gpa, students[i].data.i.toefl);
        }
    }
}

int main(int argc, char *argv[])
{
    if (argc != 4)
    {
        printf("Usage: ./<name of executable> <input file> <output file> <option>\n");
        return 1;
    }

    // A numbers of everyone. AXXXX_AXXXX_AXXX format.
    char *ANum = "A01330048_A01263922";
    FILE *ANumFile = fopen(ANum, "w");
    if (!ANumFile)
    {
        printf("Failed to create the ANum output file.\n");
        return 1;
    }
    fclose(ANumFile);

     double optionDouble = atof(argv[3]);
    int option = (int)optionDouble;

    if (optionDouble != (double)option)
    {
        fprintf(stderr, "Error: Invalid option format. Option should be an integer.\n");
        return 1;
    }

    if (option < 1 || option > 3)
    {
        fprintf(stderr, "Error: Invalid option. Choose from 1,2, or 3.\n");
        return 1;
    }
    
    FILE *inputFile = fopen(argv[1], "r");
    if (!inputFile)
    {
        printf("Error: Cannot open the input file.\n");
        return 1;
    }

    char line[10000];
    int studentCount = 0;

    // initial size of the array
    Student *students = (Student *)malloc(INITIAL_CAPACITY * sizeof(Student));
    int maxStudents = INITIAL_CAPACITY;

    while (fgets(line, sizeof(line), inputFile))
    {
        // array memory dynamic allocation
        if (studentCount >= maxStudents)
        {
            maxStudents *= 2; // doubling the size
            students = realloc(students, maxStudents * sizeof(Student));
            if (!students)
            {
                fprintf(stderr, "Error: Memory allocation failed.\n");
                fclose(inputFile);
                return 1;
            }
        }

        parseLine(line, &students[studentCount]);
        if (strlen(globalError) > 0)
        {
            break;
        }

        studentCount++;
    }
    fclose(inputFile);

    FILE *outputFile = fopen(argv[2], "w");
    if (!outputFile)
    {
        printf("Error: Cannot open the output file.\n");
        return 1;
    }

    if (strlen(globalError) > 0)
    {

        fputs(globalError, outputFile);
        fclose(outputFile);
        exit(1);
    }

    mergeSort(students, 0, studentCount - 1);

   
    writeToFile(students, studentCount, option, outputFile);
    fclose(outputFile);

    return 0;
}

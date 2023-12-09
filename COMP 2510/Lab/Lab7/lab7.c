#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the structure for a student.
typedef struct {
    char last_name[50];
    char first_name[50];
    char student_number[9]; // 'A' followed by 7 digits and a null terminator
    int midterm_grade;
    int final_grade;
} Student;

// Function prototypes.
int compareStudents(const void *s1, const void *s2);
int categorizeAverage(int midterm, int final);
void printStudents(FILE *file, const Student *students, int size, int option);

// Compare students for sorting.
int compareStudents(const void *s1, const void *s2) {
    Student *student1 = (Student *)s1;
    Student *student2 = (Student *)s2;
    int last = strcmp(student1->last_name, student2->last_name);
    if (last != 0) return last;
    
    int first = strcmp(student1->first_name, student2->first_name);
    if (first != 0) return first;
    
    int number = strcmp(student1->student_number, student2->student_number);
    if (number != 0) return number;
    
    if (student1->midterm_grade != student2->midterm_grade)
        return student1->midterm_grade - student2->midterm_grade;
    
    return student1->final_grade - student2->final_grade;
}

// Categorize the average grade into a category.
int categorizeAverage(int midterm, int final) {
    float average = (midterm + final) / 2.0;
    if (average > 90) return 1;        // 평균이 90% 초과
    else if (average > 80) return 2;    // 평균이 80% 초과 90% 이하
    else if (average > 70) return 3;    // 평균이 70% 초과 80% 이하
    else if (average > 60) return 4;    // 평균이 60% 초과 70% 이하
    else return 5;                      // 평균이 60% 이하
}

// Print students to the given file based on the option selected.
void printStudents(FILE *file, const Student *students, int size, int option) {
    for (int i = 0; i < size; ++i) {
        int category = categorizeAverage(students[i].midterm_grade, students[i].final_grade);
        if (option == category) {
            fprintf(file, "%s %s %s %d %d\n",
                    students[i].first_name, students[i].last_name,
                    students[i].student_number, students[i].midterm_grade,
                    students[i].final_grade);
        }
    }
}

int main(int argc, char *argv[]) {
    // Check for proper usage.
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <input file> <output file> <option>\n", argv[0]);
        return 1;
    }
    
    // Open the input file for reading.
    FILE *inputFile = fopen(argv[1], "r");
    if (!inputFile) {
        perror("Error opening input file");
        return 1;
    }
    
    // Read students from the input file and count them.
    int count = 0;
    char tempBuffer[256];
    while (fgets(tempBuffer, sizeof(tempBuffer), inputFile)) {
        count++;
    }
    rewind(inputFile);
    
    // Allocate memory for all students.
    Student *students = (Student *)malloc(count * sizeof(Student));
    if (students == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        fclose(inputFile);
        return 1;
    }
    
    // Read student data into the array.
    for (int i = 0; i < count; ++i) {
        fscanf(inputFile, "%49s %49s %8s %d %d",
                students[i].first_name,students[i].last_name,
               students[i].student_number,
               &students[i].midterm_grade, &students[i].final_grade);
    }
    fclose(inputFile);
    
    // Sort students by last name using qsort.
    qsort(students, count, sizeof(Student), compareStudents);
    
    // Open the output file for writing.
    FILE *outputFile = fopen(argv[2], "w");
    if (!outputFile) {
        perror("Error opening output file");
        free(students);
        return 1;
    }
    
    // Convert the option to an integer.
    int option = atoi(argv[3]);

    if(option<1 || option >5){
        fprintf(stderr, "Invalid option. Option must be between 1 and 5.\n");
        return 1;
    }
    
    // Print students to the output file based on the selected option.
    printStudents(outputFile, students, count, option);
    
    fclose(outputFile);
    free(students);
    return 0;
}
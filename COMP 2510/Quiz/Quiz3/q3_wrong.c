#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long long multiplyNums(unsigned long long a, unsigned long long b) {
    long long result = 0;
    int shift = 0;

    while (b > 0) {
        if (b & 1) {
            result += (long long)a << shift;
        }
        b >>= 1;
        shift++;
    }

    return result;
}


int main(int argc, char * argv[])
{
    if(argc != 3)
    {
        printf("Usage: %s <hex_number1> <hex_number2>\n", argv[0]);
        return 1;
    }


    unsigned long long number1 = atoi(argv[1]);
    unsigned long long number2 = atoi(argv[2]);
    long long result;
    result = multiplyNums(number1, number2);
    printf("%llx\n", result);
    

    char *ANum = "A01263922";
    FILE *outputFile = fopen(ANum, "w");
    if (outputFile == NULL)
    {
        printf("Failed to create the output file.\n");
        return 1;
    }
    fclose(outputFile);

    return 0;
}
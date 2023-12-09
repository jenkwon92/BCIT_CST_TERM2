#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <inttypes.h>

// Function to add two numbers using bitwise operations
int64_t logicaladder(int64_t a, int64_t b) {
    int64_t carry;
    
    while (b != 0) {
        carry = a & b;
        // printf("carry: %lld\n", carry);
        // printf("a: %lld\n", a);
        // printf("b: %lld\n", b);
        a = a ^ b;
        b = carry << 1;

        // printf("after a: %lld\n", a);
        // printf("after carry b: %lld\n", b);
    }
    return a;
}

// Function to detect overflow for given bitwidth
int detectoverflow(int64_t a, int64_t b, int64_t bitwidth) {
    int64_t max_value = (1LL << (bitwidth - 1)) - 1;
    int64_t min_value = -(1LL << (bitwidth - 1));

    if ((a > 0 && b > 0 && a + b < 0) || (a < 0 && b < 0 && a + b > 0)) {
        return 1; // 오버플로우 발생
    }
    return (a + b > max_value || a + b < min_value);
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        fprintf(stderr, "Usage: %s <bitwidth(8,16,32,64)> <number1> <number2> <shift number>\n", argv[0]);
        return 1;
    }

    int bitwidth = atoi(argv[1]);
    int64_t number1 = strtoll(argv[2], NULL, 10);
    int64_t number2 = strtol(argv[3], NULL, 10);
    int shift = atoi(argv[4]);

    if (bitwidth != 8 && bitwidth != 16 && bitwidth != 32 && bitwidth != 64) {
        fprintf(stderr, "Invalid bitwidth. Bitwidth should be 8, 16, 32, or 64.\n");
        return 1;
    }
    if(shift < 0 || shift > bitwidth) {
        fprintf(stderr, "Invalid shift number. Shift number should be between 0 and bitwidth.\n");
        return 1;
    }

    int64_t max_value = (1LL << (bitwidth - 1)) - 1;
    int64_t min_value = -(1LL << (bitwidth - 1));
    // printf("max_value: %" PRId64 "\n", max_value);
    // printf("min_value: %" PRId64 "\n", min_value);

    if(number1 > max_value || number1 < min_value || number2 > max_value || number2 < min_value) {
        fprintf(stderr, "Invalid number. Number should be between %lld and %lld.\n", min_value, max_value);
        return 1;
    }
    // Perform addition
    int64_t result = logicaladder(number1, number2);

    // Check for overflow and apply right shift if necessary
  if (detectoverflow(number1, number2, bitwidth)) {
    result = result >> shift;
    printf("Result after right shift:  %" PRId64 "\n", result);
    printf("Overflow detected within the specified bitwidth.\n");
} else {
    printf("Result of addition:  %" PRId64 "\n", result);
}
    return 0;
}

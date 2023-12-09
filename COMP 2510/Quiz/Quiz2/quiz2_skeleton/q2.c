#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *ANum = "A0000";

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {

}

struct ListNode* createLinkedList(char* numStr) {
    struct ListNode *head = NULL, *tail = NULL;
    for (int i = 0; numStr[i] != '\0'; i++) {
        struct ListNode* newNode = malloc(sizeof(struct ListNode));
        newNode->val = numStr[i] - '0';
        newNode->next = NULL;
        if (head == NULL) {
            head = tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }
    return head;
}

void printLinkedList(struct ListNode* head) {
    struct ListNode* current = head;
    while (current != NULL) {
        printf("%d", current->val);
        if (current->next != NULL) {
            printf(" -> ");
        }
        current = current->next;
    }
    printf("\n");
}

void freeLinkedList(struct ListNode* head) {
    while (head != NULL) {
        struct ListNode* temp = head;
        head = head->next;
        free(temp);
    }
}

int main() {
    FILE *inputFile = fopen("input.txt", "r");
    if (inputFile == NULL) {
        printf("Failed to open the input file.\n");
        return 1;
    }

    char num1[256], num2[256];
    fgets(num1, sizeof(num1), inputFile);
    fgets(num2, sizeof(num2), inputFile);
    fclose(inputFile);

    // Remove newline characters read by fgets
    num1[strcspn(num1, "\n")] = '\0';
    num2[strcspn(num2, "\n")] = '\0';

    struct ListNode* l1 = createLinkedList(num1);
    struct ListNode* l2 = createLinkedList(num2);

    struct ListNode* result = addTwoNumbers(l1, l2);

    printLinkedList(result);

    freeLinkedList(l1);
    freeLinkedList(l2);
    freeLinkedList(result);

    FILE *outputFile = fopen(ANum, "w");

    if (outputFile == NULL) {
      printf("Failed to create the output file.\n");
      return 1;
    }

    fclose(outputFile);
    
    return 0;
}

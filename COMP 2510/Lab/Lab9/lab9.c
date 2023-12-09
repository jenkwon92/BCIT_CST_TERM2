#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum
{
    TYPE_INT,
    TYPE_CHAR,
    TYPE_DOUBLE,
    TYPE_FLOAT,
    TYPE_STRING
} DataType;

typedef struct Node
{
    void *key;
    DataType keyType;
    void *value;
    DataType valueType;
    size_t keySize;
    size_t valueSize;
    struct Node *next;
    struct Node *prev;
} Node;

void *getInput(size_t *size, DataType *type, const char *dataType)
{

    void *data;
    char input[256];

    printf("Insert %s: ", dataType);
    scanf("%s", input);

    printf("%s data size: ", dataType);
    scanf("%zu", size);

    switch (*size)
    {
    case 1:
        if (strlen(input) == 1)
        {
            *type = TYPE_CHAR;
            *size = sizeof(char);
            char *data = malloc(*size);
            *data = input[0];
            return data;
        }
        else
        {
            *type = TYPE_STRING;
            *size = strlen(input) + 1;
            char *data = malloc(*size);
            strcpy(data, input);
            return data;
        }
        break;
    case 4: // int or float
        if (strchr(input, '.') != NULL)
        {
            *type = TYPE_FLOAT;
            data = malloc(sizeof(float));
            *(float *)data = atof(input);
        }
        else
        {
            *type = TYPE_INT;
            data = malloc(sizeof(int));
            *(int *)data = atoi(input);
        }
        break;
    case 8: // double
        *type = TYPE_DOUBLE;
        data = malloc(sizeof(double));
        *(double *)data = atof(input);
        break;
    default:
        printf("Invalid data size.\n");
        exit(EXIT_FAILURE);
    }

    return data;
}

Node *createNode(void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->key = malloc(keySize);
    newNode->value = malloc(valueSize);
    memcpy(newNode->key, key, keySize);
    memcpy(newNode->value, value, valueSize);
    newNode->keyType = keyType;
    newNode->valueType = valueType;
    newNode->keySize = keySize;
    newNode->valueSize = valueSize;
    newNode->next = newNode->prev = NULL;
    return newNode;
}

void insertNode(Node **head, void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize)
{
    Node *newNode = createNode(key, keyType, keySize, value, valueType, valueSize);
    if (*head == NULL)
    {
        *head = newNode;
    }
    else
    {
        newNode->next = *head;
        (*head)->prev = newNode;
        *head = newNode;
    }
}

void printKeys(Node *head)
{
    Node *current = head;
    while (current != NULL)
    {
        switch (current->keyType)
        {
        case TYPE_INT:
            printf("%d\n", *(int *)(current->key));
            break;
        case TYPE_STRING:
            printf("%s\n", (char *)(current->key));
            break;
        case TYPE_CHAR:
            printf("%c\n", *(char *)(current->key));
            break;
        case TYPE_DOUBLE:
            printf("%f\n", *(double *)(current->key));
            break;
        case TYPE_FLOAT:
            printf("%f\n", *(float *)(current->key));
            break;
        }
        current = current->next;
    }
}

void printValues(Node *head)
{
    Node *current = head;
    while (current != NULL)
    {
        switch (current->valueType)
        {
        case TYPE_INT:
            printf("%d\n", *(int *)(current->value));
            break;
        case TYPE_STRING:
                printf("%s\n", (char *)(current->value));
                break;
        case TYPE_CHAR:
            printf("%c\n", *(char *)(current->value));
            break;
        case TYPE_DOUBLE:
            printf("%lf\n", *(double *)(current->value));
            break;
        case TYPE_FLOAT:
            printf("%f\n", *(float *)(current->value));
            break;
        }
        current = current->next;
    }
}

void saveToFile(Node *head, const char *filename)
{
    FILE *file = fopen(filename, "wb");
    if (!file)
    {
        perror("Unable to open file");
        return;
    }

    Node *current = head;
    while (current != NULL)
    {
        fwrite(&current->keyType, sizeof(current->keyType), 1, file);
        fwrite(&current->keySize, sizeof(current->keySize), 1, file);
        fwrite(current->key, current->keySize, 1, file);
        fwrite(&current->valueType, sizeof(current->valueType), 1, file);
        fwrite(&current->valueSize, sizeof(current->valueSize), 1, file);
        fwrite(current->value, current->valueSize, 1, file);
        current = current->next;
    }
    fclose(file);
}

Node *restoreFromFile(const char *filename)
{
    FILE *file = fopen(filename, "rb");
    if (!file)
    {
        perror("Unable to open file");
        return NULL;
    }

    Node *head = NULL;
    DataType keyType, valueType;
    size_t keySize, valueSize;

    while (fread(&keyType, sizeof(keyType), 1, file) == 1)
    {
        fread(&keySize, sizeof(keySize), 1, file);
        void *key = malloc(keySize);
        fread(key, keySize, 1, file);

        fread(&valueType, sizeof(valueType), 1, file);
        fread(&valueSize, sizeof(valueSize), 1, file);
        void *value = malloc(valueSize);
        fread(value, valueSize, 1, file);

        insertNode(&head, key, keyType, keySize, value, valueType, valueSize);

        free(key);
        free(value);
    }
    fclose(file);
    return head;
}

void freeList(Node *head)
{
    Node *current = head;
    while (current != NULL)
    {
        Node *temp = current;
        current = current->next;
        free(temp->key);
        free(temp->value);
        free(temp);
    }
}

int main()
{
    Node *head = NULL;
    int choice;
    char filename[100];
    DataType keyType, valueType;

    do {
        // 메뉴 출력 및 선택
        printf("\nMenu:\n");
        printf("1) Print Keys\n");
        printf("2) Print Values\n");
        printf("3) Insert KV Pair\n");
        printf("4) Save\n");
        printf("5) Restore\n");
        printf("6) Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar();

        switch (choice) 
        {
        case 1:
            printKeys(head);
            break;
        case 2:
            printValues(head);
            break;
        case 3:
        {
            size_t keySize, valueSize;
            void *key, *value;

            key = getInput(&keySize, &keyType, "Key");
            value = getInput(&valueSize, &valueType, "Value");

            insertNode(&head, key, keyType, keySize, value, valueType, valueSize);

            free(key);
            free(value);
            break;
        }
        case 4:
            printf("Type the file name: ");
            scanf("%s", filename);
            saveToFile(head, filename);
            break;
        case 5:
            printf("Type the file name: ");
            scanf("%s", filename);
            freeList(head);
            head = restoreFromFile(filename);
            break;
        case 6:
            printf("Exiting program.\n");
            freeList(head);
            return 0;
        default:
            printf("Invalid choice. Please try again.\n");
            break;
        }
    } while (choice != 6);
    return 0;
}
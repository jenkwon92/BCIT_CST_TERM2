#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure definition for the binary tree node
typedef struct Node
{
    char *firstName;
    char *lastName;
    struct Node *left;
    struct Node *right;
} Node;

// Function to create a new node
Node *createNode(char *firstName, char *lastName)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    if (newNode == NULL)
    {
        fprintf(stderr, "Error: Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    newNode->firstName = strdup(firstName);
    newNode->lastName = strdup(lastName);
    newNode->left = newNode->right = NULL;
    return newNode;
}

// Function to add a node to the binary search tree
Node *addNode(Node *root, char *firstName, char *lastName)
{
    if (root == NULL)
    {
        return createNode(firstName, lastName);
    }

    int cmp = strcmp(firstName, root->firstName);
    if (cmp == 0)
    {
        cmp = strcmp(lastName, root->lastName);
        // Allow duplicates
    }

    if (cmp < 0)
    {
        root->left = addNode(root->left, firstName, lastName);
    }
    else
    {
        root->right = addNode(root->right, firstName, lastName);
    }

    return root;
}

// Function to find the node with the minimum value in a tree
Node *minValueNode(Node *node)
{
    Node *current = node;
    while (current && current->left != NULL)
    {
        current = current->left;
    }
    return current;
}

// Function to delete a node from the binary search tree
Node *deleteNode(Node *root, char *firstName, char *lastName, FILE *output)
{
    if (root == NULL)
    {
        return addNode(root, firstName, lastName); // Add the node if not found
    }

    int cmp = strcmp(firstName, root->firstName);
    if (cmp == 0)
    {
        cmp = strcmp(lastName, root->lastName);
    }

    if (cmp < 0)
    {
        root->left = deleteNode(root->left, firstName, lastName, output);
    }
    else if (cmp > 0)
    {
        root->right = deleteNode(root->right, firstName, lastName, output);
    }
    else
    {
        // Node with only one child or no child
        if (root->left == NULL)
        {
            Node *temp = root->right;
            free(root->firstName);
            free(root->lastName);
            free(root);
            return temp;
        }
        else if (root->right == NULL)
        {
            Node *temp = root->left;
            free(root->firstName);
            free(root->lastName);
            free(root);
            return temp;
        }

        // Node with two children: Get the inorder successor
        Node *temp = minValueNode(root->right);

        // Copy the inorder successor's content to this node
        free(root->firstName);
        free(root->lastName);
        root->firstName = strdup(temp->firstName);
        root->lastName = strdup(temp->lastName);

        // Delete the inorder successor
        root->right = deleteNode(root->right, temp->firstName, temp->lastName, output);
    }

    return root;
}

// Function to search for a node in the binary search tree
int searchNode(Node *root, char *firstName, char *lastName, FILE *output)
{
    if (root == NULL)
    {
        fprintf(output, "Not Found\n");
        return 0;
    }

    int cmp = strcmp(firstName, root->firstName);
    if (cmp == 0)
    {
        cmp = strcmp(lastName, root->lastName);
    }

    if (cmp < 0)
    {
        return searchNode(root->left, firstName, lastName, output);
    }
    else if (cmp > 0)
    {
        return searchNode(root->right, firstName, lastName, output);
    }
    else // Node found
    {
        fprintf(output, "Found\n");
        return 1;
    }
}

void inOrderTraversal(Node *root, FILE *output)
{
    if (root != NULL)
    {
        inOrderTraversal(root->left, output);
        fprintf(output, "%s %s\n", root->firstName, root->lastName);
        inOrderTraversal(root->right, output);
    }
}

// Function to free the memory allocated for the tree nodes
void freeTree(Node *root)
{
    if (root != NULL)
    {
        freeTree(root->left);
        freeTree(root->right);
        free(root->firstName);
        free(root->lastName);
        free(root);
    }
}

int main(int argc, char *argv[])
{
    // Check for the correct number of command-line arguments
    if (argc != 3)
    {
        fprintf(stderr, "Error: Invalid number of arguments\n");
        fprintf(stderr, "./<executable> input.txt output.txt\n");
        return 1;
    }

    // Open the input file for reading
    FILE *inputFile = fopen(argv[1], "r");
    if (inputFile == NULL)
    {
        fprintf(stderr, "Error: Unable to open input file\n");
        return 1;
    }

    // Open the output file for writing
    FILE *outputFile = fopen(argv[2], "w");
    if (outputFile == NULL)
    {
        fprintf(stderr, "Error: Unable to open output file\n");
        fclose(inputFile);
        return 1;
    }

    char *ANum = "A01330048_A01263922";
    FILE *ANumFile = fopen(ANum, "w");
    if (!ANumFile)
    {
        printf("Failed to create the ANum output file.\n");
        return 1;
    }
    fclose(ANumFile);

    // Initialize the root of the binary search tree
    Node *root = NULL;
    char *firstName = (char *)malloc(1000 * sizeof(char));
    char *lastName = (char *)malloc(1000 * sizeof(char));
    int operation;

    char *line = NULL;
    size_t len = 0;

    // Read each line from the input file
    while (getline(&line, &len, inputFile) != -1)
    {
        // Check if the line contains names and an operation
        if (sscanf(line, "%s %s %d", firstName, lastName, &operation) == 3)
        {
            // Dynamically allocate memory if the names exceed the initial size
            if (strlen(firstName) >= 1000)
            {
                free(firstName);
                firstName = strdup(line);
                firstName[strlen(firstName) - 1] = '\0'; // Remove newline character
            }
            if (strlen(lastName) >= 1000)
            {
                free(lastName);
                lastName = strdup(line);
                lastName[strlen(lastName) - 1] = '\0'; // Remove newline character
            }

            switch (operation)
            {
            case 1:
                root = addNode(root, firstName, lastName);
                break;
            case 2:
                root = deleteNode(root, firstName, lastName, outputFile);
                break;
            case 3:
                searchNode(root, firstName, lastName, outputFile);
                break;
            default:
                fprintf(stderr, "Error: Invalid operation\n");
                fclose(inputFile);
                fclose(outputFile);
                freeTree(root);
                free(line);
                free(firstName);
                free(lastName);
                return 1; // Exit the program if there's an invalid operation
            }
        }
        else if (sscanf(line, "%d", &operation) == 1)
        {
            // Operation without names (assuming operation is 4)
            if (operation == 4)
            {
                inOrderTraversal(root, outputFile);
            }else{
                fprintf(stderr, "Error: Invalid operation\n");
                fclose(inputFile);
                fclose(outputFile);
                freeTree(root);
                free(line);
                free(firstName);
                free(lastName);
                return 1; // Exit the program if there's an invalid operation
            }
        }
        else
        {
            fprintf(stderr, "Error: Invalid input line\n");
            fclose(inputFile);
            fclose(outputFile);
            freeTree(root);
            free(line);
            free(firstName);
            free(lastName);
            return 1; // Exit the program if there's a format error
        }
    }

    // Close the input and output files
    fclose(inputFile);
    fclose(outputFile);
    freeTree(root);
    free(line);
    free(firstName);
    free(lastName);

    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct {
    char* name;
    int operation;
} Instruction;

// Create Node
typedef struct Node {
  char* key;
  struct Node *left;
  struct Node *right;
  int height;
  int frequency;
} Node;

// Calculate height
int height(Node *N) {
  if (N == NULL)
    return 0;
  return N->height;
}

int max(int a, int b) {
  return (a > b) ? a : b;
}

// Create a node
Node *newNode(const char* key) {
  Node *node = (Node *)malloc(sizeof(Node));
  node->key = strdup(key); // Dynamically allocate and copy the string
  node->left = NULL;
  node->right = NULL;
  node->height = 1;
  return (node);
}

// Right rotate
Node *rightRotate(Node *y) {
  Node *x = y->left;
  Node *T2 = x->right;

  x->right = y;
  y->left = T2;

  y->height = max(height(y->left), height(y->right)) + 1;
  x->height = max(height(x->left), height(x->right)) + 1;

  return x;
}

// Left rotate
Node *leftRotate(Node *x) {
  Node *y = x->right;
  Node *T2 = y->left;

  y->left = x;
  x->right = T2;

  x->height = max(height(x->left), height(x->right)) + 1;
  y->height = max(height(y->left), height(y->right)) + 1;

  return y;
}

// Get the balance factor
int getBalance(Node *N) {
  if (N == NULL)
    return 0;
  return height(N->left) - height(N->right);
}

// Insert node
Node *insertNode(Node *node, const char* key) {
    if (node == NULL) {
        Node *new_node = newNode(key);
        new_node->frequency = 1;
        return new_node;
    }

    int cmp = strcmp(key, node->key);
    if (cmp < 0) {
        node->left = insertNode(node->left, key);
    } else if (cmp > 0) {
        node->right = insertNode(node->right, key);
    } else {
        node->frequency += 1;  // Increment frequency for duplicate key
        return node;
    }

  // Update the balance factor of each node and
  // Balance the tree
  node->height = 1 + max(height(node->left),
               height(node->right));

  int balance = getBalance(node);
  if (balance > 1 && strcmp(key, node->left->key) < 0)
    return rightRotate(node);

  if (balance < -1 && strcmp(key, node->right->key) > 0)
      return leftRotate(node);

  if (balance > 1 && strcmp(key, node->left->key) > 0) {
      node->left = leftRotate(node->left);
      return rightRotate(node);
  }

  if (balance < -1 && strcmp(key, node->right->key) < 0) {
      node->right = rightRotate(node->right);
      return leftRotate(node);
  }

  return node;
}

Node *minValueNode(Node *node) {
  Node *current = node;

  while (current->left != NULL)
    current = current->left;

  return current;
}

int nodeFound = 1;

// Delete a nodes
Node *deleteNode(Node *root, const char* key) {
    if (root == NULL) {
        // Key not found, insert the key and return the new node
        nodeFound = 0;
        return NULL;
    }

    int cmp = strcmp(key, root->key);
    if (cmp < 0) {
        root->left = deleteNode(root->left, key);
    } else if (cmp > 0) {
        root->right = deleteNode(root->right, key);
    } else {
        // Check frequency before deleting
        if (root->frequency > 1) {
            root->frequency -= 1;
            return root;
        }
      
        // Node with the same key found, perform deletion
        if ((root->left == NULL) || (root->right == NULL)) {
            Node *temp = root->left ? root->left : root->right;

            if (temp == NULL) {
                temp = root;
                root = NULL;
            } else {
                Node *newNode = malloc(sizeof(Node));
                if (!newNode) {
                    perror("Failed to allocate memory for new node");
                    exit(EXIT_FAILURE);
                }
                *newNode = *temp;
                free(root->key);
                free(root);
                root = newNode;
            }
        } else {
            Node *temp = minValueNode(root->right);
            free(root->key); // Free the existing key's memory
            root->key = strdup(temp->key); // Assign new key
            root->right = deleteNode(root->right, temp->key); // Delete the inorder successor
        }

        if (root == NULL)
            return root;
    }

    root->height = 1 + max(height(root->left), height(root->right));
    int balance = getBalance(root);

    // Left Right Case
    if (balance > 1 && getBalance(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    // Left Left Case
    if (balance > 1 && getBalance(root->left) >= 0) {
        return rightRotate(root);
    }

    // Right Left Case
    if (balance < -1 && getBalance(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    // Right Right Case
    if (balance < -1 && getBalance(root->right) <= 0) {
        return leftRotate(root);
    }

    return root;
}

// Print the tree
void printInOrder(FILE* ofile, Node *root) {
  if (root != NULL) {
    printInOrder(ofile, root->left);
    for (int i = 0; i < root->frequency; i++)
    {
        fprintf(ofile, "%s\n", root->key);
    }
    printInOrder(ofile, root->right);
  }
}

void process_line(char* line, Instruction* instr, FILE* ofile) {
    // Free any previously allocated name
    free(instr->name);

    // Check if the line is a traversal operation (which doesn't have a name)
    if (line[0] == '4' && (line[1] == '\n' || line[1] == '\0')) {
        instr->name = NULL;
        instr->operation = 4;
    } else {
        // Allocate memory for the name
        instr->name = malloc(strlen(line) + 1); // +1 for null terminator
        if (instr->name == NULL) {
            perror("Failed to allocate memory for name");
            exit(EXIT_FAILURE);
        }
        if (sscanf(line, "%[^\n0123456789] %d", instr->name, &instr->operation) != 2) {
            fprintf(ofile, "ERROR: INVALID INPUT\n");
            exit(EXIT_FAILURE);
        }
        instr->name[strlen(line) - 2] = '\0'; 
        int spaceCounter = 0;
        for (int i = 0; i < strlen(instr->name); i++) {
            if (instr->name[i] == ' ') spaceCounter++;
        }
        if (spaceCounter != 1) {
            fprintf(ofile, "ERROR: INVALID INPUT\n");
            exit(EXIT_FAILURE);
        }
    }
}



// Function to search for a key in the tree
void searchNode(FILE* ofile, Node *root, const char* key) {
    if (root == NULL) {
        fprintf(ofile, "Not Found\n");
        return;
    }

    int cmp = strcmp(key, root->key);
    if (cmp < 0) {
        searchNode(ofile, root->left, key);
    } else if (cmp > 0) {
        searchNode(ofile, root->right, key);
    } else {
        fprintf(ofile, "Found\n");
    }
}

void read_and_parse_file(const char* filename, const char* outputname, Node** root) {
    FILE* file = fopen(filename, "r");
    if (!file) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    FILE* ofile = fopen(outputname, "w");
    if (!ofile) {
        perror("Error opening output file");
        fclose(file); // Close the input file before exiting
        exit(EXIT_FAILURE);
    }

    char *line = NULL;
    size_t len = 0;
    ssize_t read;
    Instruction instr;
    instr.name = NULL; // Initialize to NULL

    while ((read = getline(&line, &len, file)) != -1) {
        // Remove newline character if present
        if (line[read - 1] == '\n') {
            line[read - 1] = '\0';
        }

        process_line(line, &instr, ofile);

        switch (instr.operation) {
            case 1:
                *root = insertNode(*root, instr.name);
                break;
            case 2:
                nodeFound = 1;
                *root = deleteNode(*root, instr.name);
                if (!nodeFound) {
                    *root = insertNode(*root, instr.name);
                }
                break;
            case 3:
                searchNode(ofile, *root, instr.name);
                break;
            case 4:
                printInOrder(ofile, *root);
                break;
        }
    }

    free(line); // Free the line buffer
    free(instr.name); // Free any remaining allocated name
    fclose(file);
    fclose(ofile);
}

void freeTree(Node* node) {
    if (node == NULL) {
        return;
    }

    // Recursively free the left and right subtrees
    freeTree(node->left);
    freeTree(node->right);

    // Free the key and the node itself
    free(node->key);
    free(node);
}

int main(int argc, char** argv) {

    if (argc != 3) {
        perror("Usage: <executable> <input_file> <output_file>\n");
        exit(EXIT_FAILURE);
    }
    
    const char* inputname = argv[1];
    const char* outputname = argv[2];

    Node *root = NULL;
    read_and_parse_file(inputname, outputname, &root);
    freeTree(root);

    return 0;
}
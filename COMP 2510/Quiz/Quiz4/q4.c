#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node.
struct TreeNode
{
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

// Helper function to find the node with the minimum value in a BST
struct TreeNode *minValueNode(struct TreeNode *node)
{
    struct TreeNode *current = node;
    // Loop down to find the leftmost leaf
    while (current && current->left != NULL)
    {
        current = current->left;
    }
    return current;
}

struct TreeNode *deleteNode(struct TreeNode *root, int key)
{
    // Base case: if the tree is empty
    if (root == NULL)
    {
        return root;
    }
    if (key < root->val)
    {
        root->left = deleteNode(root->left, key);
    }
    else if (key > root->val)
    {
        root->right = deleteNode(root->right, key);
    }
    else
    {
        // Case 1: Node with no child or only one child
        if (root->left == NULL)
        {
            struct TreeNode *temp = root->right;
            free(root);
            return temp;
        }
        else if (root->right == NULL)
        {
            struct TreeNode *temp = root->left;
            free(root);
            return temp;
        }
        // Case 2: Node with two children
        // Get the inorder successor (smallest in the right subtree)
        struct TreeNode *temp = minValueNode(root->right);
        // Copy the inorder successor's content to this node
        root->val = temp->val;
        // Delete the inorder successor
        root->right = deleteNode(root->right, temp->val);
    }

    return root; // Return the modified tree root
}

// Helper function to create a new TreeNode
struct TreeNode *newNode(int val)
{
    struct TreeNode *node = (struct TreeNode *)malloc(sizeof(struct TreeNode));
    node->val = val;
    node->left = NULL;
    node->right = NULL;
    return node;
}

// funtion to print the tree
void printTree(struct TreeNode *root)
{
    if (root == NULL)
    {
        return;
    }
    printTree(root->left);
    printf("%d ", root->val);
    printTree(root->right);
}

// Main function to demonstrate delete operation
int main()
{
    struct TreeNode *root = newNode(5);
    root->left = newNode(3);
    root->right = newNode(6);
    root->left->left = newNode(2);
    root->left->right = newNode(4);
    root->right->right = newNode(7);

printTree(root);
    // Deleting a node
    root = deleteNode(root, 3);

    // The root now should be the root of the BST with the node with value 3 deleted.
    // Add code here to print the tree, if needed.
printTree(root);
    return 0;
}

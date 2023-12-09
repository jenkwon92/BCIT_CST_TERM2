section .data
    welcome_msg db "Welcome to calculy", 0xA, 0xD      ; Welcome message with newline and carriage return
    len_welcome equ $- welcome_msg                    ; Length of welcome_msg

    msg1 db "Enter a digit: "                         ; Prompt for the first digit
    len1 equ $- msg1                                  ; Length of msg1

    msg2 db "Please enter a second digit: "           ; Prompt for the second digit
    len2 equ $- msg2                                  ; Length of msg2

    msg3 db "The sum is: "                            ; Message for the sum result
    len3 equ $- msg3                                  ; Length of msg3

    msg4 db "The difference is: "                     ; Message for the difference result
    len4 equ $- msg4                                  ; Length of msg4

section .bss
    num1 resd 1 ;Allocate memory to store the first digit. 32bit(4bytes)
    num2 resd 1 ;Allocate memory to store the second digit. 32bit(4bytes)
    res  resd 1 ;Allocate memory to store the result digit. 32bit(4bytes)

section .text
    global _start

_start:
    ; Write welcome_msg to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, welcome_msg     ; Pointer to the welcome message
    mov edx, len_welcome     ; Length of the welcome message
    int 0x80                 ; Call kernel

    ; Write msg1 to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, msg1            ; Pointer to the first prompt message
    mov edx, len1            ; Length of the first prompt message
    int 0x80                 ; Call kernel

    ; Read input to num1
    mov eax, 3               ; System call for sys_read
    mov ebx, 0               ; File descriptor for STDIN
    mov ecx, num1            ; Pointer to store the first digit
    mov edx, 2               ; Number of bytes to read
    int 0x80                 ; Call kernel

    ; Write msg2 to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, msg2            ; Pointer to the second prompt message
    mov edx, len2            ; Length of the second prompt message
    int 0x80                 ; Call kernel

    ; Read input to num2
    mov eax, 3               ; System call for sys_read
    mov ebx, 0               ; File descriptor for STDIN
    mov ecx, num2            ; Pointer to store the second digit
    mov edx, 2               ; Number of bytes to read
    int 0x80                 ; Call kernel

    ; Convert ASCII to integers
    movzx eax, byte [num1]   ; Load the ASCII value of the first digit
    sub eax, '0'             ; Convert ASCII to integer
    movzx ebx, byte [num2]   ; Load the ASCII value of the second digit
    sub ebx, '0'             ; Convert ASCII to integer

    ; Calculate sum
    add eax, ebx             ; Add the two integers
    add eax, '0'             ; Convert the sum to ASCII by adding the ASCII value of '0'

    ; Store result in res
    mov byte [res], al       ; Store the least significant byte of eax in res

    ; Write msg3 to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, msg3            ; Pointer to the sum message
    mov edx, len3            ; Length of the sum message
    int 0x80                 ; Call kernel

    ; Write result to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, res             ; Pointer to the result
    mov edx, 1               ; Number of bytes to write
    int 0x80                 ; Call kernel

    ; Add newline to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, newline         ; Pointer to the newline character
    mov edx, 1               ; Number of bytes to write
    int 0x80                 ; Call kernel

    ; Calculate difference (handle case where num1 < num2)
    movzx eax, byte [num1]   ; Load the ASCII value of the first digit
    sub eax, '0'             ; Convert ASCII to integer
    movzx ebx, byte [num2]   ; Load the ASCII value of the second digit
    sub ebx, '0'             ; Convert ASCII to integer

    cmp eax, ebx             ; Compare the two integers
    jl swap_numbers          ; Jump to swap_numbers if num1 < num2

    sub eax, ebx             ; Subtract the two integers
    add eax, '0'             ; Convert the difference to ASCII by adding the ASCII value of '0'
    jmp print_difference     ; Jump to print_difference

swap_numbers:
    sub ebx, eax             ; Subtract the two integers (num2 - num1)
    mov eax, ebx             ; Move the result to eax
    add eax, '0'             ; Convert the difference to ASCII by adding the ASCII value of '0'

print_difference:
    ; Store result in res
    mov byte [res], al       ; Store the least significant byte of eax in res

    ; Write msg4 to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, msg4            ; Pointer to the difference message
    mov edx, len4            ; Length of the difference message
    int 0x80                 ; Call kernel

    ; Write result to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, res             ; Pointer to the result
    mov edx, 1               ; Number of bytes to write
    int 0x80                 ; Call kernel

    ; Add newline to STDOUT
    mov eax, 4               ; System call for sys_write
    mov ebx, 1               ; File descriptor for STDOUT
    mov ecx, newline         ; Pointer to the newline character
    mov edx, 1               ; Number of bytes to write
    int 0x80                 ; Call kernel

    ; Exit program
    mov eax, 1               ; System call for sys_exit
    xor ebx, ebx             ; Exit code 0
    int 0x80                 ; Call kernel

section .data
    newline db 0xA

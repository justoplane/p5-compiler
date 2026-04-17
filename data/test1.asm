# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main
main:
 li $v0, 11
 li $a0, 10
 syscall
 li $v0, 10
 syscall

# All memory structures are placed after the
# .data assembler directive
.data

strLabel0: .asciiz "Hello world"

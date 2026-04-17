# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main
main:
  li $v0, 4
  la $a0, strLabel0
  syscall
 li $v0, 11
 li $a0, 10
 syscall
  li $t0, 3
  sw $t0, -
# All memory structures are placed after the
# .data assembler directive
.data

strLabel0: .asciiz "This program prints the number 7"

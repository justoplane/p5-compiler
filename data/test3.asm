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
  sw $t0, -0($sp)
  li $t0, 4
  sw $t0, -4($sp)
  lw $t0, -0($sp)
  lw $t1, -4($sp)
  add $t2, $t0, $t1
  li $v0, 1
  move $a0, $t2
  syscall
 li $v0, 11
 li $a0, 10
 syscall
 li $v0, 10
 syscall

# All memory structures are placed after the
# .data assembler directive
.data

strLabel0: .asciiz "This program prints the number 7"

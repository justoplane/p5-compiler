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
  li $t0, 7
  li $v0, 1
  move $a0, $t0
  syscall
 li $v0, 11
 li $a0, 10
 syscall
  li $t0, 3
  li $t1, 4
  add $t2, $t0$t1
  li $v0, 1
  move $a0, $t2
  syscall
 li $v0, 11
 li $a0, 10
 syscall
  li $t0, 14
  li $t1, 2
  div $t0, $t1
  mflo $t2
  li $v0, 1
  move $a0, $t2
  syscall
 li $v0, 11
 li $a0, 10
 syscall
  li $t0, 7
  li $t1, 1
  mul $t2, $t0$t1
  li $v0, 1
  move $a0, $t2
  syscall
 li $v0, 11
 li $a0, 10
 syscall
  li $t0, 7
  li $t1, 2
  mul $t2, $t0$t1
  li $t0, 2
  div $t2, $t0
  mflo $t1
  li $v0, 1
  move $a0, $t1
  syscall
 li $v0, 11
 li $a0, 10
 syscall
 li $v0, 10
 syscall

# All memory structures are placed after the
# .data assembler directive
.data

strLabel0: .asciiz "This program prints 7 7 7 7 7 (separated by newlines)"

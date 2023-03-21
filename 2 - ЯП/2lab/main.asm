%include 'dict.inc'
%include 'colon.inc'
%include 'lib.inc'

section .rodata
%include 'words.inc'
found_error: 
    db 'Вхождение не найдено!', 0
length_error: 
    db 'Строка слишком длинная!', 0
   
section .bss
buffer: 
		resb 256

section .text
global _start
_start:
	mov rdi, buffer
  mov rsi, 256
  call read_word
  cmp rax, 0
  je .len_error
  mov rdi, buffer
  mov rsi, current
  call find_word
  cmp rax, 0
  je .not_found_error
	.found_success:
  	add rax, 8
  	mov rdi, rax
		push rdi
    call string_length
    pop rdi
    inc rdi
    add rdi, rax
    mov rsi, 1
    call print_string
    call exit
	.len_error:
    mov rdi, length_error
    jmp .end_error
	.not_found_error:
    mov rdi, found_error
    jmp .end_error
	.end_error:
	  mov rsi, 2
    call print_string
    call exit

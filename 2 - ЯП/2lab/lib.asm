global exit
global string_length
global print_string
global string_equals
global read_char
global read_word

section .text

; Принимает код возврата и завершает текущий процесс
exit: 
    mov rax, 60
    syscall
    
; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length:
    xor rax, rax
    .count:
    	cmp byte [rdi+rax], 0
    	je .end
    	inc rax
    	jmp .count
    .end:
    	ret

; Принимает указатель на нуль-терминированную строку и дескриптор. Выводит её в поток вывода/ошибок
print_string:
		push rsi
    push rdi
    push rsi
    call string_length
    pop rsi
    pop rdi
    mov rdx, rax
    mov rax, 1
    mov rsi, rdi
    pop rdi
    syscall 
    .end:
    	ret


; Принимает два указателя на нуль-терминированные строки, возвращает 1 если они равны, 0 иначе
string_equals:
    xor r10, r10
    xor r8, r8
  .loop:
    	mov r10b, [rdi+r8]
    	mov r11b, [rsi+r8]
    	cmp r10b, r11b
    	jne .not_equals
    	cmp r10b, 0
    	je .equals
    	inc r8
    	jmp .loop
   .not_equals:
    	xor rax, rax
    	ret
   .equals:
    	mov rax, 1 
    	ret
   

; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char:
    push 0
    mov rax, 0
    mov rdi, 0
    mov rdx, 1
    mov rsi, rsp
    syscall
    pop rax
    .end:
    	ret

; Принимает: адрес начала буфера, размер буфера
; Читает в буфер слово из stdin, пропуская пробельные символы в начале, .
; Пробельные символы это пробел 0x20, табуляция 0x9 и перевод строки 0xA.
; Останавливается и возвращает 0 если слово слишком большое для буфера
; При успехе возвращает адрес буфера в rax, длину слова в rdx.
; При неудаче возвращает 0 в rax
; Эта функция должна дописывать к слову нуль-терминатор

read_word:
    push rdi
    push rdi
    push rsi
    
    .begin_space_skip:
    	call read_char
    	cmp rax, 0x20
    	je .begin_space_skip
    	cmp rax, 0x9
    	je .begin_space_skip
    	cmp rax, 0xA
    	je .begin_space_skip
    	xor r9,r9
    	pop rsi
    	pop rdi
    
    .read_char_cycle:
    	cmp rax, 0
    	je .success
    	cmp rax, 0x20
    	je .success
    	cmp rax, 0x9
    	je .success
    	cmp rax, 0xA
    	je .success
    	cmp rsi, r9
    	je .fail
    	mov [rdi], rax
    	inc rdi
    	inc r9
    	push rdi
    	push rsi
    	call read_char
    	pop rsi
    	pop rdi
    	jmp .read_char_cycle
    
    .success:
    	pop rax
    	mov rdx, r9
    	ret
    
    .fail:
    	pop rax
    	xor rax, rax
    	ret

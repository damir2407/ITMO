global find_word
%include "lib.inc"

section .text
find_word:
	.main_cycle:
    	push rdi            
    	push rsi            
    	add rsi, 8          
    	call string_equals  
    	pop rsi             
    	pop rdi     
    	cmp rax, 1
    	je .found
    	mov rsi, [rsi]     
    	cmp rsi, 0
    	jnz .main_cycle                  
	.found:
    	mov rax, rsi        
    	ret                  

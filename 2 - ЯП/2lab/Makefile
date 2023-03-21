main: main.o lib.o dict.o
	ld -o main main.o lib.o dict.o
	
main.o: main.asm lib.o dict.o colon.inc words.inc 
	nasm -f elf64 -o main.o main.asm

dict.o: dict.inc lib.o
	nasm -f elf64 -o dict.o dict.asm
	
lib.o: lib.asm
	nasm -f elf64 -o lib.o lib.asm

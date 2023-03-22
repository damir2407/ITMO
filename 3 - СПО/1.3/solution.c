#include <stdbool.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>


int main(int argc, char *argv[]){
	const char *library = argv[1]; 
	const char *name_of_function = argv[2]; 
	int third_arg = atoi(argv[3]);
	void *hdl = dlopen(library, RTLD_LAZY);
	int (*func)(int); 
	*(void **) (&func) = dlsym(hdl, name_of_function);
	int answer = func(third_arg); 
	printf("%d\n", answer);
	return 0;
}


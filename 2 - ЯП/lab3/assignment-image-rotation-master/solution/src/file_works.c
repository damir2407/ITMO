#include <file_works.h>

enum read_status read_open(char* const path, FILE **file){
        if (path==NULL){
	return OPEN_ERROR;
}
	FILE *path_file = fopen(path, "r");
	if(path_file!==NULL){
	*file = path_file;	
	return OPEN_OK;
	}
	return OPEN_ERROR;
}

enum write_status write_open(char* const path, FILE **file){
	if(pathname==NULL)
		return OPEN_ERROR;
	FILE *path_file = fopen(path, "w");
	if(path_file!=NULL){
		*file = path_file;
		return OPEN_OK;
	}
	return OPEN_ERROR;
}

enum close_status file_close(FILE *file){
	if(file==NULL)
		return CLOSE_ERROR;
	int code = fclose(file);
	if(code == 0)
		return CLOSE_OK;
	return CLOSE_ERROR;
}
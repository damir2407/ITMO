#include <sys/ioctl.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <malloc.h>
#include <unistd.h>
#include <stdbool.h>

#define WR_SIGNAL _IOW('a','a',struct my_signal_struct*)
#define WR_PAGE _IOW('a','b',struct my_page_struct*)
#define RD_SIGNAL _IOR('b','b',struct my_signal_struct*)
#define RD_PAGE _IOR('b','a',struct my_page_struct*)


struct my_signal_struct {
    bool valid;
    int nr_threads;
    int group_exit_code;
    int notify_count;
    int group_stop_count;
    unsigned int flags;
    pid_t pid;
};

struct my_page_struct {
    bool valid;
    unsigned long flags;
    long virtual_address;
    unsigned long mapping; 
    pid_t pid;
};


int main(int argc, char *argv[]) {
    if (argc < 3){
        printf("Вам необходимо ввести struct_id (1 - signal, 2 - page) и PID!\n" );
        return 0;
    }

    if (argc > 3){
        printf("Некорректное количество аргументов!\n" );
        return 0;
    }
    
    int struct_id = atoi(argv[1]);
    pid_t console_pid = atoi(argv[2]);
    int dev;

    if (struct_id == 1){
    	struct my_signal_struct mss;
     	mss.pid = console_pid;
      
        printf("\nОткрытие драйвера...");
    	dev = open("/dev/my_new_dev", O_RDWR);
       	if (dev == -1){
		printf("\nОшибка при открытии!\n");	
		return -1;
	}

        printf("\nОткрытие прошло успешно!\n");
    	printf("Запись данных в драйвер...\n");
    	ioctl(dev, WR_SIGNAL, (struct my_signal_struct*) &mss); 

        printf("Чтение данных из драйвера...\n");
    	ioctl(dev, RD_SIGNAL, (struct my_signal_struct*) &mss); 
        
        if (mss.valid == true){
    
    		printf("\nSignal struct, PID %d: \n", mss.pid);
    		printf("\tnr_threads = %d\n", mss.nr_threads);
    		printf("\tgroup_exit_code = %d\n", mss.group_exit_code);
    		printf("\tnotify_count = %d\n", mss.notify_count);
    		printf("\tgroup_stop_count = %d\n", mss.group_stop_count);
    		printf("\tflags = %u\n", mss.flags);
    	}else printf("\nОшибка! Заданный PID не существует!");
    
     printf("\nЗакрытие драйвера...\n");
     close(dev);

    } 
    
    else if (struct_id == 2){
    	struct my_page_struct mps; 
        mps.pid = console_pid;

        printf("\nОткрытие драйвера...");
   	dev = open("/dev/my_new_dev", O_RDWR);
	if (dev == -1){
		printf("\nОшибка при открытии!\n");	
		return -1;
	}        
         
        printf("\nОткрытие прошло успешно!\n");
        printf("Запись данных в драйвер...\n");
	ioctl(dev, WR_PAGE, (struct my_page_struct*) &mps);

        printf("Чтение данных из драйвера...\n");
	ioctl(dev, RD_PAGE, (struct my_page_struct*) &mps);

	if (mps.valid == true){
    		printf("\nPage struct, PID %d: \n", mps.pid);
                printf("\tflags = %ld\n", mps.flags);
		printf("\tvirtual address = %lx\n", mps.virtual_address);
		printf("\tphysical address = %lx\n", mps.mapping);
  
    	}else printf("\nОшибка! Невозможно получить структуру page!");



    	printf("\nЗакрытие драйвера...\n");
    	close(dev);
  
    } else { 
      	printf("\nОшибка! Введенный struct_id не существует!\n");
	return 0;
      }
}

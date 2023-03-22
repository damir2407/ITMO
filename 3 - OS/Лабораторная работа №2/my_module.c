#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

#include <linux/fs.h>
#include <linux/cdev.h>
#include <linux/uaccess.h>
#include <linux/device.h>
#include <linux/vmalloc.h>

#include <linux/ioctl.h>
#include <linux/pid.h>
#include <linux/sched.h>
#include <linux/sched/signal.h>

#include <linux/seq_file.h>
#include <asm/pgtable.h>
#include <asm/page.h>
#include <linux/pagemap.h>
#include <linux/mm_types.h>
#include "linux/mm.h"

#define WR_SIGNAL _IOW('a','a',struct my_signal_struct*)
#define WR_PAGE _IOW('a','b',struct my_page_struct*)
#define RD_SIGNAL _IOR('b','b',struct my_signal_struct*)
#define RD_PAGE _IOR('b','a',struct my_page_struct*)

MODULE_LICENSE("Dual BSD/GPL");
MODULE_AUTHOR("Балтабаев Дамир P33121");
MODULE_DESCRIPTION("ОСИ | Лабораторная работа №2 | ioctl: signal_struct, page");
MODULE_VERSION("1.0");

dev_t first = 0; //Global variable for the first device number
static struct cdev c_dev; //Global variable for the character device structure
static struct class *cl; //Global variable for the device class


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


struct task_struct* ts;
struct my_signal_struct mss;
struct my_page_struct mps;


static ssize_t  my_ictl_read(struct file *file, char __user *buf, size_t len,loff_t * off);
static ssize_t  my_ictl_write(struct file *file, const char *buf, size_t len, loff_t * off);
static int      my_ictl_open(struct inode *inode, struct file *file);
static long     my_ictl_ioctl(struct file *file, unsigned int cmd, unsigned long arg);
static int      my_ictl_release(struct inode *inode, struct file *file);
static int      __init ofcd_init(void);
static void     __exit ofcd_exit(void);

void signal_struct_method(void);
void page_method(void);


static struct file_operations fops =
{
        .owner          = THIS_MODULE,
        .read           = my_ictl_read,
        .write          = my_ictl_write,
        .open           = my_ictl_open,
        .unlocked_ioctl = my_ictl_ioctl,
        .release        = my_ictl_release,
};


static int my_ictl_open(struct inode *inode, struct file *file)
{
        printk(KERN_INFO "Была вызвана функция открытия!\n");
        return 0;
}


static int my_ictl_release(struct inode *inode, struct file *file)
{
        printk(KERN_INFO "Была вызвана функция закрытия!\n");
        return 0;
}


static ssize_t my_ictl_read(struct file *file, char __user *buf, size_t len, loff_t *off)
{
        printk(KERN_INFO "Была вызвана функция чтения!\n");
        return 0;
}


static ssize_t my_ictl_write(struct file *file, const char __user *buf, size_t len, loff_t *off)
{
        printk(KERN_INFO "Была вызвана функция записи!\n");
        return 0;
}



static long my_ictl_ioctl(struct file *file, unsigned int cmd, unsigned long arg)
{
         switch(cmd) {
                case WR_SIGNAL:
                        if( copy_from_user(&mss ,(struct my_signal_struct*) arg, sizeof(mss)))
                        {
                        	printk(KERN_ERR "Ошибка копирования данных от пользователя!\n");
                        }     
                        signal_struct_method();
                        break;
		case WR_PAGE:
			if( copy_from_user(&mps ,(struct my_page_struct*) arg, sizeof(mps)))
                        {
                                printk(KERN_ERR "Ошибка копирования данных от пользователя!\n");
                        }
                        page_method();
                        break;
 		case RD_SIGNAL:
			if( copy_to_user((struct my_signal_struct*) arg, &mss , sizeof(mss)))
                        {
                                printk(KERN_ERR "Ошибка копирования данных пользователю!\n");
                        }
                        break;
		case RD_PAGE:
			if( copy_to_user((struct my_page_struct*) arg, &mps , sizeof(mps)))
                        {
                                printk(KERN_ERR "Ошибка копирования данных пользователю!\n");
                        }
                        break;
                default:
                        printk(KERN_INFO "Заданная команда нереализована!");
                        break;
        }
        return 0;
}
 

static int __init ofcd_init(void) /* Constructor */
{	
	//регистрация MAJOR and MINOR
        //int alloc_chrdev_region(dev_t *first, unsigned int firstminor, unsigned int cnt, char *name);
        if((alloc_chrdev_region(&first, 0, 1, "my_new_dev")) <0)
	{
		printk(KERN_ERR "Не удалось зарегистрировать диапазон символьных номеров устройств\n");
                return -1;
        }
 

     	//создание класса устройства
        if((cl = class_create(THIS_MODULE,"my_new_class")) == NULL)
	{
            printk(KERN_ERR "Невозможно создать структуру класса\n");
            unregister_chrdev_region(first,1);
            return -1;
        }

     	//заполнение класса информацией об устройстве (MAJOR,MINOR)
        if((device_create(cl,NULL,first,NULL,"my_new_dev")) == NULL)
	{
            printk(KERN_ERR "Невозможно создать девайс\n");
            class_destroy(cl);
	    unregister_chrdev_region(first,1);
            return -1;
        }


        //заполняем структуру устройства необходимыми функциями
        cdev_init(&c_dev,&fops);
 
        //добавляем девайс в систему 
        if((cdev_add(&c_dev,first,1)) == -1)
	{
            printk(KERN_ERR "Невозможно добавить девайс в систему\n");
            device_destroy(cl,first);
	    class_destroy(cl);
            unregister_chrdev_region(first, 1);
    	    return -1;
        }
        printk(KERN_INFO "Установка драйвера произошла успешно!\n");
        return 0;
}



void signal_struct_method(){
    ts = get_pid_task(find_get_pid(mss.pid), PIDTYPE_PID);
    if (ts == NULL){
    	mss.valid = false;
  	return 0;
    }else{
    struct signal_struct *signalStruct = ts->signal;
    mss.valid = true;
    mss.nr_threads = signalStruct->nr_threads;
    mss.group_exit_code = signalStruct->group_exit_code;
    mss.notify_count = signalStruct->notify_count;
    mss.group_stop_count = signalStruct->group_stop_count;
    mss.flags = signalStruct->flags;
    }
}



static struct page *get_current_page(struct mm_struct* mm, long virtual_address) {
    pgd_t *pgd;	
    p4d_t *p4d;
    pud_t *pud;
    pmd_t *pmd;
    pte_t *pte;
    struct page *page = NULL;
    pgd = pgd_offset(mm, virtual_address);
    if (!pgd_present(*pgd)) {
        return NULL;
    }
    p4d = p4d_offset(pgd, virtual_address);
    if (!p4d_present(*p4d)) {
    	return NULL;
    }
    pud = pud_offset(p4d, virtual_address);
    if (!pud_present(*pud)) {
        return NULL;
    }
    pmd = pmd_offset(pud, virtual_address);
    if (!pmd_present(*pmd)) {
        return NULL;
    }
    pte = pte_offset_kernel(pmd, virtual_address);
    if (!pte_present(*pte)) {
        return NULL;
    }
    page = pte_page(*pte);
    return page;
}




void page_method(){
    ts = get_pid_task(find_get_pid(mps.pid), PIDTYPE_PID);


    if (ts == NULL){
    	mps.valid = false;
  	return 0;
    }else {
    struct page *page_struct;
    struct mm_struct *mm = ts->mm; 
    
    if (mm == NULL) 
    {
	mps.valid=false;
	return 0;
    }else {
     
    mps.valid=true;
    struct vm_area_struct *vas = mm->mmap;
    long virtual_address;
    for (virtual_address = vas->vm_start; virtual_address <= vas->vm_end;virtual_address += PAGE_SIZE){
    	page_struct = get_current_page(mm, virtual_address);
    	if (page_struct != NULL) {
		mps.flags = page_struct->flags;
		mps.virtual_address = virtual_address;
		mps.mapping= page_struct->mapping;
		break;
	}
    }
    
    if (page_struct == NULL) 
    {   
	mps.valid=false;
	printk(KERN_ERR "Ошибка при взятии страницы!\n");
	return 0;
    }
    }
    }

}




static void __exit ofcd_exit(void) /*Desctructor */
{
        cdev_del(&c_dev);
	device_destroy(cl, first);
	class_destroy(cl);
        unregister_chrdev_region(first, 1);
	printk(KERN_INFO "Девайс уничтожен!");
}
 
module_init(ofcd_init);
module_exit(ofcd_exit);

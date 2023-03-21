#include "image.h"
#include <malloc.h>
struct image make_img(uint64_t width, uint64_t height){
return ((struct image)
{.width=width, .height=height, .data=malloc(sizeof(struct pixel)*width*height))
});

void destroy_img(struct image img){
free(img.data);
free(img);
}

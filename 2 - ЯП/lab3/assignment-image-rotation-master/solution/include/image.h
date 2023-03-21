#include <stdint.h>
#include <stdlib.h>
#include <stdbool.h>

struct pixel { uint8_t b, g, r; };

struct image {
  uint64_t width, height;
  struct pixel* data;
};

struct image make_img(uint64_t width, uint64_t height);
void destroy_img(struct image img);


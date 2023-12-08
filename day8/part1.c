#include "stdio.h"
#include "stdlib.h"
#include "string.h"
struct Object{
    char nav[50];
    
};
typedef struct Object Object;

Object * parse(){
    Object * b;
    b = malloc(sizeof * b);
    
    FILE* ptr;
    char ch;
    ptr = fopen("input", "r");
    if (NULL == ptr) {
        printf("file can't be opened \n");
    }

    char * line = NULL;
    size_t len = 0;
    size_t read;
    
    while ((read = getline(&line, &len, ptr)) != -1) {
        printf("Retrieved line of length %zu:\n", read);
        printf("%s", line);
    }
    return b;
}

void main(){
    printf("hey\n");

    Object * obj = parse();
    printf("obj %s\n", obj->nav);
}
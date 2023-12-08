#include "stdio.h"
#include "stdlib.h"
#include "string.h"

struct NetCell{
    char from[3];
    char right[3];
    char left[3];
};

typedef struct NetCell NetCell;

struct Object{
    char nav[10000];
    int count_nav;
    NetCell network[10000];
    int net_capacity;
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
    read = getline(&line, &len, ptr);
    b->count_nav = strlen(line);
    strcpy(b->nav, line);

    read = getline(&line, &len, ptr);

    b->net_capacity = 0;
    while ((read = getline(&line, &len, ptr)) != -1) {
        //printf("%s\n", line);
        char from[3];
        char right[3];
        char left[3];
        sscanf(line, "%s = (%s, %s)\n", from, left, right);
        strcpy(b->network[b->net_capacity].from, from);
        strcpy(b->network[b->net_capacity].left, left);
        strcpy(b->network[b->net_capacity].right, right);
        (b->net_capacity)++;
    }
    fclose(ptr);
    return b;
}

void main(){
    printf("hey\n");

    Object * obj = parse();
    printf("llll\n");
    //printf("%s = (%s, %s)\n", obj->network[0].from, obj->network[0].right, obj->network[0].left);
}
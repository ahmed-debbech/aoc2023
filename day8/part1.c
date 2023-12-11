#include "stdio.h"
#include "stdlib.h"
#include "string.h"

struct NetCell{
    char * from;
    char  *  right;
    char * left;
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
    b->count_nav = strlen(line)-2;
    printf("count nqv: %ld\n", strlen(line)-2);
    strcpy(b->nav, line);

    read = getline(&line, &len, ptr);

    b->net_capacity = 0;

    while ((read = getline(&line, &len, ptr)) != -1) {
        //printf("%s\n", line);
        char * from = malloc(sizeof(char)* 3);
        char * right = malloc(sizeof(char)* 3);
        char * left = malloc(sizeof(char)* 3);

        from[0] = line[0];
        from[1] = line[1];
        from[2] = line[2];

        left[0] = line[7];
        left[1] = line[8];
        left[2] = line[9];

        right[0] = line[12];
        right[1] = line[13];
        right[2] = line[14];

        b->network[b->net_capacity].from = from;
        b->network[b->net_capacity].left = left;
        b->network[b->net_capacity].right = right;
        (b->net_capacity)++;
    }
    fclose(ptr);
    return b;
}

void main(){
    printf("hey\n");

    Object * obj = parse();
    //printf("%s = (%s, %s)\n", obj->network[1].from, obj->network[1].right, obj->network[1].left);
    int j = 0;
    int k = 0;
    for(k =0; k<= obj->net_capacity-1; k++){
        if(strcmp(obj->network[k].from, "AAA") == 0) {
            j = k;
            break;
        }
    }
    char * next = obj->network[j].from;
    int i = 0;
    int g = 0;
    do{
        printf("***************iter ***************************************\n");
        printf("current: %s\n", next);
        g++;
        if(obj->nav[i] == 'R'){
            next = obj->network[j].right;
        }else{
            next = obj->network[j].left;
        }
        printf("after choosing %d %c, next is %s\n", i, obj->nav[j], next);
        int k = 0;
        for(k =0; k<= obj->net_capacity-1; k++){
            if(strcmp(obj->network[k].from, next) == 0) {
                j = k;
                break;
            }
        }
        if(i >= obj->count_nav) {
            i=0;
        }else{
            i++;
        }
        printf("\n");
    }while(strcmp(next , "ZZZ") != 0);
    printf("THE RESULT: %d\n", g);
}
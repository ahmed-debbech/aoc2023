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
int checkEnd(char * nexts[], int sizeA){
    int count = 0;
    for(int d =0; d<=sizeA-1; d++){
        if(nexts[d][2] == 'Z'){
            count++;
        }
    }
    if(count == sizeA){
        return 1;
    }
    return 0;
}
char * navigate(int ind, char * next, Object * obj){
    printf("current: %s\n", next);
    int j = 0;
    int k = 0;
    for(k =0; k<= obj->net_capacity-1; k++){
        if(strcmp(obj->network[k].from, next) == 0) {
            j = k;
            break;
        }
    }

    if(obj->nav[ind] == 'R'){
        next = obj->network[j].right;
    }else{
        next = obj->network[j].left;
    }

    return next;
}

void main(){
    printf("hey\n");

    Object * obj = parse();
    
    //get all ending A nodes
    NetCell ** allA = NULL;
    int size_a = 0;
    int i=0;

    for(i=0; i<=obj->net_capacity-1; i++){
        if(obj->network[i].from[2] == 'A'){
            allA = realloc(allA, sizeof(NetCell*) * (size_a + 1));
            allA[size_a] = malloc(sizeof(NetCell));
            *(allA[size_a]) = obj->network[i];
            size_a++;
        }
    }

    //show all A
    for(i=0; i<=size_a-1; i++){
        printf("node: %s\n", allA[i]->from);
    }

    i =0;
    char * nexts[size_a];

    //navigate to allll
    for(int d =0; d<=size_a-1; d++){
        nexts[d] = allA[d]->from;
        printf("this next : %s\n", nexts[d]);
    }
    printf("********** START **********\n");
    int g=0;
    do{
        printf("************************* dir: %c ***\n", obj->nav[i]);
        int f = 0;
        for(f=0; f<=size_a-1; f++){
            printf("== navigation number %d ==\n",f);
            nexts[f] = navigate(i, nexts[f], obj);
            printf("next: %s\n", nexts[f]);
        }
        printf("\n");
        if(i >= obj->count_nav) {
            i=0;
        }else{
            i++;
        }
        g++;
    }while(checkEnd(nexts, size_a) == 0);
        printf("THE RESULT: %d\n", g);

}
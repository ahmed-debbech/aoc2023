#include "stdio.h"

void f(char *line)
{
  // Continue if the value pointed at by `line` is not NUL
  while (*line) {
    // Dereference `line` and assign a new value '0'
    // then advance the pointer
    *(line++) = '0';
  }
}


int main()
{
	char line[] = "gdgegryt";
	f(line);
	printf("%s\n", line);
	return 0;
}

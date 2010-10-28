// (C) 2009 Ralf Laemmel

#include <stdio.h>

int factorial(int n) {
  int r = 1;
start:
	if (n == 0) goto end;
    r = r * n;
    n = n - 1;
    goto start;
end:
  return(r);
}

int main(int argc, char *argv[])
{
  printf("%i\n", factorial(5));
  return 0;
}

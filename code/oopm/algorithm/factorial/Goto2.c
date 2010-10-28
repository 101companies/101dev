// (C) 2009 Ralf Laemmel

#include <stdio.h>

int factorial(int i) {
  int r = 1;
  start:
  if (i != 0) {
    r = i * r;
    i = i - 1;
    goto start;
  }
  return(r);
}

int main(int argc, char *argv[])
{
  printf("%i\n", factorial(5));
  return 0;
}

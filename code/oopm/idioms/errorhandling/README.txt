// (C) 2009-10 Ralf Laemmel

The present package illustrates different techniques for error handling.
The running example is a piece of functionality to read two ints and print their sum.
There are these variations:
- NoErrorHandling: input errors lead to top-level exceptions exiting the program.
- ExceptionHandling: input errors are caught by the program and reported nicely.
- FlagBasedErrorHandling: input errors are communicated by a flag to the application.
- NullBasedErrorHandling: input errors are communicated by a special value (null).


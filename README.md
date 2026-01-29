# Assignment-1-DECIDE


## Build && Run
This project uses Java and [JUnit v.1.11.4](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone/1.11.4) for testing.
1. Make sure you have Java installed.
2. Open terminal and navigate to the project directory.
3. If you have Make installed
```bash
# compiling the program:
make build
# running the program:
make run
# running the tests:
make test
# cleaning the build files:
make clean
```
4. If you don't have Make installed
```bash
# compiling the program:
mkdir -p out
javac -d out src/*.java

# running the program:
java -cp out Decide

# running the tests:
javac -cp lib/junit-platform-console-standalone.jar:out -d out test/*.java
java -jar lib/junit-platform-console-standalone.jar execute --class-path out --scan-class-path --include-engine junit-jupiter --disable-banner
```

Compile and run Java, run in terminal
`javac filename.java && java filename`

## Goal
The primary objective of this project was to implement a hypothetical anti-ballistic missile system. By evaluating fifteen Launch Interceptor Conditions (LICs), the DECIDE() function acts as a boolean signal that outputs "YES" or "NO" to determine if an interceptor should be launched.
 
The main goal with the assignment is to learn of to apply software techniques, and learn how to work with professional collaboration.


## Roles
**Gabriel:** 
-Initiated code structure, and git
-Implimented PUM FUV 
-CUV structure

**Lassya:** 
-Implemented LICs 2,4,6,10,12 
-Developed helper gunction for geomertric calculation.

**Olivia:** 
-Implimeted LICs 0,1,3,7,8
-Developed helper functions for geometric calculations
-Uppdated final README.

**David:** 
-Implemeted LICs 5,9,11,12
-Created contributing.md documentation
-Assisted in README

**Sophia:** 
-Created and managed GitHub issues
-Declared global varibles
-Contributed to inital README documation.


### Way of working (Remarkable aspect)

Beyond implementing the functional DECIDE requirements, our team is proud of the remarkable level of collaboration that distinguishes our work.

Despite our diverse technical backgrounds, we proactively identified **individual strengths** to maintain a transparent workflow and **professional** structure, utilizing GitHub Projects to manage tickets and link issues to specific branches for operations.
Every member demonstrated great reliability by respecting all deadlines for a culture of mutual respect and feedback, which successfully enhanced our "Way of Working". We take particular pride in our commitment to atomic traceability; our strict adherence to a "feat:" and "test:" prefix convention ensured that every new feature was professionally integrated and verified through contract unit tests.


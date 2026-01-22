JAVAC = javac
JAVA  = java
OUT   = out
LIB   = lib
JUNIT = $(LIB)/junit-platform-console-standalone.jar

SRC   = src/Decide.java
TEST  = test/DecideTest.java

.PHONY: all run test clean

all: build

build:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)

run: build
	$(JAVA) -cp $(OUT) Decide

test: build
	# compile tests with JUnit on the classpath
	$(JAVAC) -cp $(JUNIT):$(OUT) -d $(OUT) $(TEST)
	# run tests with JUnit
	$(JAVA) -jar $(JUNIT) execute --class-path $(OUT) --scan-class-path --include-engine junit-jupiter --disable-banner

clean:
	rm -rf $(OUT)
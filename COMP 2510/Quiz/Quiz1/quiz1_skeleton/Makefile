CC = gcc
CFLAGS = -Wall

# Executable name
EXECUTABLE = q1

# Change input file name if your program requires it
# and output files
OUTPUT_FILE = output.txt
REFERENCE_OUTPUT_FILE = reference_output.txt

all: $(EXECUTABLE)

$(EXECUTABLE): q1.c
	$(CC) $(CFLAGS) -o $(EXECUTABLE) q1.c

run: $(EXECUTABLE)
	./$(EXECUTABLE) > $(OUTPUT_FILE)

test: run
	@echo "Comparing output with reference..."
	@diff $(OUTPUT_FILE) $(REFERENCE_OUTPUT_FILE) && echo "Test passed" || echo "Test failed"

clean:
	rm -f $(EXECUTABLE) $(OUTPUT_FILE)

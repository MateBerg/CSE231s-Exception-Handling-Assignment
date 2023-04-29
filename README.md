
# Exception Handling Assignment

A program that reads an ARXML file containing a list of containers, each with a unique ID, and reorders the containers alphabetically by their name sub-container `SHORT-NAME`
  
The program should write the reordered containers to a new ARXML file.
  
`ARXML` stands for Autosar xml

`Autosar` stands for AUTomotive Open System ARchitecture

**Output from linux arch terminal:**  

![image](https://user-images.githubusercontent.com/69548206/235323576-6c8405e0-6f9e-41ee-9bd1-88ef2ccc493a.png)



## Requirements:

- Passing name of input_file_name.arxml through the command line.

- If the input file is empty, your program should throw a user-defined exception 
called “EmptyAutosarFileException”.

- If the input file doesn't have an .arxml extension, user-defined exception 
called “NotValidAutosarFileException”.

- The output file shall be named as the same of the input file plus `_mod.arxml`.
  

## Repo Notation:

- **main.java**: This is the main class of the program.

- **Normal.arxml**: Normal ARXML file for testing the normal mode.

- **Empty.arxml**: Empty ARXML file for testing the empty file mode.

- **random_ext.xlsx**: Random file for testing the wrong extension mode.

- **batch_file.bat**: This is a `batch file` that runs your program with different input files for testing purposes.

- **batch_file.sh**: This is a `bash script` that does the same job, but in `Linux Kernel`.
  
## Brief explanation of the code:

- The program first checks whether an input file name is provided as a command-line argument. If not, it prints a usage message and exits.

- The program then constructs the output file name by appending `_mod` to the input file name before the file extension.

- The input file is validated to ensure it has a `.arxml` file extension.

- The input file is parsed using the DocumentBuilder class from the `javax.xml.parsers` package and normalized.

- The program checks if the input file is empty or not.

- The `<CONTAINER>` elements in the input file are extracted and stored in an ArrayList<Element>.

- The `Collections.sort()` method is called on the ArrayList<Element> to sort the `<CONTAINER>` elements by the value of their `<SHORT-NAME>` child element.

- The sorted `<CONTAINER>` elements are appended to the root element of the Document object.

- The sorted Document object is transformed to a new XML file using the Transformer class from the javax.xml.transform package.

## How to run:

To run your program with a single input file, use the following command:

`java main.java <your_file>`

where `<your_file>` is the name of the ARXML file you want to process. For example:

`java main.java Normal.arxml`

This will create a new file called “Normal_mod.arxml” with the reordered containers.

To run your program with all the test files provided, use the following command:

`batch_file.bat`

This will run your program with each of the test files and show the output on the console.
  
Or you can use in Linux Kernel:       
`./batch_file.sh`  

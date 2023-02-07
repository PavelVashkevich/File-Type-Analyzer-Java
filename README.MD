This small tool extracts information about file to determine the type of the file. User can choose different algorithms to solve this problem. Using different algorithms will change the speed of the tool.

Note: To use this application use JCommander external library to parse cli arguments. Library should be added to your cloned project to execute this tool.

#### Example of command:
``java Main --in test_files --db patterns.db --alg robin-karp``

User should specify: \
--**in** - Path to directory with files to analyze. \
--**db** - Path to the db file with patterns (see the information below).\
--**alg** - Algorithms to use. User can choose between: 'naive', 'robin-karp', 'knuth-morris-pratt'.

#### Example of output:

```doc_0.doc: MS Office Word 2003
doc_1.pptx: MS Office PowerPoint 2007+. It took 0.004 seconds
doc_2.pdf: PDF document. It took 0.008 seconds
file.pem: PEM certificate. It took 0.010 seconds
```

#### Pattern database 
Tool uses pattern from database to determine the file type. Small example of pattern's database to recognize 12 different types of extensions can be found under the"
FileTypeAnalyzer/File Type Analyzer/task/patterns.txt" path in this repo. 
User can use and complete the database to satisfy their needs. 

**Note:** Structure of the database entry is following: 
_Priority (int number. A higher value means higher priority.);File type pattern (String);File Type_
Example: _1;"%PDF-";"PDF document"_

Tool use merge sort to sort entries in db before search execution.





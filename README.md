# aDoctor
Code Smell Detector able to detect a set of 16 Android-specific design flaws.

aDoctor is a project developed using Java able to identify design problems in Android apps.

For the moment, the tool supports the detection of the following code smells:

- Debuggable Release;
- Slow Loop;
- Data Transmission Without Compression;
- Inefficient Data Format and Parser;
- Inefficient Data Structure;
- Inefficient SQL Query;
- Internal Getter and Setter;
- Leaking Thread;
- Leaking Inner Class;
- No Low Memory Resolver;
- Unclosed Closable;
- Durable Wakelock;
- Member Ignoring Method;
- Public Data;
- Rigid Alarm Manager.

aDoctor is the outcome of the research conducted by the Software Engineering Lab of the University of Salerno, Italy.

# Running aDoctor
There are two ways to run the code smell detection. Firstly, once downloaded the tool, you can rely on the Graphical User Interface. Secondly, there is also a command-line support (made available to programmatically run aDoctor). 

To run the tool via command-line, you need to invoke the following command:

java -cp aDoctor-1.0.jar it.unisa.aDoctor.process.RunAndroidSmellDetection [path-to-android-app] [path-to-where- i-want-to save-the results] [smell-ID]

where: 

- [path-to-android-app] is the path to the folder containing the Android project to analyze;
- [path-to-where- i-want-to save-the results] is the path to the file where the code smell candidates will be printed;
- [smell-ID] is the ID of the code smell to identify.

The complete list of code smell IDs is in the following:

- 100000000000000: Data Transmission Without Compression;
- 010000000000000: Debuggable Release;
- 001000000000000: Durable Wakelock;
- 000100000000000: Inefficient Data Format and Parser;
- 000010000000000: Inefficient Data Structure;
- 000001000000000: Inefficient SQL Query;
- 000000100000000: Internal Getter and Setter;
- 000000010000000: Leaking Inner Class;
- 000000001000000: Leaking Thread;
- 000000000100000: Member Ignoring Method;
- 000000000010000: No Low Memory Resolver;
- 000000000001000: Public Data;
- 000000000000100: Rigid Alarm Manager;
- 000000000000010: Slow Loop;
- 000000000000001: Unclosed Closable;

# License
The project is released under MIT license.

Icons made by Dave Gandy from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Gregor Cresnar from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Iconnice from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Freepik from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Madebyoliver from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Vectors Market from www.flaticon.com is licensed by CC 3.0 BY.

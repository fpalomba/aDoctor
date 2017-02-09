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
- Set Config Changes;
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

- 000000000000001: Data Transmission Without Compression;
- 000000000000002: Debuggable Release;
- 000000000000003: Durable Wakelock;
- 000000000000004: Inefficient Data Format and Parser;
- 000000000000005: Inefficient Data Structure;
- 000000000000006: Inefficient SQL Query;
- 000000000000007: Internal Getter and Setter;
- 000000000000008: Leaking Inner Class;
- 000000000000009: Leaking Thread;
- 000000000000010: Member Ignoring Method;
- 000000000000011: No Low Memory Resolver;
- 000000000000012: Public Data;
- 000000000000013: Rigid Alarm Manager;
- 000000000000014: Slow Loop;
- 000000000000015: Unclosed Closable;
- 000000000000016: Set Config Changes;

# License
The project is released under Apache 2.0 license.

Icons made by Dave Gandy from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Gregor Cresnar from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Iconnice from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Freepik from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Madebyoliver from www.flaticon.com is licensed by CC 3.0 BY.

Icons made by Vectors Market from www.flaticon.com is licensed by CC 3.0 BY.

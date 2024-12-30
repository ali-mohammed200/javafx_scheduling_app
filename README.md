C195 WGU PA
Mohammad Ali

Title:
Java FX Scheduling Application

Purpose:
Scheduling Application designed for an international business that handles multiple timezones and languages

Author:
Mohammad Ali
mali63@wgu.edu
12/29/2024

IDE:
IntelliJ IDEA 2023.3.2 (Community Edition)
Build #IC-233.13135.103, built on December 19, 2023
Runtime version: 17.0.9+7-b1087.9 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10.0
GC: G1 Young Generation, G1 Old Generation
Memory: 2048M
Cores: 8
Registry:
ide.experimental.ui=true
Kotlin: 233.13135.103-IJ


Java SDK Version: Java 17 Oracle OpenJDK version 17.0.9
JavaFX version compatible with JDK version: JavaFX-SDK-17.0.6
MySQL Connector driver version number: mysql-connector-j-8.0.33

JavaDocs are located in the JavaDocs folder within the root of the application

How to run the program:
- Open IntelliJ
- Open the project
- Make sure the system supports the same Java SDK version above
- Using maven, reimport/download the needed JavaFX and related project libraries
- Make sure the mysql database server is running and accepting connections
- Modify the database configurations here: src/main/java/DAO/DatabaseConnecter.java
- Click the run button on IntelliJ
- If there are no errors, a login window will appear

Custom Report ran in A3f:
The report retrieves the total number of customers based on country. This allows the business to see where
they can focus on for growth.

Example:
- Country  |  Count
-----------------
- US       |   10
- Canada   |    3
- UK       |    7

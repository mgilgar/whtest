Test done by Miguel Gil for William Hill.

Instructions:
=============
This is a maven project. To build run mvn package on command line. All tests should pass.

I have written a file called search.sh, sorry I do not have windows so I cannot test search.bat.
To run the program you can try:
./search.sh -f pom.xml .

This file contains this lines:
#!/bin/sh
java -cp ./target/classes org.mgilgar.whtest.App $1 $2 $3
If you want to run it manually, after building the project just from project directory run this line on command line:
java -cp ./target/classes org.mgilgar.whtest.App $1 $2 $3


Libraries I could have used:
============================
hamcrest to have nicer matchers.
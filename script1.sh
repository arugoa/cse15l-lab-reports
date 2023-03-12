rm -r lab7
git clone git@github.com:arugoa/lab7.git
cp script2.sh ./lab7
cd lab7
javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestListExamples
nano ListExamples.java
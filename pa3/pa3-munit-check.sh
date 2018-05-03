#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.s18.grading/master/pa3
NUMTESTS=5
PNTSPERTEST=5
let MAXPTS=$NUMTESTS*$PNTSPERTEST

if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi


cp *.java Makefile backup   # copy all files of importance into backup

curl $SRCDIR/ModelMatrixTest.java > ModelMatrixTest.java

echo ""
echo ""

echo "Press Enter To Continue with MatrixTest Results"
read verbose

javac ModelMatrixTest.java Matrix.java List.java

timeout 5 java ModelMatrixTest -v > MatrixTest-out.txt &>> MatrixTest-out.txt

cat MatrixTest-out.txt

rm -f *.class ModelListTest.java ModelMatrixTest.java garbage


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

curl $SRCDIR/ModelListTest.java > ModelListTest.java

echo "Press Enter To Continue with ListTest Results"
read verbose

javac ModelListTest.java List.java

timeout 5 java ModelListTest -v > ListTest-out.txt &>> ListTest-out.txt

cat ListTest-out.txt

echo ""
echo ""

rm -f *.class ModelListTest.java ModelMatrixTest.java garbage


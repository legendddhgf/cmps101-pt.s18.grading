#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.s18.grading/master/pa1
NUMTESTS=3
PNTSPERTEST=5
let MAXPTS=$NUMTESTS*$PNTSPERTEST


if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi

cp *.java Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
done

curl $SRCDIR/ModelListTest.java > ModelListTest.java

rm -f *.class
javac -Xlint Lex.java List.java
echo "Main-class: Lex" > Manifest
jar cvfm Lex Manifest *.class
rm Manifest
chmod +x Lex

echo ""
echo ""

lextestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/stderr"
echo "Lex tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 Lex infile$NUM.txt outfile$NUM.txt &> garbage >> garbage
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt &> diff$NUM.txt >> diff$NUM.txt
  echo "Test $NUM:"
  echo "=========="
  cat diff$NUM.txt
  echo "=========="
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then
    let lextestspassed+=1
  fi
done

echo ""
echo ""

let lextestpoints=5*lextestspassed

echo "Passed $lextestspassed / $NUMTESTS Lex tests"
echo "This gives a total of $lextestpoints / $MAXPTS points"

echo ""
echo ""
echo ""

echo "Press Enter To Continue with ListTest Results"
read verbose

javac ModelListTest.java List.java
timeout 5 java ModelListTest -v > ListTest-out.txt &>> ListTest-out.txt

cat ListTest-out.txt

rm *.class ModelListTest.java garbage

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

for NUM in $(seq 1 $NUMTESTS); do
  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
done

rm -f *.class
javac -Xlint Sparse.java Matrix.java List.java
echo "Main-class: Sparse" > Manifest
jar cvfm Sparse Manifest *.class
rm Manifest
chmod +x Sparse

echo ""
echo ""

sparsepassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/stderr"
echo "Sparse tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 Sparse infile$NUM.txt outfile$NUM.txt &> garbage >> garbage
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt > diff$NUM.txt &>> diff$NUM.txt
  echo "Test $NUM:"
  echo "=========="
  cat diff$NUM.txt
  echo "=========="
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then # increment number of tests passed counter
    let sparsepassed+=1
  fi
done

echo ""
echo ""

let sparsepoints=2*sparsepassed
echo "Passed $sparsepassed Sparse tests for a total of $sparsepoints / 10 points"

rm -f *.class ModelListTest.java ModelMatrixTest.java garbage


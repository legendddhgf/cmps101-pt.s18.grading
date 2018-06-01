#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.s18.grading/master/pa5
NUMTESTS=7
PNTSPERTEST=3
let MAXPTS=$NUMTESTS*$PNTSPERTEST
let MAXPTS=20

if [ ! -e backup ]; then
  mkdir backup
fi

cp *.c *.h Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
  rm -f outfile$NUM.txt
done


rm -f *.o FindComponents

gcc -c -Wall -std=c99 -g FindComponents.c Graph.c List.c
gcc -o FindComponents FindComponents.o Graph.o List.o


echo ""
echo ""

pathtestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/print stderr separately"
echo "FindComponents tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 FindComponents infile$NUM.txt outfile$NUM.txt &> garbage >> garbage #all stdout/stderr thrown away
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt > diff$NUM.txt &>> diff$NUM.txt
  echo "FindComponents Test $NUM:"
  echo "=========="
  cat diff$NUM.txt
  echo "=========="
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then # increment number of tests passed counter
    let pathtestspassed+=1
  fi
done

echo ""
echo ""

let pathtestspoints=$PNTSPERTEST*$pathtestspassed
if [ "$pathtestspoints" -gt "$MAXPTS" ]; then # max 10 points
  let pathtestspoints=$(expr $MAXPTS)
fi
echo "Passed $pathtestspassed FindComponents tests for a total of $pathtestspoints / $MAXPTS points"

echo ""
echo ""

rm -f *.o ModelListTest* ModelGraphTest* FindComponents garbage


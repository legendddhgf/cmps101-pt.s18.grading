#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.s18.grading/master/pa4
NUMTESTS=6
PNTSPERTEST=2
let MAXPTS=$NUMTESTS*$PNTSPERTEST
let MAXPTS=10

if [ ! -e backup ]; then
  mkdir backup
fi

cp *.c *.h Makefile backup   # copy all files of importance into backup

curl $SRCDIR/ModelListTest.c > ModelListTest.c

echo ""
echo ""

rm -f *.o FindPath

echo ""

echo "Press Enter To Continue with ListTest Results"
read verbose

gcc -c -std=c99 -Wall -g ModelListTest.c List.c
gcc -o ModelListTest ModelListTest.o List.o

timeout 5 valgrind ./ModelListTest -v

echo ""
echo ""

rm -f *.o ModelListTest* ModelGraphTest* FindPath garbage


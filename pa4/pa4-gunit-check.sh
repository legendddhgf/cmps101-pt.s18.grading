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

curl $SRCDIR/ModelGraphTest.c > ModelGraphTest.c

echo ""
echo ""

rm -f *.o FindPath

echo "Press Enter To Continue with GraphTest Results"
read verbose

echo ""
echo ""

gcc -c -std=c99 -Wall -g ModelGraphTest.c Graph.c List.c
gcc -o ModelGraphTest ModelGraphTest.o Graph.o List.o

timeout 5 valgrind --leak-check=full -v ./ModelGraphTest -v

rm -f *.o ModelListTest* ModelGraphTest* FindPath garbage


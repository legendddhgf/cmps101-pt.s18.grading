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


echo ""
echo ""

rm -f *.o FindPath

make

if [ ! -e FindPath ] || [ ! -x FindPath ]; then # exist and executable
  echo ""
  echo "Makefile probably doesn't correctly create Executable!!!"
  echo ""
else
  echo ""
  echo "Makefile probably correctly creates Executable!"
  echo ""
fi

echo ""
echo ""

make clean

if [ -e FindPath ] || [ -e *.o ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

rm -f *.o ModelListTest* ModelGraphTest* FindPath garbage


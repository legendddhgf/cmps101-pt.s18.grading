#!/usr/bin/bash

echo ""
echo ""
echo "press Enter for make tests"
read garbage

make

if [ ! -e Lex ] || [ ! -x Lex ]; then # exist and executable
   echo ""
   echo "Makefile doesn't correctly create Executable!!!"
   echo ""
else
   echo ""
   echo "Makefile probably correctly creates Executable!"
   echo ""
fi

make clean

echo ""
echo ""

if [ -e Lex ] || [ -e *.o ]; then
   echo "WARNING: Makefile didn't successfully clean all files"
fi

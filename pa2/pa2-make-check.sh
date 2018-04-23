#!/usr/bin/bash

echo ""
echo ""

make

if [ ! -e Lex ] || [ ! -x Lex ]; then # exist and executable
   echo ""
   echo "Makefile doesn't correctly create Executable!!!"
   echo ""
fi

make clean

echo ""
echo ""

if [ -e Lex ] || [ -e *.o ]; then
   echo "WARNING: Makefile didn't successfully clean all files"
fi

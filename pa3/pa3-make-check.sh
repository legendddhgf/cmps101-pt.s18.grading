#!/usr/bin/bash

if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi


cp *.java Makefile backup   # copy all files of importance into backup

rm -f Sparse *.class

make

if [ ! -e Sparse ] || [ ! -x Sparse ]; then # exist and executable
  echo ""
  echo "Makefile doesn't correctly create Executable!!!"
  echo ""
else
  echo ""
  echo "Makefile correctly creates Executable!"
  echo ""
fi

echo ""
echo ""

make clean


echo ""
echo ""

if [ -e Sparse ] || [ -e *.class ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
  echo ""
  echo ""
fi

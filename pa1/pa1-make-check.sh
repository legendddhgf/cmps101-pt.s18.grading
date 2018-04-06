
if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi

cp *.java Makefile backup   # copy all files of

make

echo ""
if [ ! -e Lex ] || [ ! -x Lex ]; then # exist and executable
  echo "Makefile probably doesn't correctly create Executable!!!"
else
  echo "Makefile probably correctly creates Executable!"
fi
echo ""

echo ""
echo ""

make clean

if [ -e Lex ] || [ -e *.class ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""
echo ""

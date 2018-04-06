#!/bin/bash
# cmps101-pt.s18 grading
# usage: pa1.sh
# (run within your pa1 directory to test your code)

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps101-pt.s18.grading/master/pa1
EXE1="pa1-make-check.sh"
EXE2="pa1-perf-check.sh"
SCRIPT=$(mktemp)

curl $SRCDIR/$EXE1 > $EXE1
curl $SRCDIR/$EXE2 > $EXE2
chmod +x $EXE1
chmod +x $EXE2
./$EXE1
./$EXE2
rm -f $EXE1
rm -f $EXE2

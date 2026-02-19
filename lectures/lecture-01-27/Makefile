########################################################################
#
# A simple Makefile for compiling Java code for CS392X1
#
########################################################################
JAVA=java
JAVAC=javac
########################################################################
MYCPS=.:./../../assets/Algorithms/algs4/algs4.jar
########################################################################
all::
all:: BinarySearch
all:: SBinarySearch
########################################################################
BinarySearch: BinarySearch.class; $(JAVA) -cp $(MYCPS) BinarySearch input.txt
BinarySearch.class: BinarySearch.java; $(JAVAC) -cp $(MYCPS) BinarySearch.java
########################################################################
SBinarySearch: SBinarySearch.class; $(JAVA) -cp $(MYCPS) SBinarySearch input.txt
SBinarySearch.class: SBinarySearch.java; $(JAVAC) -cp $(MYCPS) SBinarySearch.java
########################################################################
clean:: ; rm -f *~
########################################################################
cleanall:: clean
cleanall:: ; rm -f *.class
########################################################################
########################################################################
########################################################################

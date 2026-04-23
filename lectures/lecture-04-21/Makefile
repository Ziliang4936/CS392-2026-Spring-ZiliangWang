##################################################################
JAVA=java
JAVAC=javac
##################################################################
MYCLASSPATH=.:./../../libraries
##################################################################
all::
##################################################################
clean:: ; rm -f *~
cleanall:: clean
cleanall:: ; rm -f *.class
##################################################################
all:: \
QueenPuzzleTest
QueenPuzzleTest: \
QueenPuzzleTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) QueenPuzzleTest
QueenPuzzleTest.class: \
QueenBoard.java QueenPuzzleTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) QueenPuzzleTest.java
##################################################################
######################## end-of(Makefile) ########################
##################################################################

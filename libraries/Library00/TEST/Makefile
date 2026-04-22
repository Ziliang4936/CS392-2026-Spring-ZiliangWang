##################################################################
JAVA=java
JAVAC=javac
##################################################################
MYCLASSPATH=.:./../..
##################################################################
clean:: ; rm -f *~
cleanall:: clean
cleanall:: ; rm -f *.class
##################################################################
test:: \
FnInt1Test
FnInt1Test: \
FnInt1Test.class ; \
$(JAVA) -cp $(MYCLASSPATH) FnInt1Test
FnInt1Test.class: FnInt1Test.java; \
$(JAVAC) -cp $(MYCLASSPATH) FnInt1Test.java
##################################################################
test:: \
FnStrnTest
FnStrnTest: \
FnStrnTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) FnStrnTest
FnStrnTest.class: FnStrnTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) FnStrnTest.java
##################################################################
test:: \
FnListTest
FnListTest: \
FnListTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) FnListTest
FnListTest.class: FnListTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) FnListTest.java
##################################################################
test:: \
FnA1szTest
FnA1szTest: \
FnA1szTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) FnA1szTest
FnA1szTest.class: FnA1szTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) FnA1szTest.java
##################################################################
test:: \
LnListTest
LnListTest: \
LnListTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) LnListTest
LnListTest.class: LnListTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) LnListTest.java
##################################################################
test:: \
QueenPuzzleTest
QueenPuzzleTest: \
QueenPuzzleTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) QueenPuzzleTest
QueenPuzzleTest.class: \
QueenBoard.java QueenPuzzleTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) QueenPuzzleTest.java
##################################################################
test:: \
MyStackListTest
MyStackListTest: \
MyStackListTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) MyStackListTest
MyStackListTest.class: MyStackListTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) MyStackListTest.java
##################################################################
test:: \
MyStackArrayTest
MyStackArrayTest: \
MyStackArrayTest.class ; \
$(JAVA) -cp $(MYCLASSPATH) MyStackArrayTest
MyStackArrayTest.class: MyStackArrayTest.java; \
$(JAVAC) -cp $(MYCLASSPATH) MyStackArrayTest.java
##################################################################
######################## end-of(Makefile) ########################
##################################################################

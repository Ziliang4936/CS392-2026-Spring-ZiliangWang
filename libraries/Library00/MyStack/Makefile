########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp $(Library) MyStack.java
comp:: ; javac -cp $(Library) MyStackList.java
comp:: ; javac -cp $(Library) MyStackArray.java
comp:: ; javac -cp $(Library) MyStackFullExn.java
comp:: ; javac -cp $(Library) MyStackEmptyExn.java

###################################################################

test:: \
MyStackListTest
MyStackListTest: \
MyStackListTest.class; java -cp .::$(Library) MyStackListTest
MyStackListTest.class: MyStackListTest.java; javac -cp $(Library) MyStackListTest.java

###################################################################

test:: \
MyStackArrayTest
MyStackArrayTest: \
MyStackArrayTest.class; java -cp .::$(Library) MyStackArrayTest
MyStackArrayTest.class: MyStackArrayTest.java; javac -cp $(Library) MyStackArrayTest.java

########################(end of [Makefile])########################

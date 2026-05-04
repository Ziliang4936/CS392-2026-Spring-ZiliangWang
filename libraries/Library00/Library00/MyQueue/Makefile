########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp $(Library) MyQueue.java
comp:: ; javac -cp $(Library) MyQueueArray.java
comp:: ; javac -cp $(Library) MyQueueFullExn.java
comp:: ; javac -cp $(Library) MyQueueEmptyExn.java

###################################################################

test:: \
MyQueueArrayTest
MyQueueArrayTest: \
MyQueueArrayTest.class; java -cp .::$(Library) MyQueueArrayTest
MyQueueArrayTest.class: MyQueueArrayTest.java; javac -cp $(Library) MyQueueArrayTest.java

########################(end of [Makefile])########################

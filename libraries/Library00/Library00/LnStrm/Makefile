########################(beg of [Makefile])########################
Library=./../..
###################################################################

comp::

###################################################################

clean:: ; rm -f *~

cleanall:: clean
cleanall:: ; rm -f *.class

###################################################################

comp:: ; javac -cp .:$(Library) LnStrm.java
comp:: ; javac -cp .:$(Library) LnStcn.java
comp:: ; javac -cp .:$(Library) LnStrmSUtil.java

###################################################################

test:: \
LnStrmTest
LnStrmTest: \
LnStrmTest.class; java -cp .::$(Library) LnStrmTest
LnStrmTest.class: LnStrmTest.java; javac -cp $(Library) LnStrmTest.java

########################(end of [Makefile])########################

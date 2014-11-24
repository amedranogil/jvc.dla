jvc.dla
=======

JVC D-ILA / DLA type projector interface in Java

Libraries for custom control of projector.
Java Swing (PC) interface for controlling projector from a PC (multi platform: Windows, Linux, MacOS, etc...)

##Requierements##
You'll need:

+ A JRE to run 
+ A JDK to compile
+ Maven to build <http://maven.apache.org/>

##Building##
steps to build the code (make sure you have the requisites installed):

1. clone the project from GitHub
1. open a terminal in the cloned project folder
1. run:

> mvn install

The swing interface ready for running (double click if JRE installed and linked) should be in:
> jvc.dla/jvc.dla.swing/target/jvc.dla.swing-x.y.z-SNAPSHOT-jar-with-dependencies.jar

##Swing Interface Usage##
The swing interface is straigh forward, run the executable (by double clicking on the jar file) and a window should appear, initially you will not be able to connect to the projector, click connect to select (and check) the IP (or dns name) of the projector (future versions might include auto detect and RSR232 connection).

The window is a virtual representation of the remote controll (see user manual for the effect of each button). 

##Access Library##
jvc.dla.controller is a library that you may use to create your custom connection to the projector. Read the javadoc for more information

to create the javadoc run:
> mvn javadoc:javadoc

and open in your web browser:
> ${project}\target\site\apidocs\index.html

##Roadmap##
- [ ] provide RS232 external connector
- [ ] provide Android interface
- [ ] IP auto detect mechanism
- [ ] improve javadoc
- [x] improve visualization of remote and simple control

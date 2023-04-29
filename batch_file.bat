@echo off

REM I'm using Linux Arch, so not familiar with batch files, but better with bash scripts

REM Compiling first
javac ARXMLSorter.java

echo Testing output of Normal mode : 
java ARXMLSorter Normal.arxml

echo Testing other random ext. : 
java ARXMLSorter random_ext.xlsx

echo Testing Empty file :
java ARXMLSorter Empty.arxml

pause
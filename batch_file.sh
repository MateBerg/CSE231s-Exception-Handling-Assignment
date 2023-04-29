#!/bin/bash

# I'm using Linux Arch, so not familiar with batch files, but better with bash scripts

# Compiling first
javac main.java

echo "Testing output of Normal mode : " 
java main Normal.arxml
echo ""

echo "Testing other random ext. : "
java main random_ext.xlsx
echo ""

echo "Testing Empty file : "
java main Empty.arxml
echo ""

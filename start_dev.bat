@echo off
chcp 65001
if not exist class mkdir class
javac *.java -d class
java -cp class Main
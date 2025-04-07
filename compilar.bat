@echo off
if not exist class mkdir class
javac *.java -d class

echo Criando o arquivo manifest.txt...
echo Main-Class: Main>manifest.txt

echo Criando o arquivo JAR...
jar cfm out/chat.jar manifest.txt -C class .

echo Arquivo chat.jar criado com sucesso!
pause
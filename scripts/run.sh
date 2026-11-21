#!/bin/bash

cd "$(dirname "$0")/.."

mkdir -p out

javac -d out src/*.java

if [ $? -eq 0 ]; then
    java -cp out Main
else
    echo "Compilation failed."
fi
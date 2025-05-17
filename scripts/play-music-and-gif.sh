#!/bin/bash


#ASCII art
chafa  -f symbols --symbols ascii -c none --stretch resources/output2.gif &
afplay resources/musicfile.mp3

# no stretch + speed up
#chafa --animate=on --optimize=9 --clear --align mid,mid -d 5 --speed=1.09 resources/output2.gif &
#afplay resources/musicfile.mp3

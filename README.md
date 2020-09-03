# Texas Hold'em Algorithm

## INTRODUCTION
This is an Texas Hold'em Algorithm which accepts the input as 5 cards for dealer hand and at least one hand of two cards for player(s). 
Output will be players hands in ascending order (first hand will have the lowest value/combination and the last hand will have greatest value/combination).
In the case if hands are completely equal, the = sign will be placed between them. Input should have at least one line of dealer and players hands.
***No 3rd party libraries have been used***.

*INPUT*
```
<5 dealer board cards> <hand 1> <hand 2> <...> <hand N>

or

<5 dealer board cards 1> <hand 1> <hand 2> <...> <hand N>
...
<5 dealer board cards M> <hand 1> <hand 2> <...> <hand N>
```

*OUTPUT*
```
<hand block 1> <hand block 2> <...> <hand block n>

or

<hand block 1> <hand block 2> <...> <hand block n>
...
<hand block 1> <hand block 2> <...> <hand block n>
```

*INPUT EXAMPLE 1:*
```
TsAsQs7d4h KsJs Jh3d 8d7d 2d3d 7s3h Kh7s
```
*OUTPUT EXAMPLE 1:*
```
2d3d Jh3d 7s3h 8d7d Kh7s KsJs
```

*INPUT EXAMPLE 2:*
```
2h3s4d5c9h AcKh 6c9d 3h9s 9dJh As4c 
KcQc9c8c2h KcQd 5dJc 2c5s
6s6d6h8h2d 6s9c JhJd 9dAh 9dJh 7s3h Kh7s
```
*OUTPUT EXAMPLE 2:*
```
9dJh 3h9s AcKh=As4c 6c9d
2c5s 5dJc KcQd
7s3h 9dJh Kh7s 9dAh JhJd 6s9c
```

## Installation
Clone or download repository into designated folder. If you're dowloading ZIP from Github make sure to unarchieve the content.
You should see the main folder *texas-holdem*.

## Requirements 
Make sure you have installed the latest JRE on your local machine. Otherwise, go to the https://www.oracle.com/java/technologies/javase-jre8-downloads.html and follow the instructions.

## Quick Start
From the command line you have to go to the downloaded folder
```
cd *your-path*/texas-holdem/
```
You should be able to see this content:
```
bin      
src                 
texas-holdem.jar
```
Now if you have installed JRE run the following command
```
java -jar texas-holdem.jar
```

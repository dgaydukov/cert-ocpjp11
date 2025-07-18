# Binary Math

### Content
* [Binary to decimal conversion](#binary-to-decimal-conversion)
* [Storing negative numbers](#storing-negative-numbers)
* [Storing floating point numbers](#storing-floating-point-numbers)

### Binary to decimal conversion
You can easily convert between decimal and binary using this simple rule:
* divide decimal into 2 until you get 1/0 and write the numbers as binary
* multiple binary into 2 with power
Lets closer look into this example, we would convert 100:
```
100 / 2 = 50 => 0
50  / 2 = 25 => 0
25  / 2 = 12 => 1
12  / 2 = 6  => 0
6   / 2 = 3  => 0
3  / 2  = 1  => 1
1  /2   = 0  => 1
# So we write from bottom to top all the reminders as: 0110 0100, we add leading zero so we have 8 bits

# now let's convert it back to decimal 
0110 0100 => 0 * 2**0 + 0 * 2**1 + 1 * 2**2 + 0 * 2**3 + 0 * 2**4 + 1 * 2**5 + 1 * 2**6 + 0 * 2**7 = 100
```

### Storing negative numbers
There are 4 ways to store signed numbers:
* sing-magnitude - the most simple to understand. We just use most-significant-bit to store sign, so bit representation of positive and negative is almost the same:
    * problems:
        * 2 ways to represent 0
        * addition and subtraction require different behavior
```
# almost identical except the first bit
00101011 => 43
10101011 => -43
```
* Ones' complement - of binary number is such a number where you invert all the bits. It's called this way because if you add such 2 number: number + invertNumber you get binary value with all ones. So here negative numbers stored as inverse of positive binary number. One downside that you can have negative zero `-0`
```
# negative is invert
00101011 => 43
11010100 => -43
```
* Two's complement - most common way to represent signed integers in binary format (for integers this one is currently used in most PC). Negative number is NOT of positive binary number plus 1. This solves problems of 2 zeros and adding caret, that we have with ones' complement.
```
# binary form of -6
6 => 0110 (add first bit 0 as positive sign)
# slipt all bits
0110 => 1001
# add 1 to flipped bits
1001 + 1 => 1010

# backward calcuation
1010 = −(1×2**3) + (0×2**2) + (1×2**1) + (0×2**0) 
```
* offset binary - not used
* base -2 - not used


### Storing floating point numbers
let's see how fractional numbers stored in memory
```
convert separately integer and fractional part into binary
7.25=111.01
write number in exponential way (-1**s) * 1.m * 10**e, where s - first bit, m - mantis, e - power 10
111.01=1.1101 * 10**2
move our power number into binary 2=10
7.25=1.1101 * 10**10
write this into 32 bit; first bit - sign, 8 bit - for power, 23 bits for mantis
since power itself can be positive/negative we have simple rule based on 127 = power+127, in our case we would get 129, 
based on this rule you can see that for 32bit we can store max 10**128 - very large number 0[10000001]1101[all zeros]
we can convert it back
-1**0 * 1.1101 * 10**(10000001-127) == 1 * 1.1101 * 10**2
```
so if we use this calculation now it's clear that some fractions like 0.2 when we try to convert to binary we have to fill first 23 bits and discard others. Yet when we try to convert this binary back into decimal, we don't get 0.2, but 0.199989...  And this is limitation of binary math, not of processors or programming language. 
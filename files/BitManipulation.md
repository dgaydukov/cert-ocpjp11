# Bit Manipulation

### Content

* [Basics](#basics)
* [Tricks](#tricks)
    * [Odd or even](#odd-or-even)
    * [Number of 1 bits](#number-of-1-bits)

### Basics
There are 6 bit manipulation operators:
* `&`   (bitwise AND operation) - result bit is 1 if both corresponding bits are 1
* `|`   (bitwise OR operation) - result bit is 1 if at least one of the corresponding bits is 1
* `^`   (bitwise XOR operation) - result bit is 1 if the corresponding bits are different
* `~`   (bitwise NOT operation) - flips each bit (0 becomes 1, and 1 becomes 0)
* `<<`  (shifts the bits of the number to the left by n) - equivalent to multiplying the number by 2 for each shift
* `>>`  (shifts the bits of the number to the right by n) - equivalent to dividing the number by 2 for each shift
* `>>>` (shifts the bits to the right, filling the leftmost bits with zeroes)


### Tricks
##### Odd or even
Using this trick we can immediately check if number odd or even.
LSB (least significant bit) - the rightmost bit in binary representation of a number.
So if LSB = 1, number is odd, if 0 - even. But how we can check last bit. Well we can cal AND with 1. Then if the result is 0, it 0, if it 1, then result is 0. For example let's check 10 & 1.
```
10 => 1010
1  => 0001
AND => 1&0 0&1 1&0 0&1 => 0000
```
Java example
```java
boolean isEven(int n) {
    return (n & 1) == 0;
}
```

##### Power of 2
If we take logical AND with number and number-1 we got 0 if number is power of 2 or 0. This is due to binary representation. Every number which is power of 2, in binary would be `1000...`,. But if we subtract 1, then this number in binary would be `0111...`. And if you do AND on these bits, you always get `1 & 0`, so 0 as result. Let's look into example:
```
8 => 1000
7 => 0111
perform AND => 1&0 0&1 0&1 0&1 => 0000
```
Java implementation:
```java
boolean isPowerOf2(int n) {
    return n > 0 && ((n & (n - 1)) == 0);
}
```

##### Number of 1 bits
If you look into previous trick, what if you apply it to any number which is not power of 2. Then you get either n-1 or n-2, depending on 1 bits. Using this feature we can calculate number of 1 bits.
```

```
Java implementation:
```java
int numberOf1bis(int n) {
    int count = 0;
    while (n > 0) {
        n = n & (n - 1); // Turn off the rightmost set bit
        count++;
    }
    return count;
}
```
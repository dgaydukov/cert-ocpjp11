# Bit Manipulation

### Content

* [Bitwise and Bit Shift Operators](#bitwise-and-bit-shift-operators)
* [Basics](#basics)
* [BitSet](#bitset)
* [Utility](#utility)
  * [AND](#and)
  * [XOR](#xor)
  * [Bit masking](#bit-masking)
* [Tricks](#tricks)
    * [Odd or even](#odd-or-even)
    * [Power of 2](#power-of-2)
    * [Number of 1 bits](#number-of-1-bits)
    * [Reverse bits](#reverse-bits)

###### Bitwise and Bit Shift Operators
There are 2 types of bitwise:
* unary
  * `~` unary bitwise operator - invert bit pattern - change 0 to 1, and vice versa
    * java `byte` values are in range `-128 to 127`
    * `~5 => ~0000 0101 => 1111 1010 => -6`
    * How do you know that `1111 1010` is equal to `-6`
      * Since first byte is `1` we know that it's negative
      * How negative is stored in computer: we take positive => invert all bytes => add 1
      * step 1: take positive number 6 `6 => 0000 0110`
      * step 2: invert all bytes `0000 0110 => 1111 1001`
      * step 3: add 1 `1111 1001 +1 => 1111 1010`
      * So we can use this algo to calculate negative back to decimal value.
  * `>>` signed right shift - move all bits to right `5>>1 => 101>>1 => 10 = 2`
  * `<<` signed left shift - move all bits to left `5<<1 => 101<<1 => 1010 = 10`
* binary
  * `&` - AND - like logical &&
  * `^` - exclusive OR - like logical ||
  * `|` inclusive OR - 1 if both bits are different
    As you see strange behavior of inversion, this is due to fact, that int is 32 bits, and if we invert 5 `101`, then 29 first bitst that are 0
    sets to 1, that's why we got such strange number. For proper inversion we should take last 3 bits `..*010` and convert it into int
    if we do it we got proper result `~5 = 2`
```java
public class App{
    public static void main(String[] args) {
        int x = 5, y = 4;
        System.out.println("x => " + x + " / " + Integer.toBinaryString(x));
        System.out.println("y => " + y + " / " + Integer.toBinaryString(y));
        System.out.println("~x => " + ~x + " / " + Integer.toBinaryString(~x));
        System.out.println("x>>1 => " + (x>>1) + " / " + Integer.toBinaryString(x>>1));
        System.out.println("x<<1 => " + (x<<1) + " / " + Integer.toBinaryString(x<<1));
        System.out.println("x&y => " + (x&y) + " / " + Integer.toBinaryString(x&y));
        System.out.println("x^y => " + (x^y) + " / " + Integer.toBinaryString(x^y));
        System.out.println("x|y => " + (x|y) + " / " + Integer.toBinaryString(x|y));
    }
}
```
```
x => 5 / 101
y => 4 / 100
# since it's int and it store 32 bits, we have number with length 32
~x => -6 / 11111111111111111111111111111010
x>>1 => 2 / 10
x<<1 => 10 / 1010
x&y => 4 / 100
x^y => 1 / 1
x|y => 5 / 101
```

### Basics
There are 6 bit manipulation operators:
* `&`   (bitwise AND operation) - result bit is 1 if both corresponding bits are 1
* `|`   (bitwise OR operation) - result bit is 1 if at least one of the corresponding bits is 1
* `^`   (bitwise XOR operation) - result bit is 1 if the corresponding bits are different
* `~`   (bitwise NOT operation) - flips each bit (0 becomes 1, and 1 becomes 0)
* `<<`  (shifts the bits of the number to the left by n) - equivalent to multiplying the number by 2 for each shift
* `>>`  (shifts the bits of the number to the right by n) - equivalent to dividing the number by 2 for each shift
* `>>>` (shifts the bits to the right, filling the leftmost bits with zeroes)

### BitSet
* java class `java.util.BitSet` is used for bit manipulation
* using `boolean[]` not good, because each boolean value would be stored as byte and will consume more memory
* it uses a combination of int/long and bit-wise operations under-the-hood - for example long - 8 bytes - 64 bits, you can use one long value as underlying storage for array of 64 bits. Of course if you need to store 1024 bits, you would need 16 long numbers. So you can use an array of 16 longs as underlying storage for 1024 bits.
```java
import java.util.BitSet;

public class App {
  public static void main(String[] args) {
    BitSet set = new BitSet();
    // sets bits from 20 to 30 to 1
    set.set(20, 30);
    // clear bit
    set.clear(10);
    set.flip(10);
  }
}
```

### Utility
##### AND
bitmask is when you pack a ton of boolean values into a single integer and then use a bitwise AND to mask the single bit you are interested in. For example, you have 4 flags, you can store them in 4bit number, and max is 16. Now you can use AND operation to immediately see if flag is allowed in your operation or not.
```
16 => 1111
4  => 0100
AND   0100 => 4 # so flag 4 is inside 16
```
You can use this code in java `n & flag > 0`

Can be used to convert between signed and unsigned numbers:
* in java all primitive types are signed, except for `char`
* for `byte` it's in -128 => +127
* If you want to convert you need to use some smart logic, and the easiest way is to `& 0xFF`
* for short we can use `0xffff`
* under-the-hood methods like `Byte.toUnsignedInt` and `Short.toUnsignedInt` use this conversion to convert between signed and unsigned value
```java
for (int b = -128; b <= 127; b++) {
    System.out.println(b+" => "+(b & 0xff));
}
```

##### XOR
This operation has several properties that can be reused:
* `x ^ y = z  => x ^ z = y`
* `x ^ x = 0`, `x ^ 0 = x`

##### Bit masking
In essence, Bitmask is a list of boolean flags (for example isAlive, isMoving...) compressed into a single field, usually an integer (max 32 boolean values, but we can store up to 64 in `long`). It can cut quite a significant amount of JSON string size or memory footprint.
bit mask - a pattern of bit that we can apply to any binary, usually used for storing flags and "rights". For example for 8bit, you can store up to 8 flags or permissions. And mask is specific permission, for example if second bit is allowed. This is our mask. Using bitwise operations we can apply mask to data to check or set if our data has mask inside.
* apply mask: `data | mask` - set a subset of bits into the value `8 | 4 = 12`
* check mask: `data & mask` - extract a subset of bits from the value `12 | 4 = 4`
* clear mask: `data & ~mask` - remove a subset of bits from the value `12 & ~4 = 8`
* toggle mask: `data ^ mask` - toggle a subset of the bits in the value `12 ^ 4 = 8`, but `8 ^4 = 12` so you can easily toggle/switch between different `data`
  * actually `XOR => AND + NOT`, so we can see that `12 ^ 4 = 12 & ~4 = 8`
Let's consider this example. we have 4 bit data=8, and we want to apply mask=4.
```
# apply mask 4 to our value of 8
8 => 1000
4 => 0100
OR   1100 => 12 # now our value include bit 4 or third bit from right is set to 0

# check if mask 4 in our value of 12
12 => 1100
4  => 0100
AND   0100 => 4 # if mask bits were not set, the result would be 0

# clear/remove mask: 2 steps
# step1: invert our mask
4  => 0100
~4 => 1011
# spep2: call AND with inverted mask
12 => 1100
~4 => 1011
AND   1000 => 8 # result is our value before applying mask

# toggle mask
8 => 1000
4 => 0100
XOR  1100 => 12
```

### Tricks
##### Odd or even
Using this trick we can immediately check if number odd or even.
LSB (least significant bit) - the rightmost bit in binary representation of a number.
So if LSB = 1, number is odd, if 0 - even. But how we can check last bit. Well we can call `AND` with 1. 1 in binary is all zeros and LSB=1. So if we take AND with 1 and any number result would be either 1 or 0. Depending upon LSB. So if the LSB=1 then result would be 1, if LSB=0, the result would be 0. For all even numbers LSB=0, but for all odd numbers LSB=1.
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
This is because when you take `n-1` compare to `n` will always have one bit inverted. So that means it would have 0 where n has 1. And by running this several times until we get 0, we kind of remove 1 bit from the number. Look into example below (we take 15, largest number with 4 bits and all are 1):
```
15 => 1111
14 => 1110
      1100 => result 14
      
14 => 1100
13 => 1101
      1100 => resut 12

12 => 1100
11 => 1011
      1000 => result 8

8  => 1000
7  => 0111
      0000 => result 0
```
As you can see at each iteration, one bit that was 1 in n, is 0 in n-1. And because of this the operation `n & (n-1)` remove 1 bit from the number.
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

##### Reverse bits
We can reverse bits of number from right to left. For example:
```
8  => 1000 => reverse => 0001 => 3
12 => 1100 => reverse => 0011 => 3
15 => 1111 => reverse => 1111 => 15
```
Of course, we can convert number to binary, then iterate over it, and construct new binary with reversed bits and then convert back to binary. But we can use binary math to do it simpler.
```java
int reverse(int n){
    int r = 0;
    while (n > 0) {
        r = (r << 1) | (n & 1);
        n >>= 1;
    }
    return r;
}
```

##### Binary swapping
The first lesson on programming teaches how to swap 2 numbers. The most common way is to introduce third variable:
```java
void swap(){
  int a = 5, b = 10;
  int x = a;
  a = b;
  b = x;
}
```
We can do the same, using math without third variable
```java
void swap(){
    int a = 5, b = 10;
    a = a + b;
    b = a - b;
    a = a - b;
}
```
And of course we can use bit math ro resolve it without third variable
```java
void swap(){
    int a = 5, b = 10;
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
}
```
To explain, how it works, we need to keep in mind that `A ^ A = 0`.
# Binary Math

### Content
* [Binary to decimal conversion](#binary-to-decimal-conversion)
* [Converting negative numbers](#converting-floating-point-numbers)
* [Converting floating point numbers](#converting-floating-point-numbers)
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

### Converting negative numbers
There are 4 ways to store signed numbers:
* sign-magnitude - the most simple to understand. We just use most-significant-bit to store sign, so bit representation of positive and negative is almost the same:
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
* Two's complement - most common way to represent signed integers in binary format (for integers this one is currently used in most PC). Negative number is NOT of positive binary number plus 1. This solves problems of 2 zeros and adding caret, that we have with ones' complement. Arithmetic operation can be applied to both signed and unsigned integers. The `two` in the name refers to the `2**N`, so it means this number is a compliment to `2**N`. Complement - means simply addition of this number with `2**N`. (for example if we have 3 bits, then max is 2**3=8, so 3 is complement to 5, because when we add them we get 8). Calculation of the binary two's complement of a positive number essentially means subtracting the number from the `2**N`.
```
# binary form of -6: we need 3 steps
# step 1: calculate binary of 6
6 => 0110 (add first bit 0 as positive sign)
# step 2: flip all bits
0110 => 1001
# step 3: add 1 to flipped bits, ignoring overflow
1001 + 1 => 1010

# backward calcuation: exactly the same but we add minus to the rightmost bit
1010 = −(1×2**3) + (0×2**2) + (1×2**1) + (0×2**0) 
```
* offset binary - not used
* base -2 - not used

Now the question is how CPU knows what the number is normal binary or two's compliment negative binary. The answer is simple, CPU doesn't care, it just stores bytes. The CPU knows whether to do signed or unsigned arithmetic according to the op-code that you (or the compiler on your behalf) chose
```
15 => 1111
-1 => 1111 in two's complement

# why -1 => 1111, let's perform 3 steps
# step 1 convert 1 to binary
1 => 0001 (first number is 0 as sign)
# step 2: invert bits
0001 => 1110
# step 3: add 1
1110 + 1 => 1111
```
Some values in two's compliment system are the same:
* 128 and -128 => `1000 0000`

### Converting floating point numbers
Don't confuse (for number 2/3):
* numerator - upper part of fractional number or `2`
* denominator - bottom part of fractional number or `3`
* fractional bar - divisor

There are several types of numbers:
* natural numbers - positive numbers like: 1,2,3...
* integers - positive/negative natural numbers and zero -2,-1,0,1,2....
* rational numbers - those that can be represented as `ratio` like 1/3. all integers are rational cause 5 is 5/1
* irrational numbers - those that can't be represented as `ratio` of 2 numbers, like `sqrt(2)`
* real numbers - include both rational and irrational

converting decimal fraction to binary:
* to convert you start with fraction and multiply by 2 until fraction is 0
* only those with denominator of power 2 can be finitely represented in binary like 3/8=0.375
* other denominators like 1/10=0.1 or 1/5=0.2 can't be finitely represented in binary
```
# convert 7.25 to binary: convert separately integer and fractional part
# integer - divide on 2 until we reach 0
7 / 2 = 3 + 1
3 / 2 = 1 + 1
1 / 2 = 0 + 1
7 => 111
# fractional - multiply by 2 until we reach 0, and write the whole part from top-down
0.25 * 2 = 0 + 0.5
0.5  * 2 = 1 + 0
0.25 => 0.01

7.25 => 111.01

# for backwrad conversion: integer part convert normally but fraction with power of minus
111.01 = (1 * 2**0) + (1 * 2**1) + (1 * 2**2) + (0 * 2**-1) + (1 * 2**-2) = 1 + 2 + 4 + 0 + 1/4 = 7.25
```

Finite representation:
* not all numbers can be finitely represented in either decimal or binary
* to be finite it, we should multiply by base-n until we get 0: if we can't get 0, fraction can't be finite
* denominator rule:
  * if power of 2 can be divided into denominator without leftover - fraction can be represented as finite binary. example => 2,4,8,16,32.... any number if power of 2 can be divided into this number without leftover - generally for 2 it's only power of 2 number itself
  * if power of 10 can be divided into denominator without leftover - fraction can be represented as finite binary. example => 2,5,10,20,25,50,100,125.... any number if power of 10 can be divided into this number without leftover - generally for 10 is any combination of 2/5/10 like 2*2*2, 5*5*2*2, 5*2*10*5 and so on... As you can see here 2 is subset of 10, so any finite number within binary is also finite within decimal, but not vice versa.
```
convert 1/5 to binary
0.2 * 2 = 0 + 0.4
0.4 * 2 = 0 + 0.8
0.8 * 2 = 1 + 0.6
0.6 * 2 = 1 + 0.2 => as you see again we got 0.2
so 0.2 = 0.(1100)
```
so you can see that we have rational number like:
* 1/8=0.125 that can be represented as finite in both decimal and binary
* 1/5=0.2 that can be represented as finite in decimal but not in binary
* `1/11=0.0909090909090909...` or `5/17=0.29411764705882354...` that can't be finitely represented in both decimal and binary

As you see many rational numbers can't be represented as finite binary, so we have to do rounding in order to store it - but once we do this, we encounter rounding problem:
* stuffing infinite number of real numbers into finite number of bits is impossible, so whatever we do we always have rounding issue
* floating-point representation - most widely used representation of real numbers in PC. it has base=b, and precision=p.
There are 3 way to store floating point:
* float - 32bit
* double - 64bit
* long double (not available in java, yet we have it in C) - 80bit or 128bit

There are a few ways you can avoid floating point rounding error:
* don't use fractional numbers, use integers
* use BigDecimal/BigInteger

### Storing floating point numbers
* converting fraction to binary is one thing, but storing in memory is another, computers use `IEEE-754` as standard to store fractions in memory
* `fixed point` - convenient way to store fractions `7.25 => 111.01` but computers use the concept called `floating point`
* There are 2 standards in IEEE 754:
  * single precision: 32 bits => 1 bit for sign + 8 bits for exponent + 23 bits for mantissa
  * double precision: 64 bits => 1 bit for sign + 11 bits for exponent + 52 bits for mantissa
```
# step 1: convert separately integer and fractional part into binary
7.25 = 111.01
# step 2: normalize the binary so that only one non zero digit remains to the left of it (do ti by shifting n bits left and multiplying b 2**n)
# since power itself can be positive/negative we have simple rule based on 127 = power+127
111.01 = 1.1101 * 2**2 
# step 3: set first bit as sign, 0 - in our case cause it's a positive number
# step 4: normalize exponent => adjust exponent by adding 127 to it and convert to binary
2 + 127 = 129 = 1000_0001
# step 5: normalize mantissa => remove left bit cause it's 1 and add zeros to the length of 23
1.1101 = 1101 000 0000 0000 0000 0000
# step 6: write final number in 32 bits system as: sign + exponent + mantissa
0 - 1000 0001 - 110 1000 0000 0000 0000 0000
```

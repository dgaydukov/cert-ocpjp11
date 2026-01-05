# Numeral systems


### 4 types of literals
Java support only 4 types of literal:
* binary (base-2) - prefix with `0xb` or `0xB`
* octal (base-8) - prefix with 0, use digits 0-7
* decimal (base-10) - normal decimal numbers
* hex (base-16) - prefix with `0x` or `0X` with digits 0-9 and letters a-e

```java
public class Test{
    public static void main(String[] args) {
        // Decimal declaration and possible chars are [0-9]
        int decimal =  495;
        System.out.println("decimal => " + decimal);
        // HexaDecimal declaration starts with 0X or 0x and possible chars are [0-9A-Fa-f]
        int hex =  0X1EF;
        System.out.println("hex => " + hex);
        // Octal declaration starts with 0 and possible chars are [0-7]
        int octal =  0757;
        System.out.println("octal => " + octal);
        // Binary representation starts with 0B or 0b and possible chars are [0-1]
        int binary =  0b111101111;
        System.out.println("binary => " + binary);
    }
}
```
```
decimal => 495
hex => 495
octal => 495
binary => 495
```

### Floating point
Floating point only supported for 2 numeral systems:
* decimal - normal numbers `3.14 = 3*10**0 + 1*10**-1 + 4*10**-2`
* hex - you have to add `p{index}` where `p` stands for power and index - value 0-n. The calculation is simple

Example of hex floating point:
```
1.1p0 => (1 + 1 * 16**-1) * 2**0 = 1.0625
1.1p4 => (1 + 1 * 16**-1) * 2**4 = 17
```
Java code
```java
public class Test{
    public static void main(String[] args) {
        double d = 1.1;
        double d0 = 0x1.1P0;
        double d1 = 0x1.1P1;
        double d2 = 0x1.1P2;
        double d3 = 0x1.1P3;
        System.out.println("decimal => " + d);
        System.out.println("0x1.1P0 => " + d0);
        System.out.println("0x1.1P1 => " + d1);
        System.out.println("0x1.1P2 => " + d2);
        System.out.println("0x1.1P3 => " + d3);
    }
}
```
```
decimal => 1.1
0x1.1P0 => 1.0625
0x1.1P1 => 2.125
0x1.1P2 => 4.25
0x1.1P3 => 8.5
```
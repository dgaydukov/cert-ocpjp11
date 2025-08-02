package com.aeron.hacks.misc.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeUseCase {

  public static void main(String[] args) {
    UnsafeUseCase app = new UnsafeUseCase();
    System.out.println("----------------------storeInt----------------------");
    app.storeInt();
    System.out.println("----------------------storeIntObj----------------------");
    app.storeIntObj();
    System.out.println("----------------------storeIntByteArray----------------------");
    app.storeIntByteArray();
  }

  public Unsafe getUnsafe()  {
    try{
      Field f = Unsafe.class.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      return  (Unsafe) f.get(null);
    } catch (NoSuchFieldException | IllegalAccessException ex){
      throw new RuntimeException(ex);
    }
  }

  /**
   * Here example of putInt(long address, int x)
   * as you see here we can just set int value into memory address in off-heap
   */
  public void storeInt(){
    Unsafe unsafe = getUnsafe();
    long address = unsafe.allocateMemory(1);
    int value = 99;
    System.out.println("Put into memory: value="+value+", address="+address);
    unsafe.putInt(address, value);
    int savedValue = unsafe.getInt(address);
    System.out.println("Get value from memory: value="+savedValue);
  }

  /**
   * Here example of putInt(Object o, long offset, int x)
   * we change memory address inside heap for given object using offset
   */
  public void storeIntObj() {
    Unsafe unsafe = getUnsafe();
    Person mike32 = new Person("Mike", 32);
    System.out.println("mike => "+mike32);
    int value = 99;
    Field ageField;
    try{
      ageField = mike32.getClass().getDeclaredField("age");
    } catch (NoSuchFieldException ex){
      throw new RuntimeException(ex);
    }
    long offset = unsafe.objectFieldOffset(ageField);
    System.out.println("Person age from memory: offset="+offset+", age="+unsafe.getInt(mike32, offset));
    System.out.println("Put into memory: value="+value+", offset="+offset);
    unsafe.putInt(mike32, offset, value);
    System.out.println("mike => "+mike32);
  }

  /**
   * Get value from byte array at specific offset (index)
   */
  public void storeIntByteArray(){
    Unsafe unsafe = getUnsafe();
    byte[] byteArray = new byte[10];
    int value = 99;
    int offset = 1;
    System.out.println("Get value from byte array: offset="+offset+", valueInByteArray="+unsafe.getInt(byteArray, offset));
    System.out.println("Put value into byte array: offset="+offset+", value="+value);
    unsafe.putInt(byteArray, offset, value);
    System.out.println("Get value from byte array: offset="+offset+", valueInByteArray="+unsafe.getInt(byteArray, offset));
  }
}

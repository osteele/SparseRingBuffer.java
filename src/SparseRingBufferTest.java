package src;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SparseRingBufferTest {
  @Test
  void testIsEmpty() {
    var buffer = new SparseRingBuffer(100);
    assert buffer.isEmpty();

    buffer.put(50, 1);
    assert !buffer.isEmpty();
  }

  @Test
  void testSize() {
    var buffer = new SparseRingBuffer(100);
    Assert.assertEquals(buffer.size(), 0);

    buffer.put(50, 1);
    Assert.assertEquals(buffer.size(), 1);

    buffer.put(60, 2);
    Assert.assertEquals(buffer.size(), 2);

    buffer.put(60, 3);
    Assert.assertEquals(buffer.size(), 2);
  }

  @Test
  void testContainsKey() {
    var buffer = new SparseRingBuffer(100);
    Assert.assertFalse(buffer.containsKey(10));

    buffer.put(10, 1);
    Assert.assertTrue(buffer.containsKey(10));

    buffer.put(20, 1);
    Assert.assertTrue(buffer.containsKey(10));
    Assert.assertTrue(buffer.containsKey(20));
  }

  @Test
  void testPut() {
    var buffer = new SparseRingBuffer(100);
    buffer.put(10, 1);
    Assert.assertTrue(buffer.containsKey(10));
    Assert.assertEquals(buffer.get(10), 1);
    Assert.assertEquals(buffer.toString(), "{10:1}");

    buffer.put(10, 2);
    Assert.assertEquals(buffer.get(10), 2);
    Assert.assertEquals(buffer.toString(), "{10:2}");

    buffer.put(20, 3);
    Assert.assertEquals(buffer.get(10), 2);
    Assert.assertEquals(buffer.get(20), 3);
    Assert.assertEquals(buffer.toString(), "{10:2,20:3}");

    // test wrapping
    buffer = new SparseRingBuffer(100);
    buffer.put(10, 1);
    buffer.put(20, 2);
    Assert.assertEquals(buffer.get(10), 1);
    Assert.assertEquals(buffer.get(20), 2);
    buffer.put(105, 3);
    Assert.assertEquals(buffer.size(), 3);
    Assert.assertEquals(buffer.toString(), "{10:1,20:2,105:3}");

    // wrap onto the first element
    buffer = new SparseRingBuffer(100);
    buffer.put(10, 1);
    buffer.put(20, 2);
    buffer.put(110, 3);
    Assert.assertEquals(buffer.size(), 2);
    Assert.assertFalse(buffer.containsKey(10));
    Assert.assertEquals(buffer.get(20), 2);
    Assert.assertEquals(buffer.get(110), 3);
    Assert.assertEquals(buffer.toString(), "{20:2,110:3}");

    // wrap past the first element
    buffer = new SparseRingBuffer(100);
    buffer.put(10, 1);
    buffer.put(20, 2);
    buffer.put(115, 3);
    Assert.assertEquals(buffer.size(), 2);
    Assert.assertFalse(buffer.containsKey(10));
    Assert.assertEquals(buffer.get(20), 2);
    Assert.assertEquals(buffer.get(115), 3);
    Assert.assertEquals(buffer.toString(), "{20:2,115:3}");
  }

  @Test
  void testIterator() {
    var buffer = new SparseRingBuffer(100);
    Assert.assertEquals(buffer.toString(), "{}");

    buffer.put(10, 1);
    Assert.assertEquals(buffer.toString(), "{10:1}");

    buffer.put(20, 2);
    Assert.assertEquals(buffer.toString(), "{10:1,20:2}");

    buffer.put(30, 3);
    Assert.assertEquals(buffer.toString(), "{10:1,20:2,30:3}");
  }

  @Test
  void testRemoveBefore() {
    // for (SparseRingBuffer ch : channels.values()) {
    // ch.removeBefore(sampleTime - duration + 1);
    // }
  }
}

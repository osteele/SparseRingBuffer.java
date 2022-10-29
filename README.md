# SpringRingBuffer.java

`SparseRingBuffer` implements a sparse list of integers stored in a fixed-size
ring buffer.

The buffer is sparse in time, not space: like a ring buffer, it uses fixed-size
arrays that are sized for the largest possible number of samples; but like a
linked list, each entry contains a pointer to the next populated entry, and is
possible to skip entries.

The class uses names from java.util.AbstractList and
java.util.AbstractCollection, but it does not implement those interfaces: first,
because it implements only the functionality that I have needed for my specific
use cases; second, because it is specalized to the `int` primitive type, which
is not possible at the time of this writing with Java generics.

I wrote this for <https://github.com/osteele/SerialPlotter>, and ended up
developing this part of it outside of Processing so that I could use Java
development tools to develop the ring buffer class.

## License

Copyright (c) 2022 Oliver Steele.

Available under the MIT License.

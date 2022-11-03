# M2-4 Programming Assignment
#### Student: Jeremiah Webb

## Chosen Sorting Algorithms
1. Bubble Sort
2. Quick Sort

## Dependency Requirements
1. Requires [Maven](https://github.com/apache/maven) for [JMH](https://github.com/openjdk/jmh).

JMH is used as a benchmarking framework for keeping track of runtime.<br>

To physically look at the code being ran for this assignment go to:
`BubbleVsQuick-Sort/test/src/main/java/org/sample/MyBenchmark.java`<br>
To edit the number of elements in all arrays when testing, go to the above file, and change ["LIST_SIZE"](https://github.com/illusion173/BubbleVsQuick-Sort/blob/d7b821bd0a1566e181da8136aafda1b381cac32e/test/src/main/java/org/sample/MyBenchmark.java#L121)


## Compilation & Execution Instructions
1. `cd test/`
2. `mvn clean install`
3. `mvn clean verify`
4. `java -jar target/benchmarks.jar`
For Json Outputs:
`java -jar target/benchmarks.jar -rf json`


## Contribution
Add more algorithms! Merge Sort, insertion sort, etc.
Do a pull request and I can add it!

## System Originally Ran On:
- Operating System: Arch Linux x86_64
- Kernel: 6.0.2-arch-1
- CPU: AMD Ryzen 7 5800H
- GPU: GeForce RTX 3060M
- Memory: 16GB 3200 MHz
- OpenJDK Version: 19.0.1



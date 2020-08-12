# KudesuRand
Counter-based pseudorandom number generation algorithm

This algorithm based on middle-square method with Weyl sequence, but with major changes;

It is very fast counter-based random generator which can pass statistical tests faultless with more than 16 TB generated data;

For this generator, seeding is important process: seed should be about half ones and half zeros with irregular template, so this class contains a generator of such seeds;

Algorithm uniformity tested for nextInt() and nextLong() methods by:
 * NIST STS
 * GJrand STS
 * PractRand STS on 16 TB (2^44 bytes) data with no fails;

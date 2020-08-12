# KudesuRandom
Counter-based pseudorandom number generation algorithm

[![GitHub last commit](https://img.shields.io/github/last-commit/kudesunik/KudesuRandom.svg)](https://github.com/Kudesunik/KudesuRandom/commits)
[![GitHub](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/Kudesunik/KudesuRandom/blob/master/LICENSE)

### Description

This algorithm based on middle-square method with Weyl sequence, but with major changes;

It is very fast counter-based random generator which can pass statistical tests faultless with more than 16 TB generated data;

For this generator, seeding is important process: seed should be about half ones and half zeros with irregular template, so this class contains a generator of such seeds;

Algorithm uniformity tested for nextInt() and nextLong() methods by:
 * NIST STS
 * GJrand STS
 * PractRand STS on 16 TB (2^44 bytes) data with no fails;

### Build 

This is Java Ant project.

#### Contributors

When you're ready to submit your code, just make a pull request.

#### Reporting bugs

1. Start by searching issue tracker for duplicates;
2. Create a new issue, explaining the problem in proper detail.

### License
MIT License. See LICENSE file for details.

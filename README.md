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
 
### What for?

I want to make fast and statistical correct algorithm with counter that will allow me to quickly get a pseudo-random value at any given time without overwriting the generator seed. It's all.

### Tests

PractRand Statistical Test Suite and performance tests results:

#### PractRand STS

<details><summary>nextInt()</summary>
~/Programming/Random/PractRand$ ./test/test-final-int | ./RNG_test stdin32 -tlmaxonly<br />RNG_test using PractRand version 0.95<br />RNG = RNG_stdin32, seed = unknown<br />test set = core, folding = standard (32 bit)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 256 megabytes (2^28 bytes), time= 3.8 seconds<br />  no anomalies in 168 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 512 megabytes (2^29 bytes), time= 8.6 seconds<br />  no anomalies in 180 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 1 gigabyte (2^30 bytes), time= 17.4 seconds<br />  no anomalies in 194 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 2 gigabytes (2^31 bytes), time= 33.8 seconds<br />  no anomalies in 205 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 4 gigabytes (2^32 bytes), time= 64.4 seconds<br />  no anomalies in 217 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 8 gigabytes (2^33 bytes), time= 126 seconds<br />  no anomalies in 230 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 16 gigabytes (2^34 bytes), time= 261 seconds<br />  no anomalies in 240 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 32 gigabytes (2^35 bytes), time= 529 seconds<br />  no anomalies in 251 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 64 gigabytes (2^36 bytes), time= 1068 seconds<br />  no anomalies in 263 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 128 gigabytes (2^37 bytes), time= 2120 seconds<br />  no anomalies in 273 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 256 gigabytes (2^38 bytes), time= 4247 seconds<br />  no anomalies in 284 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 512 gigabytes (2^39 bytes), time= 8574 seconds<br />  no anomalies in 295 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 1 terabyte (2^40 bytes), time= 17166 seconds<br />  no anomalies in 304 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 2 terabytes (2^41 bytes), time= 34151 seconds<br />  no anomalies in 313 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 4 terabytes (2^42 bytes), time= 68623 seconds<br />  no anomalies in 323 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 8 terabytes (2^43 bytes), time= 137222 seconds<br />  no anomalies in 331 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 16 terabytes (2^44 bytes), time= 264083 seconds<br />  no anomalies in 339 test result(s)<br /><br />rng=RNG_stdin32, seed=unknown<br />length= 32 terabytes (2^45 bytes), time= 489072 seconds<br />  Test Name                         Raw       Processed     Evaluation<br />  [Low1/32]TMFn(2+13):wl            R= +26.0  p~=   9e-9    very suspicious  <br />  ...and 346 test result(s) without anomalies
</details>

<details><summary>nextLong()</summary>
~/Programming/Random/PractRand$ ./test/test-final-long | ./RNG_test stdin64 -tlmaxonly<br />RNG_test using PractRand version 0.95<br />RNG = RNG_stdin64, seed = unknown<br />test set = core, folding = standard (64 bit)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 256 megabytes (2^28 bytes), time= 3.7 seconds<br />  no anomalies in 213 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 512 megabytes (2^29 bytes), time= 8.7 seconds<br />  no anomalies in 229 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 1 gigabyte (2^30 bytes), time= 17.5 seconds<br />  no anomalies in 246 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 2 gigabytes (2^31 bytes), time= 34.3 seconds<br />  no anomalies in 263 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 4 gigabytes (2^32 bytes), time= 64.9 seconds<br />  no anomalies in 279 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 8 gigabytes (2^33 bytes), time= 126 seconds<br />  no anomalies in 295 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 16 gigabytes (2^34 bytes), time= 245 seconds<br />  no anomalies in 311 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 32 gigabytes (2^35 bytes), time= 478 seconds<br />  no anomalies in 325 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 64 gigabytes (2^36 bytes), time= 949 seconds<br />  no anomalies in 340 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 128 gigabytes (2^37 bytes), time= 1865 seconds<br />  no anomalies in 355 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 256 gigabytes (2^38 bytes), time= 3719 seconds<br />  no anomalies in 369 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 512 gigabytes (2^39 bytes), time= 7492 seconds<br />  no anomalies in 383 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 1 terabyte (2^40 bytes), time= 15002 seconds<br />  no anomalies in 397 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 2 terabytes (2^41 bytes), time= 29816 seconds<br />  no anomalies in 409 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 4 terabytes (2^42 bytes), time= 59935 seconds<br />  no anomalies in 422 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 8 terabytes (2^43 bytes), time= 119886 seconds<br />  no anomalies in 434 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 16 terabytes (2^44 bytes), time= 233727 seconds<br />  no anomalies in 445 test result(s)<br /><br />rng=RNG_stdin64, seed=unknown<br />length= 32 terabytes (2^45 bytes), time= 432759 seconds<br />  no anomalies in 455 test result(s)
</details>

#### Performance

Performed on JMH (jmh-core-1.21)

![alt text](https://github.com/Kudesunik/KudesuRandom/blob/master/resources/tests/performance.png)

### Build 

This is Java Ant project.

#### Contributors

When you're ready to submit your code, just make a pull request.

#### Reporting bugs

1. Start by searching issue tracker for duplicates;
2. Create a new issue, explaining the problem in proper detail.

### License
MIT License. See LICENSE file for details.

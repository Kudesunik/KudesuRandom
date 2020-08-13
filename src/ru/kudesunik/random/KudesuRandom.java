package ru.kudesunik.random;

/**
 * Counter-based pseudorandom number generation algorithm;
 * <br>Based on middle-square method with Weyl sequence, but with major changes;
 * <br>It is very fast counter-based random generator which can pass statistical tests faultless with more than 16 TB generated data;
 * <p>For this generator, seeding is important process: seed should be about half ones and half zeros with irregular template, so this class contains a generator of such seeds;
 * <p>Algorithm uniformity tested for nextInt() and nextLong() methods by:
 * <br>NIST STS
 * <br>GJrand STS
 * <br>PractRand STS on 16 TB (2^44 bytes) data with no fails;
 * 
 * <p>Warning: this alghorithm implementation not thread safe!
 * 
 * @since 2020
 * @version 1.2.0
 * @author Kudesunik
 */
public class KudesuRandom implements Cloneable {
	
	private static final long SEED = -4995613063753901367L;
	
	private static final int SEED_BIT_CHECK = 2;
	private static final int SEED_BIT_SHIFT = 15;
	
	private long publicSeed;
	private long currentSeed;
	
	private long count;
	
	private double nextGaussian;
	
	public KudesuRandom() {
		setSeed(System.nanoTime() ^ System.identityHashCode(this));
	}
	
	public KudesuRandom(long seed) {
		setSeed(seed);
	}
	
	/**
	 * Sets pseudorandom number generator current <code>count</code> by <code>long</code> value;
	 * <br>If the count is 0, then the first value of <code>nextInt()</code>, <code>nextLong()</code> and other methods will be 0;
	 */
	public void setCounter(long count) {
		this.count = count;
	}
	
	/**
	 * Returns pseudorandom number generator current <code>count</code> value
	 */
	public long getCounter() {
		return count;
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed <code>int</code> value by current internal <code>count</code> value
	 */
	public int nextInt() {
		long x = currentSeed * count;
		long y = x;
		x = ((x >>> 32) * x) + count;
		return (int) ((((x >>> 32) * x) + y + (count++)) >>> 32);
	}
	
	/**
	 * Returns a pseudorandom, uniformly distributed <code>int</code> value between <code>0</code> (inclusive) and <code>max</code> value (exclusive)
	 * by current internal <code>count</code> value
	 */
	public int nextIntRange(int max) {
		if(max <= 0) {
			throw new IllegalArgumentException("illegal bound: " + max + " (must be positive)");
		}
		int t = nextInt();
		int m = max - 1;
		if((max & m) == 0) {
			return t & m;
		}
		int u = t >>> 1;
		while((u + m - (t = u % max)) < 0) {
			u = nextInt() >>> 1;
		}
		return t;
	}
	
	/**
	 * Returns a pseudorandom, uniformly distributed <code>int</code> value between <code>min</code> (inclusive) and <code>max</code> (exclusive)
	 */
	public int nextIntRange(int min, int max) {
		if(min >= max) {
			throw new IllegalArgumentException("illegal bounds: " + max + " must be greater than " + min);
		}
		return min + nextIntRange(Math.max(min, max) - min);
	}
	
	/**
	 * Returns pseudorandom, uniformly distributed <code>int</code> by the specified <code>counter</code> value, 
	 * but store generator internal <code>count</code>
	 */
	public int getInt(long counter) {
		long buffer = count;
		this.count = counter;
		int result = nextInt();
		this.count = buffer;
		return result;
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed <code>long</code> value by current internal <code>count</code> value
	 */
	public long nextLong() {
		long x = currentSeed * count;
		long y = x;
		x = ((x >>> 32) * x) + count;
		x = ((x >>> 32) * x) + y;
		return ((((x >>> 32) * x) + y + count) >>> 32) ^ (x + (count++));
	}
	
	/**
	 * Returns a pseudorandom, uniformly distributed <code>long</code> value between <code>0</code> (inclusive) and <code>max</code> value (exclusive)
	 * by current internal <code>count</code> value
	 */
	public long nextLongRange(long max) {
		if(max <= 0) {
			throw new IllegalArgumentException("illegal bound: " + max + " (must be positive)");
		}
		long t = nextLong();
		long m = max - 1L;
		if((max & m) == 0) {
			return t & m;
		}
		long u = t >>> 1;
		while((u + m - (t = u % max)) < 0) {
			u = nextLong() >>> 1;
		}
		return t;
	}
	
	/**
	 * Returns a pseudorandom, uniformly distributed <code>long</code> value between <code>min</code> (inclusive) and <code>max</code> (exclusive)
	 */
	public long nextLongRange(long min, long max) {
		if(min >= max) {
			throw new IllegalArgumentException("illegal bounds: " + max + " must be greater than " + min);
		}
		return min + nextLongRange(Math.max(min, max) - min);
	}
	
	/**
	 * Returns pseudorandom, uniformly distributed <code>long</code> by the specified <code>counter</code> value, 
	 * but store generator internal <code>count</code>
	 */
	public long getLong(long counter) {
		long buffer = count;
		this.count = counter;
		long result = nextLong();
		this.count = buffer;
		return result;
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed <code>boolean</code> value
	 */
	public boolean nextBoolean() {
		return nextInt() < 0;
	}
	
	/**
	 * Returns pseudorandom, uniformly distributed <code>boolean</code> by the specified <code>counter</code> value, 
	 * but store generator internal <code>count</code>
	 */
	public boolean getBoolean(long counter) {
		long buffer = count;
		this.count = counter;
		boolean result = nextBoolean();
		this.count = buffer;
		return result;
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed <code>float</code> value
	 */
	public float nextFloat() {
		return (nextInt() >>> 9) * 0x1.0P-23f;
	}
	
	/**
	 * Returns pseudorandom, uniformly distributed <code>float</code> by the specified <code>counter</code> value, 
	 * but store generator internal <code>count</code>
	 */
	public float getFloat(long counter) {
		long buffer = count;
		this.count = counter;
		float result = nextFloat();
		this.count = buffer;
		return result;
	}
	
	/**
	 * Returns the next pseudorandom, uniformly distributed <code>double</code> value
	 */
	public double nextDouble() {
		return (nextLong() >>> 12) * 0x1.0P-52d;
	}
	
	/**
	 * Returns pseudorandom, uniformly distributed <code>double</code> by the specified <code>counter</code> value, 
	 * but store generator internal <code>count</code>
	 */
	public double getDouble(long counter) {
		long buffer = count;
		this.count = counter;
		double result = nextDouble();
		this.count = buffer;
		return result;
	}
	
	/**
	 * Generates pseudorandom, uniformly distributed bytes and places them into a user-supplied byte array
	 */
	public void nextBytes(byte[] bytes) {
		int i = bytes.length;
		int n = 0;
		while(i != 0) {
			n = Math.min(i, 8);
			for(long bits = nextLong(); (n--) != 0; bits >>>= 8) {
				bytes[--i] = (byte) bits;
			}
		}
	}
	
	/**
	 * Returns the next pseudorandom, 
	 * Gaussian normally distributed <code>double</code> value with mean 0.0 and standard deviation 1.0
	 */
	public double nextGaussian() {
		double random;
		if(Double.isNaN(nextGaussian)) {
			double x = nextDouble();
			double y = nextDouble();
			double alpha = 2 * Math.PI * x;
			double r = Math.sqrt(-2 * Math.log(y));
			random = r * Math.cos(alpha);
			nextGaussian = r * Math.sin(alpha);
		} else {
			random = nextGaussian;
			nextGaussian = Double.NaN;
		}
		return random;
	}
	
	/**
	 * Sets the seed of the pseudorandom number generator using an <code>long</code> seed;
	 * <br>Also resets internal pseudorandom generator state
	 */
	public void setSeed(long seed) {
		currentSeed = SEED;
		publicSeed = seed;
		count = seed;
		long preSeed = nextLong();
		int bit = Long.bitCount(preSeed) - 32;
		int bitCount;
		if((bitCount = Math.abs(bit)) <= SEED_BIT_CHECK) {
			this.currentSeed = preSeed;
			return;
		}
		int mark = (bit > 0) ? 0 : 1;
		for(int i = 0; (i < 63) && (bitCount > SEED_BIT_CHECK); i++) {
			preSeed = Long.rotateRight(preSeed, SEED_BIT_SHIFT);
			if((preSeed & 1) != mark) {
				preSeed ^= 1;
				bitCount--;
			}
		}
		this.currentSeed = preSeed;
		reset();
	}
	
	/**
	 * Returns current pseudorandom number generator <code>long</code> seed
	 */
	public long getSeed() {
		return this.publicSeed;
	}
	
	@Override
	public KudesuRandom clone() {
		KudesuRandom random = new KudesuRandom(this.publicSeed);
		random.count = this.count;
		return random;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + (int) (currentSeed ^ (currentSeed >>> 32));
		long temp;
		temp = Double.doubleToLongBits(nextGaussian);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (publicSeed ^ (publicSeed >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		KudesuRandom other = (KudesuRandom) obj;
		if(count != other.count) {
			return false;
		}
		if(currentSeed != other.currentSeed) {
			return false;
		}
		if(Double.doubleToLongBits(nextGaussian) != Double.doubleToLongBits(other.nextGaussian)) {
			return false;
		}
		return (publicSeed == other.publicSeed);
	}
	
	/**
	 * Resets internal pseudorandom generator state
	 */
	public void reset() {
		this.count = Long.MIN_VALUE;
		this.nextGaussian = Double.NaN;
	}
}
